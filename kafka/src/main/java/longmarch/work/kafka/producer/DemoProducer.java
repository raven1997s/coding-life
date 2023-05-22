package longmarch.work.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Description:
 * date: 2023/5/17 17:08
 *
 * @author longjiaocao
 */
public class DemoProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        //指定kafka服务器地址 如果是集群可以指定多个  但是就算只指定一个他也会去集群环境下寻找其他的节点地 址
//        properties.setProperty("bootstrap.servers", "longmarch.work:9092");
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "longmarch.work:9092");
        //key序列化器
//        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //value序列化器
//        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<String, String>("demo-topic", 0, "testKey", "hello,kafka");
        Future<RecordMetadata> send = kafkaProducer.send(stringStringProducerRecord);
        ProducerRecord<String, String> stringStringProducerRecord2 = new ProducerRecord<String, String>("test-topic", 0, "testKey", "hello,kafka");
        kafkaProducer.send(stringStringProducerRecord2);
        // 同步接收消息发送成功的返回
        RecordMetadata recordMetadata = send.get();
        System.out.println("offset:[" + recordMetadata.offset() + "] topic:[" + recordMetadata.topic() + "]");
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}