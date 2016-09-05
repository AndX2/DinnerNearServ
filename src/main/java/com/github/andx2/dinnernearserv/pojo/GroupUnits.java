package com.github.andx2.dinnernearserv.pojo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by savos on 31.08.2016.
 */
@DatabaseTable(tableName = "groups")
public class GroupUnits {

    @DatabaseField(dataType = DataType.LONG, generatedId = true)
    private long id;
    @DatabaseField
    private int version = 1;
    @ForeignCollectionField
    private ForeignCollection<Unit> units;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;

    public GroupUnits() {
    }

    //region Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public ForeignCollection<Unit> getUnits() {
        return units;
    }

    public void setUnits(ForeignCollection<Unit> units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion
}
