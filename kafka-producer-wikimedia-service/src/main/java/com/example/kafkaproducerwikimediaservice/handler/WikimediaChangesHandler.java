package com.example.kafkaproducerwikimediaservice.handler;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@AllArgsConstructor
public class WikimediaChangesHandler implements BackgroundEventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String wikimediaRecentChangeTopic;

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        log.info("[EVENT] data: \"%s\".".formatted(messageEvent.getData()));

        kafkaTemplate.send(
                wikimediaRecentChangeTopic,
                messageEvent.getData()
        );
    }

    @Override
    public void onComment(String comment) throws Exception {

    }

    @Override
    public void onError(Throwable t) {

    }

}
