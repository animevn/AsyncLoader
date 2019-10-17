package com.haanhgs.asyncloadermultithread;

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
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Integer> {

    private Button bnThread1;
    private Button bnThread2;
    private Button bnThread3;
    private ProgressBar pbrThread1;
    private ProgressBar pbrThread2;
    private ProgressBar pbrThread3;
    private TextView tvThread1;
    private TextView tvThread2;
    private TextView tvThread3;


    private void initViews(){
        bnThread1 = findViewById(R.id.bnThread1);
        bnThread2 = findViewById(R.id.bnThread2);
        bnThread3 = findViewById(R.id.bnThread3);
        pbrThread1 = findViewById(R.id.pbrThread1);
        pbrThread2 = findViewById(R.id.pbrThread2);
        pbrThread3 = findViewById(R.id.pbrThread3);
        tvThread1 = findViewById(R.id.tvThread1);
        tvThread2 = findViewById(R.id.tvThread2);
        tvThread3 = findViewById(R.id.tvThread3);
    }

    private void handleThread1(){
        bnThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("thread1", 500);
                LoaderManager.getInstance(MainActivity.this).restartLoader(1, bundle,
                        MainActivity.this);
                tvThread1.setText(R.string.tvThreadRunning);
                pbrThread1.setIndeterminate(true);
            }
        });
    }

    private void handleThread2(){
        bnThread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("thread2", 600);
                LoaderManager.getInstance(MainActivity.this).restartLoader(2, bundle,
                        MainActivity.this);
                tvThread2.setText(R.string.tvThreadRunning);
                pbrThread2.setIndeterminate(true);
            }
        });
    }

    private void handleThread3(){
        bnThread3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("thread3", 900);
                LoaderManager.getInstance(MainActivity.this).restartLoader(3, bundle,
                        MainActivity.this);
                tvThread3.setText(R.string.tvThreadRunning);
                pbrThread3.setIndeterminate(true);
            }
        });
    }

    private void initLoader(){
        if (LoaderManager.getInstance(this).getLoader(1) != null){
            LoaderManager.getInstance(this).initLoader(1, null, this);
        }

        if (LoaderManager.getInstance(this).getLoader(2) != null){
            LoaderManager.getInstance(this).initLoader(2, null, this);
        }
        if (LoaderManager.getInstance(this).getLoader(3) != null){
            LoaderManager.getInstance(this).initLoader(3, null, this);
        }
    }

    private void reload(Bundle bundle){
        if (bundle != null){
            pbrThread1.setIndeterminate(bundle.getBoolean("thr1pbr"));
            bnThread1.setEnabled(bundle.getBoolean("thr1bn"));
            tvThread1.setText(bundle.getString("thr1text"));
            pbrThread2.setIndeterminate(bundle.getBoolean("thr2pbr"));
            bnThread2.setEnabled(bundle.getBoolean("thr2bn"));
            tvThread2.setText(bundle.getString("thr2text"));
            pbrThread3.setIndeterminate(bundle.getBoolean("thr3pbr"));
            bnThread3.setEnabled(bundle.getBoolean("thr3bn"));
            tvThread3.setText(bundle.getString("thr3text"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        reload(savedInstanceState);
        initLoader();
        handleThread1();
        handleThread2();
        handleThread3();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("thr1pbr", pbrThread1.isIndeterminate());
        outState.putBoolean("thr1bn", bnThread1.isEnabled());
        outState.putString("thr1text", tvThread1.getText().toString());
        outState.putBoolean("thr2pbr", pbrThread2.isIndeterminate());
        outState.putBoolean("thr2bn", bnThread2.isEnabled());
        outState.putString("thr2text", tvThread2.getText().toString());
        outState.putBoolean("thr3pbr", pbrThread3.isIndeterminate());
        outState.putBoolean("thr3bn", bnThread3.isEnabled());
        outState.putString("thr3text", tvThread3.getText().toString());
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
       if (id == 1){
           bnThread1.setEnabled(false);
           pbrThread1.setIndeterminate(true);
           int seed = 0;
           if (args != null){
               seed = args.getInt("thread1");
           }
           return new SimpleLoader(this, seed);
       }else if (id == 2){
           bnThread2.setEnabled(false);
           pbrThread2.setIndeterminate(true);
           int seed = 0;
           if (args != null){
               seed = args.getInt("thread2");
           }
           return new SimpleLoader(this, seed);
       }else {
           bnThread3.setEnabled(false);
           pbrThread3.setIndeterminate(true);
           int seed = 0;
           if (args != null){
               seed = args.getInt("thread3");
           }
           return new SimpleLoader(this, seed);
       }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if (data != null && loader.getId() == 1){
            tvThread1.setText(MessageFormat.format(
                    "The thread " +
                    "finishes " +
                    "in " +
                    "{0} " +
                    "milisecs", data));
            pbrThread1.setIndeterminate(false);
            bnThread1.setEnabled(true);
            LoaderManager.getInstance(this).destroyLoader(1);
        }else if (data != null && loader.getId() == 2){
            tvThread2.setText(MessageFormat.format(
                    "The thread " +
                            "finishes " +
                            "in " +
                            "{0} " +
                            "milisecs", data));
            pbrThread2.setIndeterminate(false);
            bnThread2.setEnabled(true);
            LoaderManager.getInstance(this).destroyLoader(2);
        }else {
            tvThread3.setText(MessageFormat.format(
                    "The thread " +
                            "finishes " +
                            "in " +
                            "{0} " +
                            "milisecs", data));
            pbrThread3.setIndeterminate(false);
            bnThread3.setEnabled(true);
            LoaderManager.getInstance(this).destroyLoader(3);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {
    }
}

