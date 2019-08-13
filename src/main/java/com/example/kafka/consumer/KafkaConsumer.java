package com.example.kafka.consumer;

import com.example.kafka.KafkaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics = "sampleTopic", groupId = "group-id")
    public void listen(String message) {
        logger.info("Received Message in group - group-id: " + message);
    }
}
