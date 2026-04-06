package org.restaurant.qrservice;

import lombok.extern.slf4j.Slf4j;
import org.restaurant.qrservice.internal.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@Slf4j

public class QrServiceApplication implements CommandLineRunner {

    @Autowired
    QrService qrService;

    public static void main(String[] args) {
        SpringApplication.run(QrServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        while(true) {
            Scanner scanner = new Scanner(System.in);

            log.info("Enter a table number to generate QR code: ");
            Integer inputNumber;
            try {
                inputNumber = scanner.nextInt();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
            qrService.create(inputNumber);
        }
    }
}
