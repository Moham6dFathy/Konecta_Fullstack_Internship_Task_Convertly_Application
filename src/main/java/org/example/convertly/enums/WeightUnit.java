package org.example.convertly.enums;

import org.example.convertly.interfaces.UnitCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum WeightUnit implements UnitCategory {
    GRAM(1.00),
    KILOGRAM(1000.00),
    POUND(453.592),
    OUNCE(28.3495);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }
    public double getFactor() {
        return factor;
    }
    public double toGram(double value){
        return value * factor;
    }

    public double fromGram(double value){
        return value / factor;
    }

    public String generateFormula(double value,WeightUnit from , double result){
        return "( "+ value + " * " + from.factor +" ) / "+ this.factor + " + " + result ;
    }

    public double convertTo(double value, WeightUnit toUnit){
        double grams = toGram(value);
        return toUnit.fromGram(grams);
    }


    public List<String> getUnits(){
        return Arrays.stream(values())
                .map(e->e.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
