package com.example.kafka.producer;

import com.example.kafka.KafkaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class KafkaProducer implements KafkaBase {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Override
    public void start() {

        logger.info("Starting ...");

    }
}
