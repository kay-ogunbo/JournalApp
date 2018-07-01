package com.enigmasoftltd.android.prod.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enigmasoftltd.android.prod.journalapp.data.AppExecutors;
import com.enigmasoftltd.android.prod.journalapp.data.local.db.AppDatabase;
import com.enigmasoftltd.android.prod.journalapp.data.model.db.Post;
import com.enigmasoftltd.android.prod.journalapp.view.adapter.PostAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostAdapter.ItemClickListener {

    private FloatingActionButton fab;
    private RecyclerView mRecyclerView;
    private PostAdapter mPostAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // RecyclerView
        mRecyclerView = findViewById(R.id.recyclerViewPosts);
        // Attach recycler to a layout view manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter and attach to Recycler
        mPostAdapter = new PostAdapter(this, this);
        mRecyclerView.setAdapter(mPostAdapter);

        // add item divider
//        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(decoration);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start Activity intent
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivity(intent);

            }
        });
        mDb = AppDatabase.getsInstance(getApplicationContext());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Post> posts = mDb.postDao().loadAllPosts();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPostAdapter.setTasks(posts);
                    }
                });

            }
        });
    }

    @Override
    public void onItemClickListener(int clickedItemIndex) {

    }
}
