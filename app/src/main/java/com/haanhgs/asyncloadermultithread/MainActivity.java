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
import android.widget.Toast;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Integer> {

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

        initViews();

        if (savedInstanceState != null){
            pbrThread1.setIndeterminate(savedInstanceState.getBoolean("thr1pbr"));
            bnThread1.setEnabled(savedInstanceState.getBoolean("thr1bn"));
            tvThread1.setText(savedInstanceState.getString("thr1text"));
        }

        if (LoaderManager.getInstance(this).getLoader(1) != null){
            LoaderManager.getInstance(this).initLoader(1, null, this);
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("thr1pbr", pbrThread1.isIndeterminate());
        outState.putBoolean("thr1bn", bnThread1.isEnabled());
        outState.putString("thr1text", tvThread1.getText().toString());
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        bnThread1.setEnabled(false);
        int seed = 0;
        if (args != null){
            seed = args.getInt("thread1");
        }
        return new SimpleLoader(this, seed);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if (data != null){
            tvThread1.setText(MessageFormat.format(
                    "The thread " +
                    "finishes " +
                    "in " +
                    "{0} " +
                    "milisecs", data));
            pbrThread1.setIndeterminate(false);
            bnThread1.setEnabled(true);
            LoaderManager.getInstance(this).destroyLoader(1);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {
        Toast.makeText(this, "reset", Toast.LENGTH_LONG).show();
    }
}

