package com.codingshuttle.homework1.homework1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "deploy.env",havingValue = "Strawberry")
public class Strawberry  implements Frosting, Syrup{
    public String getSyrupType() {
        return "Syrup Type Strawberry";
    }

    @Override
    public String getFrostingType() {
        return "Frosting required Strawberry ";
    }
}
