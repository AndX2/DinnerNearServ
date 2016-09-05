package com.github.andx2.dinnernearserv;


import com.github.andx2.dinnernearserv.pojo.GroupUnits;
import com.github.andx2.dinnernearserv.pojo.MessageStub;
import com.github.andx2.dinnernearserv.pojo.Unit;
import com.github.andx2.dinnernearserv.storage.MessageStorage;
import com.github.andx2.dinnernearserv.storage.UnitStorage;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.github.andx2.dinnernearserv.util.StringHelper.randStr;
import static com.github.andx2.dinnernearserv.util.SystemIO.sop;
import static java.net.HttpURLConnection.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class AppInitializer {

    private static final Logger logger = Logger.getLogger(AppInitializer.class.getCanonicalName());

    private static MessageStorage messageStorage;
    private static UnitStorage unitStorage;

    public static void main(String[] args) throws SQLException {

        port(4567);
        enableDebugScreen();

        int maxThreads = 8;
        int minThreads = 2;
        int timeOutMillis = 1000;
        threadPool(maxThreads, minThreads, timeOutMillis);

        String tmpDirPath = System.getProperty("java.io.tmpdir");
        sop(tmpDirPath);
        staticFiles.externalLocation("C:\\tmp");

        before("/posts/*", (request, response) -> {
            boolean autorised = false;
            // ... check if authenticated
            if (!autorised) {
                halt(HTTP_UNAUTHORIZED, "Unauthorized!");
            }
        });

        get("/posts", (req, res) -> {
            res.redirect("/bar", HTTP_MOVED_PERM);
            return "Hello, path 'post'";
        });

        get("/bar", (request, response) -> {
            response.status(HTTP_OK);
            return "Hello, path 'bar'";
        });

        Gson gson = new Gson();
        get("/hello", "application/json", (request, response) -> {
            List<MessageStub> list = new ArrayList<>();
            list.add(new MessageStub(1, "Name", "Desc"));
            list.add(new MessageStub(2, "Name2", "Desc2"));
            list.add(new MessageStub(3, "Name3", "Desc3"));
            response.status(HTTP_OK);
            return list;
        }, gson::toJson);

        unitStorage = UnitStorage.getInstance(UnitStorage.H2);

        //region test /unit servlet
        GroupUnits groupUnits = new GroupUnits();
        groupUnits.setId(6L);
        groupUnits.setName("GroupNAmeStub1");
        groupUnits.setDescription("GroupDescStub1");
        try {
            for (int i = 0; i < 5; i++) {
                Unit unit = new Unit();
                unit.setName(randStr());
                unit.setDescription(randStr());
                unit.setGroup(groupUnits);
                unitStorage.add(unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        get("/unit", "application/json", (request, response) -> {
            response.status(HTTP_OK);
            return unitStorage.findAll();
        }, gson::toJson);

        post("/unit", "application/json", (request, response) -> {

            Unit unit = gson.fromJson(request.body(), Unit.class);
            response.status(HTTP_CREATED);
            return unitStorage.add(unit);
        });

        get("/unit/:id", "application/json", (request, response) -> {
            response.status(HTTP_OK);
            return unitStorage.findById(Long.parseLong(request.params(":id")));
        }, gson::toJson);

        put("/unit/:id", "application/json", (request, response) -> {
            Unit unit = gson.fromJson(request.body(), Unit.class);
            unit.setId(Long.parseLong(request.params(":id")));
            response.status(HTTP_ACCEPTED);
            return unitStorage.update(unit);
        });

        delete("/unit/:id", "application/json", (request, response) -> {
            Unit unit = unitStorage.delete(Long.parseLong(request.params(":id")));
            if (unit != null) response.status(HTTP_ACCEPTED);
            return unit;
        }, gson::toJson);


        //endregion

        messageStorage = MessageStorage.getInstance(MessageStorage.H2);

        sop("Server is started on port 4567!");
        sop("try Hello from link http://localhost:4567/hello");
        sop("for exit press: CLRL + C");

    }


}
