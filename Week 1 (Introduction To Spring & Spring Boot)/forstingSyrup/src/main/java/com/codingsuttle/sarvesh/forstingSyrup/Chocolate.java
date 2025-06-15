package com.codingsuttle.sarvesh.forstingSyrup;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env", havingValue = "Chocolate")
public class Chocolate implements Frosting,Syrup {

    public String getFrostingType() {
        return "Frosting req Chocolate";
    }

    public String getSyrupType() {
        return "Syrup type chocolate";
    }
}
