package com.example.coin.service;

import com.example.coin.dto.PriceUpdateDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, PriceUpdateDto> kafkaTemplate;
    private static final String TOPIC = "price-updates";

    public KafkaProducerService(KafkaTemplate<String, PriceUpdateDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PriceUpdateDto priceUpdateDto) {
        this.kafkaTemplate.send(TOPIC, priceUpdateDto);
    }
}
