package com.example.jstephani2.ledcontroller2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LedAnimation extends RealmObject implements Parcelable {
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

    public LedAnimation ( String id, String name, String code, String setting_names, String setting_vals){
        this.name = name;
        this.code = code;
        this.setting_names = setting_names;
        this.setting_vals = setting_vals;
        this.id = id;
        //this.dbID = dbID;
    }

    protected LedAnimation(Parcel in) {
        id = in.readString();
        name = in.readString();
        code = in.readString();
        setting_names = in.readString();
        setting_vals = in.readString();
    }

    public static final Creator<LedAnimation> CREATOR = new Creator<LedAnimation>() {
        @Override
        public LedAnimation createFromParcel(Parcel in) {
            return new LedAnimation(in.readString(), in.readString(), in.readString(), in.readString(), in.readString() );
        }

        @Override
        public LedAnimation[] newArray(int size) {
            return new LedAnimation[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(code);
        dest.writeString(setting_names);
        dest.writeString(setting_vals);
    }
}
