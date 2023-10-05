package com.example.kafkaconsumerdatabaseservice.service;

public interface KafkaDatabaseConsumerService {

    void consume(String eventMessage);

}
