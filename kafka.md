# Orchestra Kafka

## Overview
Orchestra kafka java library provides a set of tools to work with kafka
## Installation
```gradle 
implementation 'com.orchestra.kafka:orchestra-kafka-starter :1.0.0'
```
## Configuration
```yaml
orchestra:
  kafka-config:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: orchestra
      auto-offset-reset: earliest
      enable-auto-commit: true
      auto-commit-interval: 1000
    producer:
      acks: all
```
## Usage
### Producer
```java
import com.orchestra.kafka.producer.Producer;
public class ProducerSample {
    public static void main(String[] args) {
        Producer<String, String> producer = Producer.getInstance("your-topic-name");
        producer.send("key", "message");
        producer.close();
    }
}
```
### Consumer
```java
import com.orchestra.kafka.consumer.Consumer;
import com.orchestra.kafka.consumer.Record;
public class ConsumerSample {
    public static void main(String[] args) {
        Consumer<String, String> consumer = Consumer.getInstance("your-topic-name");
        consumer.subscribe();
        Record<String, String> record = consumer.poll();
        System.out.println("Received message: " + record.value());
        consumer.close();
    }
}
```