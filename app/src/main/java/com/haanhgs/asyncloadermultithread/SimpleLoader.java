package com.haanhgs.asyncloadermultithread;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class SimpleLoader extends AsyncTaskLoader<Integer>{

    private int seed;
    private int duration;

    public SimpleLoader(@NonNull Context context, int seed) {
        super(context);
        this.seed = seed;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        duration = seed * 10;
        forceLoad();
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        try{
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return duration;
    }
}