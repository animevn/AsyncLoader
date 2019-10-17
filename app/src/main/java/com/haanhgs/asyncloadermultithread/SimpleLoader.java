package com.haanhgs.asyncloadermultithread;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class SimpleLoader extends AsyncTaskLoader<Integer>{

    private int seed;

    public SimpleLoader(@NonNull Context context, int seed) {
        super(context);
        this.seed = seed;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        int duration = seed * 10;
        try{
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return duration;
    }
}