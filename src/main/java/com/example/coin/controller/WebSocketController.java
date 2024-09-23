package com.example.coin.controller;

import com.example.coin.dto.PriceUpdateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "price-updates", groupId = "crypto-group")
    public void listenToPriceUpdates(String message) {
        try {
            // Deserialize the JSON string into PriceUpdateDto
            PriceUpdateDto priceUpdateDto = objectMapper.readValue(message, PriceUpdateDto.class);

            // Send the PriceUpdateDto to all subscribed clients via STOMP
            messagingTemplate.convertAndSend("/topic/price-updates", priceUpdateDto);
            System.out.println("Sent PriceUpdateDto to /topic/price-updates: " + priceUpdateDto);
        } catch (Exception e) {
            System.err.println("Error deserializing message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
