package com.xiaobo.springboot2.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class RecordHandler extends Thread {

    private ConsumerRecords<String, String> records;

    public RecordHandler(ConsumerRecords<String, String> records) {
        this.records = records;
    }

    @Override
    public void run() {
        for (ConsumerRecord<String, String> record : this.records) {
            System.out.println("record key is=" + record.key() + "==================value is " + record.value());
        }
    }
}
