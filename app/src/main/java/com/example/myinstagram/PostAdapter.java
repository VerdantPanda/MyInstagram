package com.example.myinstagram;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myinstagram.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    List<Post> mPosts;
    Context context;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = mPosts.get(i);
        Log.d("PostAdapter", String.format("item %d bound by onBindViewHolder", i));
        viewHolder.tvPostUser.setText(post.getUser().get("handle").toString());
//        Glide.with(context)
//                .load(post.getUser().get("profilePicture"))
//                .placeholder(R.color.black)
//                .into(viewHolder.ivPostUserImage);
        Glide.with(context)
                .load(post.getImage().getUrl())
                .placeholder(R.color.black)
                .into(viewHolder.ivPostImage);
        viewHolder.tvDescription.setText(post.getDescription());
//        viewHolder.tvTimeStamp.setText(post.get("createdAt").toString());

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPostUser;
        public ImageView ivPostUserImage;
        public ImageView ivComment;
        public ImageView ivLike;
        public ImageView ivSave;
        // TODO: consider eliminating unused Views
        public ImageView ivPostImage;
        public TextView tvDescription;
        public TextView tvTimeStamp;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostUser = itemView.findViewById(R.id.tvPostUser);
            ivPostUserImage = itemView.findViewById(R.id.ivPostUserImage);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivLike = itemView.findViewById(R.id.ivLike);
            ivSave = itemView.findViewById(R.id.ivSave);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }
    }


}