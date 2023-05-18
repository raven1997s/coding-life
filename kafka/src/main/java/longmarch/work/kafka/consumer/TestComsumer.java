package longmarch.work.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Description:
 * date: 2023/5/17 17:08
 *
 * @author longjiaocao
 */
public class TestComsumer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        //指定kafka服务器地址 如果是集群可以指定多个  但是就算只指定一个他也会去集群环境下寻找其他的节点地 址
        properties.setProperty("bootstrap.servers","longmarch.work:9092");
        //key序列化器
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        //value序列化器
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());
        properties.setProperty("group.id","1111");
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("test-topic"));
        while (true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }

    }
}