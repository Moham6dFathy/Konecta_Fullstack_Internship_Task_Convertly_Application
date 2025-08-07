package org.example.convertly.service;

import org.example.convertly.enums.WeightUnit;
import org.springframework.stereotype.Service;

@Service
public class WeightService {

    public double convert(double value,String fromUnit,String toUnit){
        WeightUnit from =  WeightUnit.valueOf(fromUnit.toUpperCase());
        WeightUnit to = WeightUnit.valueOf(toUnit.toUpperCase());

        return from.convertTo(value,to);
    }

    public String getFormula(double value,String fromUnit,String toUnit,double result){
        WeightUnit from =  WeightUnit.valueOf(fromUnit.toUpperCase());
        WeightUnit to = WeightUnit.valueOf(toUnit.toUpperCase());

        return to.generateFormula(value,from,result);
    }
}

