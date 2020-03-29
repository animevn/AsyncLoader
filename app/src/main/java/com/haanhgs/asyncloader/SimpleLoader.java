package com.haanhgs.asyncloader;

import android.content.Context;
import android.widget.ProgressBar;
import java.lang.ref.WeakReference;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class SimpleLoader extends AsyncTaskLoader<Integer> {

    private final WeakReference<ProgressBar> pbr;
    private final int seed;


    public SimpleLoader(@NonNull Context context, ProgressBar pbr, int seed) {
        super(context);
        this.seed = seed;
        this.pbr = new WeakReference<>(pbr);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        Random random = new Random();
        int s = (random.nextInt(10) + 1) * seed;
        pbr.get().setMax(s);
        int count = 0;
        while (count < s){
            try{
                Thread.sleep(s/10);
                count += s/10;
                pbr.get().setProgress(count);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return s;
    }
}
