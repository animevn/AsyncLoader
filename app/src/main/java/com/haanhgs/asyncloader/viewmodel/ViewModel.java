package com.haanhgs.asyncloader.viewmodel;

import android.app.Application;
import com.haanhgs.asyncloader.model.Model;
import com.haanhgs.asyncloader.model.Repo;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private Repo repo;

    public ViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application.getApplicationContext());
    }

    public LiveData<Model> getData(){
        return repo.getLiveData();
    }

    public void runTask(int seed, int thread){
        repo.runTask(seed, thread);
    }
}
