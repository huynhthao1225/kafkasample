package com.example.kafka.utils;

public enum RunnerEnum {

    PRODUCER("producer"),
    CONSUMER("consumer");

    private String runner;

    RunnerEnum(String runner) {
        this.runner = runner;
    }

    public String getRunner() {
        return runner;
    }
}
