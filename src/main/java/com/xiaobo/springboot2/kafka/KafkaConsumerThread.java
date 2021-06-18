package com.xiaobo.springboot2.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerThread extends Thread {

    private AtomicBoolean runBoolean=new AtomicBoolean(true);

    private KafkaConsumer<String, String> kafkaConsumer;

    public String topic = "topic-demo";

    public KafkaConsumerThread(Properties props) {
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (runBoolean.get()) {
                ConsumerRecords<String, String> records =
                        kafkaConsumer.poll(Duration.ofMillis(1000));
                // 版本一
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("record key is=" + record.key() + "==================value is " + record.value());
                }
                // 版本二 使用多线程处理消息
                // 处理消息模块
                ExecutorService executorService  =  new ThreadPoolExecutor(3, 3,
                        0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000),
                        new ThreadPoolExecutor.CallerRunsPolicy());
                executorService.submit(new RecordHandler(records));


//                runBoolean.set(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }


}
