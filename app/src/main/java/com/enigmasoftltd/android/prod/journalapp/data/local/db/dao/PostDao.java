package com.enigmasoftltd.android.prod.journalapp.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.enigmasoftltd.android.prod.journalapp.data.model.db.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM post ORDER BY id")
    List<Post> loadAllPosts();

    @Insert
    void insertPost(Post post);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePost(Post post);

    @Delete
    void deletePost(Post post);

    @Query("SELECT * FROM Post WHERE id = :id")
    Post loadPostById(int id);
}
