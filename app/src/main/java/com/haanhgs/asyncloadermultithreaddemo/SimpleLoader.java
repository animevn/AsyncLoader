package com.haanhgs.asyncloadermultithreaddemo;

import android.content.Context;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class SimpleLoader extends AsyncTaskLoader<Integer>{

    interface Listener{
        void onMaxCreate(int duration);
        void onDataChanged(int progress);
    }

    private int seed;
    private int duration;
    private Listener listener;

    public SimpleLoader(@NonNull Context context) {
        super(context);
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Random random = new Random();
        duration = (random.nextInt(10) + 1) * seed;
        listener.onMaxCreate(duration);
        forceLoad();
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        int count = 0;
        while (count < duration){
            try{
                Thread.sleep(duration/10);
                count += duration/10;
                listener.onDataChanged(count);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return duration;
    }
}
