package com.example.kafkaconsumerdatabaseservice.serviceImpl;

import com.example.kafkaconsumerdatabaseservice.model.Wikimedia;
import com.example.kafkaconsumerdatabaseservice.repository.WikimediaRepository;
import com.example.kafkaconsumerdatabaseservice.service.KafkaDatabaseConsumerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaDatabaseConsumerServiceImpl implements KafkaDatabaseConsumerService {

    private final WikimediaRepository wikimediaRepository;

    @KafkaListener(
            topics = "${spring.kafka.topicName.wikimediaRecentChange}",
            groupId = "${spring.kafka.groupId.demo}"
    )
    @Override
    public void consume(String eventMessage) {
        log.info("[RECEIVED] event message: \"%s\".".formatted(eventMessage));

        Wikimedia wikimedia = Wikimedia
                .builder()
                .wikiEventData(eventMessage)
                .build();

        wikimediaRepository.save(wikimedia);
    }

}
