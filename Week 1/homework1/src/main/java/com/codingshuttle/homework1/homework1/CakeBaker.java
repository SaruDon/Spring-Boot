package com.codingshuttle.homework1.homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeBaker {
    @Autowired
    Frosting frosting;
    @Autowired
    Syrup syrup;

    String bakeCake() {
        return frosting.getFrostingType() + "\n" + syrup.getSyrupType();
    }

}
