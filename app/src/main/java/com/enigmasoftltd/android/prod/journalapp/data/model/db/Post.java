package com.enigmasoftltd.android.prod.journalapp.data.model.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

// Make Pojo an Entity so that the table can be created in the database
@Entity(tableName = "post")
public class Post {

    // Make id the primary key and auto-generated for consistency
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String postTitle;
    private String postBody;
    //private User firstName;
    //private User lastName;
    private Date postedAt;

    // Main construtor, annote with @Ingnore so that room database can ignore
    @Ignore
    public Post (String postTitle, String postBody, Date postedAt){
        this.postTitle = postTitle;
        this.postBody = postBody;
        //this.firstName = firstName;
        //this.lastName = lastName;
        this.postedAt = postedAt;
    }

    // Overide constructor for the room Database
    public Post (int id, String postTitle, String postBody, Date postedAt){
        this.id = id;
        this.postTitle = postTitle;
        this.postBody = postBody;
        //this.firstName = firstName;
        //this.lastName = lastName;
        this.postedAt = postedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

//    public User getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(User firstName) {
//        this.firstName = firstName;
//    }
//
//    public User getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(User lastName) {
//        this.lastName = lastName;
//    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
