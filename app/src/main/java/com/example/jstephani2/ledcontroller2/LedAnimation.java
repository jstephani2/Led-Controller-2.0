package com.example.jstephani2.ledcontroller2;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LedAnimation extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private String name;
    private String code;
    private String setting_names;
    private String setting_vals;

    public LedAnimation () {
        this.name = "";
        this.code = "";
        this.setting_names = "";
        this.setting_vals = "";
    }

    public LedAnimation (String name, String code, String setting_names, String setting_vals){
        this.name = name;
        this.code = code;
        this.setting_names = setting_names;
        this.setting_vals = setting_vals;
    }

    public LedAnimation (String name, String code, String setting_names, String setting_vals, String id){
        this.name = name;
        this.code = code;
        this.setting_names = setting_names;
        this.setting_vals = setting_vals;
        this.id = id;
        //this.dbID = dbID;
    }

    public void loadFromDatabase() {

    }

    public void saveToDatabase() {

    }

    public int getSettingValByName(String name) {
        int pos = -1;
        String[] namesArr = setting_names.split(",");
        String[] valsArr = setting_vals.split(",");
        for (int i = 0; i < namesArr.length; i++) {
            if (namesArr[i].equals(name)) {
                pos = i;
                i = namesArr.length;
            }
        }
        if (pos == -1) {
            return -1;
        }
        return Integer.parseInt(valsArr[pos]);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetting_names() {
        return setting_names;
    }

    public void setSetting_names(String setting_names) {
        this.setting_names = setting_names;
    }

    public String getSetting_vals() {
        return setting_vals;
    }

    public void setSetting_vals(String setting_vals) {
        this.setting_vals = setting_vals;
    }

    public String getId() {
        return id;
    }
}
