package org.example.convertly.service;

import org.example.convertly.enums.LengthUnit;
import org.example.convertly.enums.TimeUnit;
import org.springframework.stereotype.Service;


@Service
public class TimeService {

    public double convert(double value, String fromUnit, String toUnit){
        TimeUnit from = TimeUnit.valueOf(fromUnit.toUpperCase());
        TimeUnit to = TimeUnit.valueOf(toUnit.toUpperCase());

        return from.convertTo(value,to);
    }

    public String getFormula(double value,String fromUnit ,String toUnit,double result){
        TimeUnit fromTimeUnit = TimeUnit.valueOf(fromUnit.toUpperCase());
        TimeUnit toTimeUnit = TimeUnit.valueOf(toUnit.toUpperCase());

        return toTimeUnit.generateFormula(value,fromTimeUnit,result);
    }

}
