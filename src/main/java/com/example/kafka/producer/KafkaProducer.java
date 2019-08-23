package com.example.kafka.producer;

import com.example.kafka.KafkaBase;
import com.example.kafka.producer.model.userInfo;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
@Lazy
public class KafkaProducer implements KafkaBase {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void start() {

        logger.info("Start sending...");
        byte[] data = createMessage();
        String message = new String(data);
        kafkaTemplate.send(topic, message);


    }

    private byte[] createMessage() {


        userInfo user = createUserInfo();
        userInfo user1 = createUserInfo();
        DatumWriter<userInfo> userDatumWriter = new SpecificDatumWriter<userInfo>(userInfo.class);
        userDatumWriter.setSchema(userInfo.getClassSchema());

        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    userInfo.getClassSchema(), stream);
            userDatumWriter.write(user, jsonEncoder);
            userDatumWriter.write(user1, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            logger.error("Serialization error: {}" , e.getMessage());
        }
        return data;
    }

    private userInfo createUserInfo() {

        Random r = new Random();
        int age = r.nextInt((100 - 1) + 1) + 1;

        userInfo user = new userInfo();
        user.setAge(age);
        user.setCity("Columbus");
        user.setCountry("USA");
        user.setHousenum("7758");
        user.setPhone("6145991280");
        user.setStateProvince("Ohio");
        user.setStreet("7758 Kingman place");
        user.setUsername("huynh");
        user.setZip("43035");

        return user;
    }

}
