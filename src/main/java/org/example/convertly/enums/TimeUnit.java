package org.example.convertly.enums;

import org.example.convertly.interfaces.UnitCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TimeUnit implements UnitCategory {
    SECOND(1.00),
    MINUTE(60.00),
    HOUR(3600.00),
    DAY(86400.00);

    private final double factor;

    TimeUnit(double factor) {
        this.factor = factor;
    }

    public double toSeconds(double value){
        return value * factor;
    }

    public double fromSeconds(double value){
        return value / factor;
    }

    public String generateFormula(double value,TimeUnit fromUnit ,double result){
        return "( "+ value + " * " + fromUnit.factor +" ) / "+ this.factor + " = " + result ;
    }

    public double convertTo(double value, TimeUnit toUnit){
        double seconds = toSeconds(value);
        return toUnit.fromSeconds(seconds);
    }

    public List<String> getUnits(){
        return Arrays.stream(values())
                .map(e->e.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
