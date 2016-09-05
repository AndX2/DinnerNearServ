package com.github.andx2.dinnernearserv.pojo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by savos on 27.08.2016.
 */
@DatabaseTable(tableName = "unit")
public class Unit {

    @DatabaseField(dataType = DataType.LONG, generatedId = true)
    private long id;
    @DatabaseField
    private int versionApi = 1;
    @DatabaseField(foreign = true)
    private GroupUnits group;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField
    private String address;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> phone = new ArrayList<>();
    @DatabaseField
    private String eMail;
    @DatabaseField
    private Float raiting;
    @DatabaseField
    private String photo;
    @DatabaseField
    private String avatar;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> pics = new ArrayList<>();
    @DatabaseField
    private String workingHours;
    @DatabaseField
    private String moreInformation;

    //region Constructors
    public Unit() {

    }

    public Unit(String name, String eMail) {
        this.name = name;
        this.eMail = eMail;
    }
    //endregion


    //region Getters/Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersionApi() {
        return versionApi;
    }

    public void setVersionApi(int versionApi) {
        this.versionApi = versionApi;
    }

    public GroupUnits getGroup() {
        return group;
    }

    public void setGroup(GroupUnits group) {
        this.group = group;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Float getRaiting() {
        return raiting;
    }

    public void setRaiting(Float raiting) {
        this.raiting = raiting;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ArrayList<String> getPics() {
        return pics;
    }

    public void setPics(ArrayList<String> pics) {
        this.pics = pics;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }
    //endregion

    @Override
    public String toString() {
        return String.format("Unit with fields[id=%d, firstName='%s', lastName='%s']", id, name, eMail);
    }


}
