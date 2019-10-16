package com.haanhgs.asyncloadermultithreaddemo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class SimpleLoader extends AsyncTaskLoader<Integer> {

    public SimpleLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        return null;
    }
}
