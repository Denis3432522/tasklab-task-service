package com.tasklab.taskservice.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Slf4j
public class CarController {

//    private final CarRepository carRepository;
//
//    private final TransactionTemplate template;
//
//    @PostMapping("/test")
//    public void test(@RequestParam String name) {
//
//        Car car = Car.builder()
//                .name("name1")
//                .surname("surname2")
//                .build();
//        log.info("CREATED");
//        UUID id = step1(car);
//
//        log.info("PERSISTED");
//        Car car1 = template.execute((s) -> step2(id));
//        log.info("LOADED [equals:{}]", car == car1);
//        log.info("CAR: {}", car);
//        log.info("CAR1: {}", car1);
//
//        step3(name, car1.getId());
////        template.execute((s) -> {
////            step3(name, car1.getId());
////            return "s";
////        });
//        log.info("UPDaTED");
//        Car car2 = step4(id);
//
//        log.info("LOADED1 [equals:{}]", car1 == car2);
//    }
//
//    public UUID step1(Car car) {
//        return carRepository.save(car).getId();
//    }
//
//    public Car step2(UUID id) {
//        return carRepository.findById(id).orElseThrow();
//    }
//
//    public void step3(String name, UUID id) {
//        carRepository.findById(id)
//                .orElseThrow()
//                .setName(name);
//    }
//
//    public Car step4(UUID id) {
//        return carRepository.findById(id)
//                .orElseThrow();
//    }
}
