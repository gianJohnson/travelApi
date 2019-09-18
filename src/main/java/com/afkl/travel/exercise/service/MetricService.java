package com.afkl.travel.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Configurable
public class MetricService {

    private ConcurrentMap<String, Long> statisticsMap;

    @Autowired
    StatisticsLabelsConfig statisticsLabelsConfig;

    public void statistics(int status, long elapsedTime) {

        if (statisticsMap == null)
            initStatisticMap();

        statisticsMap.put(statisticsLabelsConfig.getTotal(), statisticsMap.get(statisticsLabelsConfig.getTotal()) + 1);

        if (status == HttpStatus.OK.value()) {
            statisticsMap.put(statisticsLabelsConfig.getOk(), statisticsMap.get(statisticsLabelsConfig.getOk()) + 1);
        }
        if (status == HttpStatus.NOT_FOUND.value()) {
            statisticsMap.put(statisticsLabelsConfig.getNotFound(), statisticsMap.get(statisticsLabelsConfig.getNotFound()) + 1);

        }
        if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            statisticsMap.put(statisticsLabelsConfig.getError(), statisticsMap.get(statisticsLabelsConfig.getError()) + 1);
        }

        statisticsMap.put(statisticsLabelsConfig.getTotalTime(), statisticsMap.get(statisticsLabelsConfig.getTotalTime()) + elapsedTime);

        statisticsMap.put(statisticsLabelsConfig.getAvgTime(),
                statisticsMap.get(statisticsLabelsConfig.getAvgTime())/statisticsMap.get(statisticsLabelsConfig.getTotal()));

        if (elapsedTime > statisticsMap.get(statisticsLabelsConfig.getMaxTime()))
            statisticsMap.put(statisticsLabelsConfig.getMaxTime(), elapsedTime);
    }

    public Map getMetrics() {
        return statisticsMap;
    }

    void initStatisticMap(){
        statisticsMap = new ConcurrentHashMap<>();
        statisticsMap.put(statisticsLabelsConfig.getTotal(), new Long(0));
        statisticsMap.put(statisticsLabelsConfig.getOk(), new Long(0));
        statisticsMap.put(statisticsLabelsConfig.getNotFound(), new Long(0));
        statisticsMap.put(statisticsLabelsConfig.getError(), new Long(0));
        statisticsMap.put(statisticsLabelsConfig.getTotalTime(), new Long(0));
        statisticsMap.put(statisticsLabelsConfig.getAvgTime(), new Long(0));
        statisticsMap.put(statisticsLabelsConfig.getMaxTime(), new Long(0));
    }




}