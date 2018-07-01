package com.enigmasoftltd.android.prod.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enigmasoftltd.android.prod.journalapp.data.AppExecutors;
import com.enigmasoftltd.android.prod.journalapp.data.local.db.AppDatabase;
import com.enigmasoftltd.android.prod.journalapp.data.model.db.Post;

import java.util.Date;

public class AddPostActivity extends AppCompatActivity {

    // required for logging
    private static final String TAG = AddPostActivity.class.getSimpleName();
    // Rotation related constant
    public static final String INSTANCE_POST_ID ="instancePostId";
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_POST_ID = -1;

    public static final String EXTRA_POST_ID = "extraPostId";

    // Views
    EditText mTitle;
    EditText mPost;
    Button mSave;

    // To save the instance state whenever there is device rotation
    private int mPostId = DEFAULT_POST_ID;

    // Database variable
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        // Entry point to the app views and database
        initViews();

        // Initialize the database variable
        mDb = AppDatabase.getsInstance(getApplicationContext());

        // Save Instance state
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_POST_ID)){
            mPostId = savedInstanceState.getInt(INSTANCE_POST_ID, DEFAULT_POST_ID);
        }

        // Grab and use the post id to update journal entry
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_POST_ID)){
            mPostId = intent.getIntExtra(EXTRA_POST_ID, DEFAULT_POST_ID);
            AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                @Override
                public void run() {
                    final Post post = mDb.postDao().loadPostById(mPostId);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUI(post);
                        }
                    });
                }
            });
        }
    }

    private void updateUI(Post post) {
        if (post == null){
            return;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_POST_ID, mPostId);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {

        // Title EditText
        mTitle = findViewById(R.id.title_et);

        // Post Edit text
        mPost =  findViewById(R.id.body_et);

        // Intialize the save Button and add click listener
        mSave = findViewById(R.id.btn_save);

        // Button listener
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the Save to database routine
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked(){
        String title = mTitle.getText().toString().trim();
        String post = mPost.getText().toString().trim();
        Date date = new Date();

        final Post mPostEntry = new Post(title, post, date);
        AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                // Insert data into the database using the DAO interface
                mDb.postDao().insertPost(mPostEntry);
                finish();
            }
        });


    }

}
