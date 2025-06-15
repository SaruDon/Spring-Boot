package com.codingsuttle.sarvesh.introToSpringBoot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

public class Apple {
    void eatApple(){
        System.out.println("I am Eating apple");
    }

    //calling before creating bean
    @PostConstruct
    void callThisBeforeAppleIsUsed(){
        System.out.println("Creating the apple before use");
    }

    //calling before destroy i.e before we exit
    @PreDestroy
    void callThisBeforeDestroy(){
        System.out.println("Exit apple");
    }

}
