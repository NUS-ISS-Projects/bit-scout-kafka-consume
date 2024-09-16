package com.example.coin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.coin.dto.PriceUpdateDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "price-updates";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PriceUpdateDto priceUpdateDto) {
        try {
            // Convert PriceUpdateDto to JSON
            String message = objectMapper.writeValueAsString(priceUpdateDto);

            // Send JSON message to Kafka
            kafkaTemplate.send(TOPIC, message);

            // Log the message
            System.out.println("Sent message to Kafka: " + message);
        } catch (Exception e) {
            System.err.println("Error while sending message to Kafka: " + e.getMessage());
        }
    }
}
