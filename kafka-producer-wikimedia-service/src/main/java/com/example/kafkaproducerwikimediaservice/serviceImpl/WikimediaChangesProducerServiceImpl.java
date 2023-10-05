package com.example.kafkaproducerwikimediaservice.serviceImpl;

import com.example.kafkaproducerwikimediaservice.handler.WikimediaChangesHandler;
import com.example.kafkaproducerwikimediaservice.service.WikimediaChangesProducerService;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikimediaChangesProducerServiceImpl implements WikimediaChangesProducerService {

    @Value("${spring.kafka.topicName.wikimediaRecentChange}")
    private String wikimediaRecentChangeTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage() throws InterruptedException, StreamException {
        BackgroundEventHandler backgroundEventHandler = new WikimediaChangesHandler(
                kafkaTemplate,
                wikimediaRecentChangeTopic
        );

        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder eventSourceBuilder = new EventSource.Builder(
                URI.create(url)
        );

        BackgroundEventSource.Builder backgroundeventSourceBuilder = new BackgroundEventSource.Builder(
                backgroundEventHandler,
                eventSourceBuilder
        );

        BackgroundEventSource backgroundEventSource = backgroundeventSourceBuilder.build();
        backgroundEventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }

}
