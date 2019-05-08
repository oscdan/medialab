package com.medialab.whisper.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medialab.whisper.R;
import com.medialab.whisper.RepliesActivity;
import com.medialab.whisper.entities.Whisper;
import com.medialab.whisper.utilities.Constants;

import java.util.List;

public class WhisperAdapter extends RecyclerView.Adapter<WhisperAdapter.ViewHolder> {
    private final List<Whisper> mWhispers;
    private final Context context;
    private boolean areReplies;

    public WhisperAdapter(Context context, List<Whisper> mWhispers, boolean areReplies){
        this.mWhispers = mWhispers;
        this.context = context;
        this.areReplies = areReplies;
    }

    @NonNull
    @Override
    public WhisperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_whisper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Whisper whisper = mWhispers.get(position);
        Glide.with(context)
                .load(whisper.getUrl())
                .into(holder.imageView);

        holder.textView.setText(whisper.getText());
        holder.replies.setText(String.valueOf(whisper.getReplies()));
        holder.me2.setText(String.valueOf(whisper.getMe2()));

        if(!areReplies) {
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RepliesActivity.class);
                    intent.putExtra(Constants.WHISPER_OBJECT, whisper);
                    context.startActivity(intent);
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        ImageView imageView;
        TextView textView;
        TextView replies;
        TextView me2;


        ViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.whisper_layout);
            imageView = itemView.findViewById(R.id.whisper_image);
            textView = itemView.findViewById(R.id.whisper_text);
            replies = itemView.findViewById(R.id.whisper_replies);
            me2 = itemView.findViewById(R.id.whisper_hearted);
        }
    }

    @Override
    public int getItemCount() {
        return mWhispers.size();
    }
}
