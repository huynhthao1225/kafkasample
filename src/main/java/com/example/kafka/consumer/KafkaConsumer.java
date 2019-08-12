package com.example.kafka.consumer;

import com.example.kafka.KafkaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class KafkaConsumer implements KafkaBase {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Override
    public void start() {
        logger.info("Starting ...");
    }
}
