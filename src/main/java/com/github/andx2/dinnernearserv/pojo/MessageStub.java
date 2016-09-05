package com.github.andx2.dinnernearserv.pojo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "messages")
public class MessageStub {

    @DatabaseField(dataType = DataType.LONG, generatedId = true)
    private long id;
    @DatabaseField(canBeNull = false, unique = true)
    private String name;
    @DatabaseField
    private String description;

    public MessageStub(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public MessageStub(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public MessageStub() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
