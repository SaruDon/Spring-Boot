package com.fcrit.week2HomeWorkexample.Department.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SalaryValidator implements ConstraintValidator<SalaryValidation,Long>{

    @Override
    public boolean isValid(Long avgSalary, ConstraintValidatorContext constraintValidatorContext){
        if(avgSalary<=1) return true;
        for(int i =2;i<avgSalary;i++){
            if (avgSalary%i==0) return false;
        }
        return true;
    }
}
