package org.example.convertly.enums;

import org.example.convertly.interfaces.UnitCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TemperatureUnit implements UnitCategory {
    CELSIUS {
        @Override
        public double toCelsius(double value) {
            return value;
        }

        @Override
        public double fromCelsius(double celsius) {
            return celsius;
        }

        @Override
        public String getFormulaTo(TemperatureUnit to, double input, double result) {
            switch (to) {
                case FAHRENHEIT:
                    return String.format("(%s * 9/5) + 32 = %s F", input, result);
                case KELVIN:
                    return String.format("%s + 273.15 = %s K", input, result);
                default:
                    return String.format("%s = %s C", input, result);
            }
        }
    },
    FAHRENHEIT {
        @Override
        public double toCelsius(double value) {
            return (value - 32) * 5 / 9;
        }

        @Override
        public double fromCelsius(double celsius) {
            return celsius * 9 / 5 + 32;
        }

        @Override
        public String getFormulaTo(TemperatureUnit to, double input, double result) {
            switch (to) {
                case CELSIUS:
                    return String.format("(%s - 32) * 5/9 = %s C", input, result);
                case KELVIN:
                    return String.format("((%s - 32) * 5/9) + 273.15 = %s K", input, result);
                default:
                    return String.format("%s = %s F", input, result);
            }
        }
    },
    KELVIN {
        @Override
        public double toCelsius(double value) {
            return value - 273.15;
        }

        @Override
        public double fromCelsius(double celsius) {
            return celsius + 273.15;
        }

        @Override
        public String getFormulaTo(TemperatureUnit to, double input, double result) {
            switch (to) {
                case CELSIUS:
                    return String.format("%s - 273.15 = %s C", input, result);
                case FAHRENHEIT:
                    return String.format("((%s - 273.15) * 9/5) + 32 = %s F", input, result);
                default:
                    return String.format("%s = %s K", input, result);
            }
        }
    };

    public abstract double toCelsius(double value);
    public abstract double fromCelsius(double celsius);
    public abstract String getFormulaTo(TemperatureUnit to, double input, double result);

    public double convertTo(double value,TemperatureUnit toUnit){
        double celsius = toCelsius(value);
        return toUnit.fromCelsius(celsius);
    }

    public List<String> getUnits(){
        return Arrays.stream(values())
                .map(e->e.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
