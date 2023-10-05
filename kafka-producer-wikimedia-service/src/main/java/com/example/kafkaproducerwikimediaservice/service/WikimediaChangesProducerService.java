package com.example.kafkaproducerwikimediaservice.service;

import com.launchdarkly.eventsource.StreamException;

public interface WikimediaChangesProducerService {

    void sendMessage() throws InterruptedException, StreamException;

}
