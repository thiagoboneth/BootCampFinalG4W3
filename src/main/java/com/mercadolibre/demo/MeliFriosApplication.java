package com.mercadolibre.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MeliFriosApplication  /*implements CommandLineRunner */{

    public static void main(String[] args) {
        SpringApplication.run(MeliFriosApplication.class, args);
    }

/*    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("123"));
    }*/
}
