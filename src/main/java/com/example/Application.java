package com.example;

import com.example.config.AmqpSender;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class Application {

    @Autowired
    private AmqpSender sender;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void test() {
        CompletableFuture.runAsync(() -> {
            long counter = 0L;
            try {
                while (true) {
                    sender.send(Long.toString(++counter), "example.route.marketing");
                    Thread.sleep(1000);

                    sender.send(Long.toString(++counter), "example.route.finance");
                    Thread.sleep(1000);

                    sender.send(Long.toString(++counter), "example.route.admin");
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
