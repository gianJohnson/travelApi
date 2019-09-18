package com.afkl.travel.exercise.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticsLabelsConfig {

    @Value("${stat.total}")
    String total;
    @Value("${stat.ok}")
    String ok;
    @Value("${stat.404}")
    String notFound;
    @Value("${stat.500}")
    String error;
    @Value("${stat.time}")
    String totalTime;
    @Value("${stat.avg}")
    String avgTime;
    @Value("${stat.max}")
    String maxTime;

    public String getTotal() {
        return total;
    }

    public String getOk() {
        return ok;
    }

    public String getNotFound() {
        return notFound;
    }

    public String getError() {
        return error;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

}

