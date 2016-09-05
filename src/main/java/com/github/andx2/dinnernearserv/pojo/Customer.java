package com.github.andx2.dinnernearserv.pojo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by savos on 02.09.2016.
 */
@DatabaseTable(tableName = "customer")
public class Customer {

    @DatabaseField(dataType = DataType.LONG, generatedId = true)
    private long id;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;

    protected Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
