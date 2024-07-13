package com.codingshuttle.homework1.homework1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env",havingValue = "Chocolate")
public class Chocolate implements Frosting, Syrup{

    public String getFrostingType(){
        return "Frosting required Chocolate";
    }

    @Override
    public String getSyrupType() {
        return "Syrup Type Chocolate";
    }
}
