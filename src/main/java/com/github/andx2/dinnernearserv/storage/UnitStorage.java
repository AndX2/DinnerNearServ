package com.github.andx2.dinnernearserv.storage;

import com.github.andx2.dinnernearserv.pojo.Unit;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by savos on 04.09.2016.
 */
public class UnitStorage {

    private static UnitStorage instance;
    private String storeType;
    private Dao<Unit, String> unitDao;
    private ConnectionSource connectionSource;
    public static final String H2 = "h2";

    //region Constructors
    private UnitStorage() {
    }
    //endregion

    public synchronized static UnitStorage getInstance(String storeType) throws SQLException, IllegalArgumentException {
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
        instance = new UnitStorage();
        instance.storeType = type;
        // create a connection source to our database
        String databaseUrl = "jdbc:h2:mem:account";
        instance.connectionSource = new JdbcConnectionSource(databaseUrl);
//        instance.connectionSource.setMaxConnectionAgeMillis(3000);

        // instantiate the DAO to handle Unit with String id
        instance.unitDao = DaoManager.createDao(instance.connectionSource, Unit.class);

        // if you need to create the 'unit' table make this call
        TableUtils.createTableIfNotExists(instance.connectionSource, Unit.class);

    }

    public void closeConnection() throws SQLException {
        instance.connectionSource.close();
    }

    public Long add(Unit unit) throws SQLException {
        instance.unitDao.create(unit);
        return unit.getId();
    }

    public List<Unit> findAll() throws SQLException {
        return instance.unitDao.queryForAll();
    }

    public Unit findById(Long id) throws SQLException {
        return instance.unitDao.queryForId(id + "");
    }

    public Long update(Unit unit) throws SQLException {
        instance.unitDao.update(unit);
        return unit.getId();
    }

    public Unit delete(Long id) throws SQLException {
        Unit unit = instance.unitDao.queryForId(id + "");
        if (unit != null) instance.unitDao.deleteById(id + "");
        return unit;
    }


    //region Getters/Setters
    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
    //endregion
}
