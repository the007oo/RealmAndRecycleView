package com.phattarapong.realmandrecycleview.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Phattarapong on 25-Jul-17.
 */

public class Student extends RealmObject {

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    @PrimaryKey
    String idNumber;
    String Name;
    String Score;


}
