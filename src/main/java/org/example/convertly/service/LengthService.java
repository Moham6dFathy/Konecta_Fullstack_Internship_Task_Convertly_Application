package org.example.convertly.service;

import org.example.convertly.enums.LengthUnit;
import org.springframework.stereotype.Service;


@Service
public class LengthService {

    public double convert(double value, String fromUnit , String toUnit){
        LengthUnit fromLengthUnit = LengthUnit.valueOf(fromUnit.toUpperCase());
        LengthUnit toLengthUnit = LengthUnit.valueOf(toUnit.toUpperCase());

        return fromLengthUnit.convertTo(value, toLengthUnit);
    }

    public String getFormula(double value,String fromUnit ,String toUnit,double result){
        LengthUnit fromLengthUnit = LengthUnit.valueOf(fromUnit.toUpperCase());
        LengthUnit toLengthUnit = LengthUnit.valueOf(toUnit.toUpperCase());

        return toLengthUnit.generateFormula(value,fromLengthUnit,result);
    }
}
