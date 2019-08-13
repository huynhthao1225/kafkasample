package com.example.kafka.producer;

import com.example.kafka.KafkaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class KafkaProducer implements KafkaBase {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void start() {

        logger.info("Start sending...");
        kafkaTemplate.send(topic, "My first kafka Message");

    }
}
