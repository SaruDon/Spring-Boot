package com.codingsuttle.sarvesh.forstingSyrup;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env", havingValue = "Strawberry")
public class Strawberry implements Frosting,Syrup{

    public String getFrostingType() {
        return "Frosting type strawberry";
    }

    public String getSyrupType() {
        return "syrup type strawberry";
    }
}
