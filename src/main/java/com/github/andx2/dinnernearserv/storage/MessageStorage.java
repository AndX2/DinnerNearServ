package com.github.andx2.dinnernearserv.storage;

import com.github.andx2.dinnernearserv.pojo.MessageStub;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class MessageStorage {

    private static MessageStorage instance;
    private String storeType;
    private Dao<MessageStub, String> messageDao;
    private JdbcPooledConnectionSource connectionSource;
    public static final String H2 = "h2";

    private MessageStorage() {
    }
/*
    public MessageStorage(String storeType, Dao<MessageStub, String> messageDao) {
        this.storeType = storeType;
        this.messageDao = messageDao;
    }*/

    public synchronized static MessageStorage getInstance(String storeType) throws SQLException, IllegalArgumentException {
        if (instance == null) {
            switch (storeType) {
                case H2:
                    initH2storage(storeType);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        if (storeType.equals(instance.getStoreType())) {
            return instance;
        } else throw new IllegalArgumentException();

    }

    private static void initH2storage(String type) throws SQLException {
        instance = new MessageStorage();
        instance.storeType = type;
        // create a connection source to our database
        String databaseUrl = "jdbc:h2:mem:account";
        instance.connectionSource = new JdbcPooledConnectionSource(databaseUrl);
        instance.connectionSource.setMaxConnectionAgeMillis(5 * 60 * 1000);
        // instantiate the DAO to handle MessageStub with String id
        instance.messageDao = DaoManager.createDao(instance.connectionSource, MessageStub.class);

        // if you need to create the 'messages' table make this call
        TableUtils.createTableIfNotExists(instance.connectionSource, MessageStub.class);



/*// create an instance of MessageStub
            String name = "Jim Smith";
            MessageStub messageStub = new MessageStub(1L, name, "_description");

// persist the message object to the database
            dao.create(messageStub);

// retrieve the message
            MessageStub message2 = dao.queryForId("1");
// show its password
            System.out.println("Message: " + message2.getDescription());

// close the connection source
            source.close();*/


    }

    public String getStoreType() {
        return this.storeType;
    }

    public void closeConnection() throws SQLException {
        instance.connectionSource.close();
    }

    public MessageStub findById(Long id) throws SQLException {
        return instance.messageDao.queryForId(id + "");
    }

    public List<MessageStub> findAll() throws SQLException {
        return instance.messageDao.queryForAll();
    }

    public int add(MessageStub messageStub) throws SQLException {
        return instance.messageDao.create(messageStub);
    }

    public int update(MessageStub messageStub) throws SQLException {
        return instance.messageDao.update(messageStub);
    }

    public int delete(Long id) throws SQLException {
        return instance.messageDao.deleteById(id + "");
    }


}
