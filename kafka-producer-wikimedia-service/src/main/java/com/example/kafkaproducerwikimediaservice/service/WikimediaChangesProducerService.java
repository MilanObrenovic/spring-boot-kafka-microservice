package com.example.kafkaproducerwikimediaservice.service;

import com.launchdarkly.eventsource.StreamException;

public interface WikimediaChangesProducerService {

    void sendMessage(String message) throws InterruptedException, StreamException;

}
