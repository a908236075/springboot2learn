package com.xiaobo.springboot2.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class Consumer {
    public static final String brokerList = "10.7.215.130:9092";

    public static final String groupid = "group1";
    public String topic = "topic-demo";

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupid);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1000);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        return props;
    }

    public Consumer() {
        Properties props = initConfig();
        int consumerThreadNum = 3;
        for (int i = 0; i < consumerThreadNum; i++) {
            new KafkaConsumerThread(props).start();
        }
    }

    public static void main(String[] args) {
        new Consumer();
    }
}



