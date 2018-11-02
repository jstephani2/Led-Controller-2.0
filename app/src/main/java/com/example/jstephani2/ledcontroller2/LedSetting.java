package com.example.jstephani2.ledcontroller2;

public class LedSetting {
    private String name;
    private String code;
    private int setting1;
    private int dbID;

    public LedSetting (String name, String code, int setting1Val, int dbID){
        this.name = name;
        this.code = code;
        this.setting1 = setting1Val;
        this.dbID = dbID;
    }

    public static void loadFromDatabase() {

    }

    public void saveToDatabase() {

    }

    public int getSetting1() {
        return setting1;
    }

    public void setSetting1(int setting1) {
        this.setting1 = setting1;
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
}
