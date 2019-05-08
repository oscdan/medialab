package com.medialab.whisper;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.medialab.whisper.entities.Whisper;
import com.medialab.whisper.ui.WhisperAdapter;
import com.medialab.whisper.utilities.Constants;
import com.medialab.whisper.viewmodel.WhispersViewModel;

import java.util.ArrayList;
import java.util.List;

public class RepliesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Whisper> repliesList = new ArrayList<>();
    WhisperAdapter repliesAdapter;
    WhispersViewModel repliesViewModel;
    Whisper whisper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replies);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            whisper = bundle.getParcelable(Constants.WHISPER_OBJECT);
            getSupportActionBar().setTitle(whisper.getText());
        }
        initRecyclerView();
        initViewModel();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.replies_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initViewModel() {

        final Observer<List<Whisper>> repliesObserver =
                new Observer<List<Whisper>>() {
                    @Override
                    public void onChanged(@Nullable List<Whisper> replies) {
                        repliesList.clear();
                        repliesList.addAll(replies);

                        if (repliesAdapter == null) {
                            repliesAdapter = new WhisperAdapter(RepliesActivity.this, repliesList, true);
                            recyclerView.setAdapter(repliesAdapter);
                        } else {
                            repliesAdapter.notifyDataSetChanged();
                        }
                    }
                };

        repliesViewModel = ViewModelProviders.of(this)
                .get(WhispersViewModel.class);
        repliesViewModel.getReplies(whisper.getWid())
                .observe(this, repliesObserver);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
