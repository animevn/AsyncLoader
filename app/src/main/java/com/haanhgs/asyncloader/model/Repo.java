package com.haanhgs.asyncloader.model;

import android.content.Context;
import com.haanhgs.asyncloader.R;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import androidx.lifecycle.MutableLiveData;

public class Repo {

    private Context context;
    private Model model = new Model();
    private MutableLiveData<Model> liveData = new MutableLiveData<>();
    private static final ThreadPoolExecutor executor = (ThreadPoolExecutor)
            Executors.newFixedThreadPool(3);

    public Repo(Context context) {
        this.context = context;
        model.setInforThread1(context.getResources().getString(R.string.tvThread));
        model.setInforThread2(context.getResources().getString(R.string.tvThread));
        model.setInforThread3(context.getResources().getString(R.string.tvThread));
        liveData.setValue(model);

    }

    public MutableLiveData<Model> getLiveData() {
        return liveData;
    }

    public void runTask(int seed, int thread){
        executor.execute(() -> {
            Random random = new Random();
            int s = (random.nextInt(10) + 1) * seed;
            switch (thread){
                case 1:
                    model.setMaxThread1(s);
                    model.setThreadRunning1(true);
                    model.setInforThread1(context.getResources()
                            .getString(R.string.tvThreadRunning));
                    break;
                case 2:
                    model.setMaxThread2(s);
                    model.setThreadRunning2(true);
                    model.setInforThread2(context.getResources()
                            .getString(R.string.tvThreadRunning));
                    break;
                case 3:
                    model.setMaxThread3(s);
                    model.setThreadRunning3(true);
                    model.setInforThread3(context.getResources()
                            .getString(R.string.tvThreadRunning));
                    break;
            }
            liveData.postValue(model);

            int count = 0;
            while (count < s){
                try{
                    Thread.sleep(s/10);
                    count += s/10;
                    switch (thread){
                        case 1:
                            model.setProgressThread1(count);
                            break;
                        case 2:
                            model.setProgressThread2(count);
                            break;
                        case 3:
                            model.setProgressThread3(count);
                            break;
                    }
                    liveData.postValue(model);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            switch (thread){
                case 1:
                    model.setMaxThread1(0);
                    model.setThreadRunning1(false);
                    model.setInforThread1(context.getResources()
                            .getString(R.string.tvThread));
                    model.setProgressThread1(0);
                    break;
                case 2:
                    model.setMaxThread2(0);
                    model.setThreadRunning2(false);
                    model.setInforThread2(context.getResources()
                            .getString(R.string.tvThread));
                    model.setProgressThread2(0);
                    break;
                case 3:
                    model.setMaxThread3(0);
                    model.setThreadRunning3(false);
                    model.setInforThread3(context.getResources()
                            .getString(R.string.tvThread));
                    model.setProgressThread3(0);
                    break;
            }
            liveData.postValue(model);

        });

    }

}
