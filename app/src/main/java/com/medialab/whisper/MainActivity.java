package com.medialab.whisper;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.medialab.whisper.entities.Whisper;
import com.medialab.whisper.ui.WhisperAdapter;
import com.medialab.whisper.viewmodel.WhispersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Whisper> whisperList = new ArrayList<>();
    WhisperAdapter whisperAdapter;
    WhispersViewModel whispersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {

        final Observer<List<Whisper>> whisperObserver =
                new Observer<List<Whisper>>() {
                    @Override
                    public void onChanged(@Nullable List<Whisper> whispers) {
                        whisperList.clear();
                        whisperList.addAll(whispers);

                        if (whisperAdapter == null) {
                            whisperAdapter = new WhisperAdapter(MainActivity.this, whisperList, false);
                            recyclerView.setAdapter(whisperAdapter);
                        } else {
                            whisperAdapter.notifyDataSetChanged();
                        }
                    }
                };

        whispersViewModel = ViewModelProviders.of(this)
                .get(WhispersViewModel.class);
        whispersViewModel.getWhispers().observe(this, whisperObserver);

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}
