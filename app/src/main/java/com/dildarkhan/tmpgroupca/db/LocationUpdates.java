package com.dildarkhan.tmpgroupca.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class LocationUpdates implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userPhone")
    private String userPhone;
    @ColumnInfo(name = "uuid")
    private String uuid;
    @ColumnInfo(name = "timeCreated")
    private String timeCreated;
    @ColumnInfo(name = "timeUpdated")
    private String timeUpdated;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "accuracyMode")
    private String accuracyMode;
    @ColumnInfo(name = "intervalPeriod")
    private String intervalPeriod;
    @ColumnInfo(name = "field1")
    private String field1;
    @ColumnInfo(name = "field2")
    private String field2;


    public LocationUpdates() {
    }

    public LocationUpdates(String userPhone, String uuid, String timeCreated, String timeUpdated, String latitude, String longitude, String accuracyMode, String intervalPeriod, String field1, String field2) {

        this.userPhone = userPhone;
        this.uuid = uuid;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracyMode = accuracyMode;
        this.intervalPeriod = intervalPeriod;
        this.field1 = field1;
        this.field2 = field2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAccuracyMode() {
        return accuracyMode;
    }

    public void setAccuracyMode(String accuracyMode) {
        this.accuracyMode = accuracyMode;
    }

    public String getIntervalPeriod() {
        return intervalPeriod;
    }

    public void setIntervalPeriod(String intervalPeriod) {
        this.intervalPeriod = intervalPeriod;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }


}
