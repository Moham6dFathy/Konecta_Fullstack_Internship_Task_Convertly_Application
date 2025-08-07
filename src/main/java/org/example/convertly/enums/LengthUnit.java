package org.example.convertly.enums;

import org.example.convertly.interfaces.UnitCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LengthUnit implements UnitCategory {
    METER(1.0),
    KILOMETER(1000.0),
    MILE(1609.34),
    INCH(0.0254),
    FOOT(0.3048);

    private final double factor;

    LengthUnit(Double value) {
        this.factor = value;
    }

    public double getFactor() {
        return factor;
    }

    public double toMeter(double value){
        return value * factor;
    }

    public double fromMeter(double meters){
        return meters / factor;
    }

    public String generateFormula(double value,LengthUnit fromUnit,double result){
        return "( "+ value + " * " + fromUnit.factor +" ) / "+ this.factor + " = " + result ;
    }

    public double convertTo(double value,LengthUnit toUnit){
        double meters = this.toMeter(value);
        return toUnit.fromMeter(meters);
    }

    public List<String> getUnits(){
        return Arrays.stream(values())
                .map(e->e.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
