package com.enigmasoftltd.android.prod.journalapp.data.model.db;

// Convert this POJO to the required Entity

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;                                 // Create an auto-generated ID key for the User object
    private String firstName;
    private String lastName;
    private String email;
    private Date createdOn;


    // add the @Ingore annotation so that room would pick the second constructor
    @Ignore
    public User(String firstName, String lastName, String email, Date createdOn){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdOn = createdOn;
    }

    public User(int uid, String firstName, String lastName, String email, Date createdOn){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdOn = createdOn;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
