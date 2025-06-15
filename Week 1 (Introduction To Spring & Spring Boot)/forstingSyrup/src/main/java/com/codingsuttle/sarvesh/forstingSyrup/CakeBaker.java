package com.codingsuttle.sarvesh.forstingSyrup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeBaker {

    final private Syrup syrup;
    final private Frosting frosting;

    public CakeBaker(Syrup syrup, Frosting frosting) {
        this.syrup = syrup;
        this.frosting = frosting;
    }

//    @Autowired
//    Syrup syrup;
//
//    @Autowired
//    Frosting frosting;


    String bakeCake(){
        return syrup.getSyrupType() + "\n" +frosting.getFrostingType();
    }

}
