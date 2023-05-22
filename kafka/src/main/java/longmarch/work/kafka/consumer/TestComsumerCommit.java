package longmarch.work.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 * date: 2023/5/17 17:08
 * commit 自动提交偏移量与手动提交偏移量
 * @author longjiaocao
 */
public class TestComsumerCommit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        //指定kafka服务器地址 如果是集群可以指定多个  但是就算只指定一个他也会去集群环境下寻找其他的节点地 址
        properties.setProperty("bootstrap.servers", "longmarch.work:9092");
        //key序列化器
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        //value序列化器
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", "1111");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("test-topic"));

        // 通常情况下异步提交偏移量
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(500);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("消息所在分区:[" + record.partition() + "]----消息的偏移量:[" + record.offset() + "]----key:[" + record.key() + "]----value:[" + record.value() + "]");
                }
                kafkaConsumer.commitAsync();
            }
        } catch (Exception e) {
        } finally {
            try {
                // 出现异常的情况下同步提交偏移量 例如消费者数量变更
                kafkaConsumer.commitSync();
            } catch (Exception e) {
            } finally {
                kafkaConsumer.close();
            }
        }
    }
}