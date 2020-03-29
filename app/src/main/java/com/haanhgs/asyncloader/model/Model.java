package com.haanhgs.asyncloader.model;

public class Model {

    private int progressThread1 = 0;
    private int progressThread2 = 0;
    private int progressThread3 = 0;

    private String inforThread1 = "";
    private String inforThread2 = "";
    private String inforThread3 = "";

    private boolean threadRunning1 = false;
    private boolean threadRunning2 = false;
    private boolean threadRunning3 = false;

    public int getProgressThread1() {
        return progressThread1;
    }

    public void setProgressThread1(int progressThread1) {
        this.progressThread1 = progressThread1;
    }

    public int getProgressThread2() {
        return progressThread2;
    }

    public void setProgressThread2(int progressThread2) {
        this.progressThread2 = progressThread2;
    }

    public int getProgressThread3() {
        return progressThread3;
    }

    public void setProgressThread3(int progressThread3) {
        this.progressThread3 = progressThread3;
    }

    public String getInforThread1() {
        return inforThread1;
    }

    public void setInforThread1(String inforThread1) {
        this.inforThread1 = inforThread1;
    }

    public String getInforThread2() {
        return inforThread2;
    }

    public void setInforThread2(String inforThread2) {
        this.inforThread2 = inforThread2;
    }

    public String getInforThread3() {
        return inforThread3;
    }

    public void setInforThread3(String inforThread3) {
        this.inforThread3 = inforThread3;
    }

    public boolean isThreadRunning1() {
        return threadRunning1;
    }

    public void setThreadRunning1(boolean threadRunning1) {
        this.threadRunning1 = threadRunning1;
    }

    public boolean isThreadRunning2() {
        return threadRunning2;
    }

    public void setThreadRunning2(boolean threadRunning2) {
        this.threadRunning2 = threadRunning2;
    }

    public boolean isThreadRunning3() {
        return threadRunning3;
    }

    public void setThreadRunning3(boolean threadRunning3) {
        this.threadRunning3 = threadRunning3;
    }
}
