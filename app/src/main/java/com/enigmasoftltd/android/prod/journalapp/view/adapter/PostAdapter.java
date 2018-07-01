package com.enigmasoftltd.android.prod.journalapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enigmasoftltd.android.prod.journalapp.R;
import com.enigmasoftltd.android.prod.journalapp.data.model.db.Post;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.DataViewHolder> {

    // Post list and context
    private List<Post> mPostEntries;
    private Context mContext;
    // Date format and formatter
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private SimpleDateFormat dateFormat =  new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    // Item Click Listener
    final private ItemClickListener mItemClickListener;


    public PostAdapter(Context context, ItemClickListener listener){
        this.mContext = context;
        this.mItemClickListener = listener;
    }

    // click Listener interface
    public interface ItemClickListener {
        void onItemClickListener(int clickedItemIndex);
    }

    @NonNull
    @Override
    public PostAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_row, viewGroup, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.DataViewHolder dataViewHolder, int position) {
        Post postEntry = mPostEntries.get(position);

        dataViewHolder.postTitleView.setText(postEntry.getPostTitle());
        dataViewHolder.postBodyView.setText(postEntry.getPostBody());

        String postedAt = dateFormat.format(postEntry.getPostedAt());
        dataViewHolder.postDateView.setText(postedAt);

    }

    @Override
    public int getItemCount() {
        if (mPostEntries == null){
            return 0;
        }
        return mPostEntries.size();
    }

    public void setTasks(List<Post> postEntries) {
        mPostEntries = postEntries;
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // Views
        TextView postTitleView;
        TextView postBodyView;
        TextView postDateView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            // Intialize the Views
            postTitleView = itemView.findViewById(R.id.list_title_tv);
            postBodyView = itemView.findViewById(R.id.list_body_tv);
            postDateView =  itemView.findViewById(R.id.list_date_tv);

            // Click Listener
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPositionId = mPostEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(clickedPositionId);

        }

    }
}
