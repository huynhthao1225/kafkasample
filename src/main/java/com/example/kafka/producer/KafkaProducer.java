package com.example.kafka.producer;

import com.example.kafka.KafkaBase;
import com.example.kafka.producer.model.userInfo;
import org.apache.avro.Schema;
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

@Service
@Lazy
public class KafkaProducer implements KafkaBase {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void start() {

        logger.info("Start sending...");
        byte[] data = createUserInfo();
        String message = new String(data);
        kafkaTemplate.send(topic, message);


    }

    private byte[] createUserInfo() {


        userInfo user = new userInfo();
        user.setAge(56);
        user.setCity("Columbus");
        user.setCountry("USA");
        user.setHousenum("7758");
        user.setPhone("6145991280");
        user.setStateProvince("Ohio");
        user.setStreet("7758 Kingman place");
        user.setUsername("huynh");
        user.setZip("43035");
        DatumWriter<userInfo> userDatumWriter = new SpecificDatumWriter<userInfo>(userInfo.class);
        userDatumWriter.setSchema(user.getSchema());

        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get().jsonEncoder(
                    user.getSchema(), stream);
            userDatumWriter.write(user, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            logger.error("Serialization error:" + e.getMessage());
        }
        return data;
    }
}
