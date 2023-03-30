package dev.selec.backend.spring.kafka.usercartproducer;

import dev.selec.backend.spring.kafka.usercartproducer.model.UserCart;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.Executors;

@SpringBootApplication
public class UsercartProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercartProducerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, UserCart> kafkaTemplate) {
        return args -> Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                kafkaTemplate.send("user-carts", UserCart.createRandom());
            }
        });
    }

}
