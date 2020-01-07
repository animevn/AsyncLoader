package com.haanhgs.asyncloadermultithread;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.MessageFormat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Integer> {

    @BindView(R.id.tvThread1)
    TextView tvThread1;
    @BindView(R.id.pbrThread1)
    ProgressBar pbrThread1;
    @BindView(R.id.bnThread1)
    Button bnThread1;
    @BindView(R.id.tvThread2)
    TextView tvThread2;
    @BindView(R.id.pbrThread2)
    ProgressBar pbrThread2;
    @BindView(R.id.bnThread2)
    Button bnThread2;
    @BindView(R.id.tvThread3)
    TextView tvThread3;
    @BindView(R.id.pbrThread3)
    ProgressBar pbrThread3;
    @BindView(R.id.bnThread3)
    Button bnThread3;

    private Loader<Integer> loader1;
    private Loader<Integer> loader2;
    private Loader<Integer> loader3;

    private void initLoaders(){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initLoaders();
    }

    private void handleButtonThread1(){
        Bundle bundle = new Bundle();
        bundle.putInt("thread1", 500);
        LoaderManager.getInstance(this).restartLoader(1, bundle, this);
        tvThread1.setText(R.string.tvThreadRunning);
    }

    private void handleButtonThread2(){
        Bundle bundle = new Bundle();
        bundle.putInt("thread2", 600);
        LoaderManager.getInstance(this).restartLoader(2, bundle, this);
        tvThread2.setText(R.string.tvThreadRunning);
    }

    private void handleButtonThread3(){
        Bundle bundle = new Bundle();
        bundle.putInt("thread3", 900);
        LoaderManager.getInstance(this).restartLoader(3, bundle, this);
        tvThread3.setText(R.string.tvThreadRunning);
    }

    @OnClick({R.id.bnThread1, R.id.bnThread2, R.id.bnThread3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnThread1:
                handleButtonThread1();
                break;
            case R.id.bnThread2:
                handleButtonThread2();
                break;
            case R.id.bnThread3:
                handleButtonThread3();
                break;
        }
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {

        if (id == 1){
            bnThread1.setEnabled(false);
            int seed = 0;
            if (args != null){
                seed = args.getInt("thread1");
            }
            loader1 = new SimpleLoader(MainActivity.this, pbrThread1, seed);
            return loader1;
        }else if (id == 2){
            bnThread2.setEnabled(false);
            int seed = 0;
            if (args != null){
                seed = args.getInt("thread1");
            }
            loader2 = new SimpleLoader(MainActivity.this, pbrThread2, seed);
            return loader2;
        }else {
            bnThread3.setEnabled(false);
            int seed = 0;
            if (args != null){
                seed = args.getInt("thread1");
            }
            loader3 = new SimpleLoader(MainActivity.this, pbrThread3, seed);
            return loader3;
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if (loader == loader1){
            bnThread1.setEnabled(true);
            tvThread1.setText(MessageFormat.format("The thread finishes in {0} milisecs", data));
            LoaderManager.getInstance(this).destroyLoader(1);
        }else if (loader == loader2){
            bnThread2.setEnabled(true);
            tvThread2.setText(MessageFormat.format("The thread finishes in {0} milisecs", data));
            LoaderManager.getInstance(this).destroyLoader(2);
        }else if (loader == loader3){
            bnThread3.setEnabled(true);
            tvThread3.setText(MessageFormat.format("The thread finishes in {0} milisecs", data));
            LoaderManager.getInstance(this).destroyLoader(3);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {}
}

