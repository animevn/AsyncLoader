package com.haanhgs.asyncloader.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haanhgs.asyncloader.R;
import com.haanhgs.asyncloader.viewmodel.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;

    private ViewModel viewModel;

    @SuppressLint("SetTextI18n")
    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getData().observe(this, model -> {
            pbrThread1.setMax(model.getMaxThread1());
            pbrThread2.setMax(model.getMaxThread2());
            pbrThread3.setMax(model.getMaxThread3());
            pbrThread1.setProgress(model.getProgressThread1());
            pbrThread2.setProgress(model.getProgressThread2());
            pbrThread3.setProgress(model.getProgressThread3());

            tvThread1.setText(model.getInforThread1());
            tvThread2.setText(model.getInforThread2());
            tvThread3.setText(model.getInforThread3());

            bnThread1.setEnabled(!model.isThreadRunning1());
            bnThread2.setEnabled(!model.isThreadRunning2());
            bnThread3.setEnabled(!model.isThreadRunning3());


            textView1.setText(getResources().getString(R.string.thread1) + ": "
                    + (model.getMaxThread1() == 0 ? "" : String.valueOf(model.getMaxThread1())));

            textView2.setText(getResources().getString(R.string.thread2) + ": "
                    + (model.getMaxThread1() == 0 ? "" : String.valueOf(model.getMaxThread2())));

            textView3.setText(getResources().getString(R.string.thread3) + ": "
                    + (model.getMaxThread1() == 0 ? "" : String.valueOf(model.getMaxThread3())));
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewModel();
    }


    @OnClick({R.id.bnThread1, R.id.bnThread2, R.id.bnThread3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnThread1:
                viewModel.runTask(900, 1);
                break;
            case R.id.bnThread2:
                viewModel.runTask(1200, 2);
                break;
            case R.id.bnThread3:
                viewModel.runTask(1500, 3);
                break;
        }
    }

}

