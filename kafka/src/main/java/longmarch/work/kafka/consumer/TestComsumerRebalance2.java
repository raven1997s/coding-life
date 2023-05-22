package longmarch.work.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 * date: 2023/5/17 17:08
 * 分区再均衡测试
 * @author longjiaocao
 */
@Slf4j
public class TestComsumerRebalance2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        //指定kafka服务器地址 如果是集群可以指定多个  但是就算只指定一个他也会去集群环境下寻找其他的节点地 址
        properties.setProperty("bootstrap.servers", "longmarch.work:9092");
        //key序列化器
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        //value序列化器
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", "1111");
        //设置不自动提交
//        当enable.auto.commit设置为true时，消费者会自动定期提交当前消费的偏移量。
//        提交的频率由auto.commit.interval.ms参数指定，默认为5000毫秒（5秒）。
//        这意味着每隔一定时间，消费者会自动将当前消费的偏移量提交给Kafka
//        以确保消费者在重新启动后能够从上次提交的偏移量处继续消费消息。
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //消费者订阅topic 并注册一个均衡监听器，再进行rebalance的时候 进行同步提交偏移量
        kafkaConsumer.subscribe(Collections.singletonList("demo-topic"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("之前");
                kafkaConsumer.commitSync();
                for (TopicPartition partition : collection) {
                    System.out.println("分区信息:" + partition.partition());
                }
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println("之后");

                for (TopicPartition partition : collection) {
                    System.out.println("新分配的分区信息:" + partition.partition());
                }
            }
        });

        Map<TopicPartition, OffsetAndMetadata> offsetAndMetadataMap = new HashMap<>();
        offsetAndMetadataMap.put(new TopicPartition("demo-topic", 0), new OffsetAndMetadata(0,"UserId"));
        // 通常情况下异步提交偏移量
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(500);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("消息所在分区:[" + record.partition() + "]----消息的偏移量:[" + record.offset() + "]----key:[" + record.key() + "]----value:[" + record.value() + "]");
                }
//                kafkaConsumer.commitAsync();
//                kafkaConsumer.commitSync(offsetAndMetadataMap);
            }
        } catch (Exception e) {
//        } finally {
//            try {
//                // 出现异常的情况下同步提交偏移量 例如消费者数量变更
//                kafkaConsumer.commitSync();
//            } catch (Exception e) {
//            } finally {
//                kafkaConsumer.close();
//            }
        }
    }
}