package com.example.kafka;


import com.example.kafka.consumer.KafkaConsumer;
import com.example.kafka.producer.KafkaProducer;
import com.example.kafka.utils.RunnerEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    private Logger logger = LoggerFactory.getLogger(DemoRunner.class);
    @Override
    public void run(String... args) throws Exception {

        logger.info("args {}", args);
        String mode = args[0];
        RunnerEnum runnerEnum = RunnerEnum.valueOf(mode.toUpperCase());

        KafkaBase kafkabase = null;

        switch (runnerEnum) {
            case PRODUCER:
                kafkabase = applicationContext.getBean(KafkaProducer.class);
                break;
            default:
                break;

        }

        if (kafkabase != null) {
            kafkabase.start();
        } else {
            logger.error("I have no idea...");
            logger.error("This application can be run as Kafka CONSUMER or PRODUCER");

        }



    }
}
