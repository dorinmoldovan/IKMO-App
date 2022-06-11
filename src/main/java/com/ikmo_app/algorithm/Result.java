package com.ikmo_app.algorithm;

import com.ikmo_app.model.Kangaroo;

public class Result {

    private String logs;
    private Kangaroo globalBest;
    private Double runningTime;

    public Result() {
        this.logs = "";
    }

    public Result(String logs, Kangaroo globalBest, Double runningTime) {
        this.logs = logs;
        this.globalBest = globalBest;
        this.runningTime = runningTime;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public Kangaroo getGlobalBest() {
        return globalBest;
    }

    public void setGlobalBest(Kangaroo globalBest) {
        this.globalBest = globalBest;
    }

    public Double getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Double runningTime) {
        this.runningTime = runningTime;
    }
}
