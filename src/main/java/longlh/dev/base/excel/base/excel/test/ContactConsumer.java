package longlh.dev.base.excel.base.excel.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContactConsumer {
    @KafkaListener(topics = "contact", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received message from 'contact' topic: " + message);
    }
}
