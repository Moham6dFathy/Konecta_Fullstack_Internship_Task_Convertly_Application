package org.example.convertly.service;

import org.example.convertly.enums.LengthUnit;
import org.example.convertly.enums.TemperatureUnit;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TemperatureService {

    public double convert(double value, String fromUnit , String toUnit){
        TemperatureUnit from = TemperatureUnit.valueOf(fromUnit.toUpperCase());
        TemperatureUnit to = TemperatureUnit.valueOf(toUnit.toUpperCase());

        return from.convertTo(value,to);
    }

    public String getFormula(double value ,String fromUnit, String toUnit,double result){
        TemperatureUnit to = TemperatureUnit.valueOf(toUnit.toUpperCase());
        TemperatureUnit from = TemperatureUnit.valueOf(fromUnit.toUpperCase());

        return from.getFormulaTo(to,value,result);
    }
}
