package com.example.Voiture_service;

import com.example.Voiture_service.entities.Car;
import com.example.Voiture_service.models.Client;
import com.example.Voiture_service.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient  // Active l'enregistrement auprès d'Eureka
public class CarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }
    @Bean
    CommandLineRunner initCars(CarRepository carRepository) {
        return args -> {

            carRepository.save(new Car(
                    null,
                    "Ford",
                    "2022",
                    "12 A 2345",
                    1L   // clientId
            ));

            carRepository.save(new Car(
                    null,
                    "Renault",
                    "2000",
                    "14 R 5245",
                    2L
            ));

            carRepository.save(new Car(
                    null,
                    "Toyota",
                    "1990",
                    "34 T 6755",
                    3L
            ));

            carRepository.save(new Car(
                    null,
                    "Ford",
                    "2021",
                    "44 R 6756",
                    2L
            ));

            System.out.println("Voitures initialisées (clientId uniquement)");
        };
    }

    /**
     * Configure RestTemplate pour la communication inter-services
     * @return Instance configurée de RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Configuration des timeouts
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);  // 5 secondes pour la connexion
        requestFactory.setReadTimeout(5000);     // 5 secondes pour la lecture

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
}