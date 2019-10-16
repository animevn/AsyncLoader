package com.haanhgs.asyncloadermultithreaddemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Integer> {

    private Loader<Integer> loader1;
    private Button bnThread1;
    private ProgressBar pbrThread1;
    private TextView tvThread1;


    private void initViews(){
        bnThread1 = findViewById(R.id.bnThread1);
        pbrThread1 = findViewById(R.id.pbrThread1);
        tvThread1 = findViewById(R.id.tvThread1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (LoaderManager.getInstance(this).getLoader(1) != null){
            LoaderManager.getInstance(this).initLoader(1, null, this);
        }

        if (savedInstanceState != null){
            ((ProgressBar)findViewById(R.id.pbrThread1)).setProgress(savedInstanceState.getInt(
                    "pr1"));
            findViewById(R.id.bnThread1).setEnabled(savedInstanceState.getBoolean("bn1"));
        }

        initViews();

        bnThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("thread1", 500);
                LoaderManager.getInstance(MainActivity.this).restartLoader(1, bundle,
                        MainActivity.this);
                tvThread1.setText(R.string.tvThreadRunning);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pr1", ((ProgressBar)findViewById(R.id.pbrThread1)).getProgress());
        outState.putBoolean("bn1", (findViewById(R.id.bnThread1)).isEnabled());
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        bnThread1.setEnabled(false);
        int seed = 0;
        if (args != null){
            seed = args.getInt("thread1");
        }
        loader1 = new SimpleLoader(this);
        ((SimpleLoader)loader1).setSeed(seed);
        ((SimpleLoader)loader1).setListener(new SimpleLoader.Listener() {
            @Override
            public void onMaxCreate(int duration) {
                pbrThread1.setMax(duration);
                pbrThread1.setProgress(0);
            }

            @Override
            public void onDataChanged(int progress) {
                pbrThread1.setProgress(progress);
            }
        });

        return loader1;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if (loader == loader1){
            bnThread1.setEnabled(true);
            tvThread1.setText(MessageFormat.format("The thread " +
                    "finishes " +
                    "in " +
                    "{0} " +
                    "milisecs", data));
            LoaderManager.getInstance(this).destroyLoader(1);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {
        Toast.makeText(this, "reset", Toast.LENGTH_LONG).show();
    }
}
