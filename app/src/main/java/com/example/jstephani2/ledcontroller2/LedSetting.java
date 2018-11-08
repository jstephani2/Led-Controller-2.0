package com.example.jstephani2.ledcontroller2;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LedSetting extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private String name;
    private String code;
    private String[] setting_names;
    private int[] setting_vals;

    public LedSetting (String name, String code, String[] setting_names, int[] setting_vals, String id){
        this.name = name;
        this.code = code;
        this.setting_names = setting_names;
        this.setting_vals = setting_vals;
        this.id = id;
        //this.dbID = dbID;
    }

    public static void loadFromDatabase() {

    }

    public void saveToDatabase() {

    }

    public int getSettingValByName(String name) {
        int pos = -1;
        for (int i = 0; i < setting_names.length; i++) {
            if (setting_names[i].equals(name)) {
                pos = i;
                i = setting_names.length;
            }
        }
        if (pos == -1) {
            return -1;
        }
        return setting_vals[pos];
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

    public String[] getSetting_names() {
        return setting_names;
    }

    public void setSetting_names(String[] setting_names) {
        this.setting_names = setting_names;
    }

    public int[] getSetting_vals() {
        return setting_vals;
    }

    public void setSetting_vals(int[] setting_vals) {
        this.setting_vals = setting_vals;
    }
}
