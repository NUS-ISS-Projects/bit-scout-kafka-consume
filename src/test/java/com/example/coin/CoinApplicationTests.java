package com.example.coin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.coin.service.KafkaProducerService;

@SpringBootTest
@EnableAutoConfiguration(exclude = { KafkaAutoConfiguration.class })
class CoinApplicationTests {

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @Test
    void contextLoads() {
    }

}
