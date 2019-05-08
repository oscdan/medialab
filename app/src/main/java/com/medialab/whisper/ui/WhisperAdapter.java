package com.medialab.whisper.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medialab.whisper.R;
import com.medialab.whisper.entities.Whisper;

import java.util.List;

public class WhisperAdapter extends RecyclerView.Adapter<WhisperAdapter.ViewHolder> {
    private final List<Whisper> mWhispers;
    private final Context context;

    public WhisperAdapter(Context context, List<Whisper> mWhispers){
        this.mWhispers = mWhispers;
        this.context = context;
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
        Whisper whisper = mWhispers.get(position);
        Glide.with(context)
                .load(whisper.getUrl())
                .into(holder.imageView);

        holder.textView.setText(whisper.getText());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.whisper_image);
            textView = itemView.findViewById(R.id.whisper_text);
        }
    }

    @Override
    public int getItemCount() {
        return mWhispers.size();
    }
}
