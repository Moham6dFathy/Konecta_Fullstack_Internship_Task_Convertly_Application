package org.example.convertly.enums;

import org.example.convertly.interfaces.UnitCategory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum Category {
    TEMPERATURE(TemperatureUnit.class),
    LENGTH(LengthUnit.class),
    WEIGHT(WeightUnit.class),
    TIME(TimeUnit.class);

    private final Class<? extends UnitCategory> unitEnum;

    Category(Class<? extends UnitCategory> unitEnum) {
        this.unitEnum = unitEnum;
    }

    public Class<? extends UnitCategory> getUnitEnum() {
        return unitEnum;
    }

    public static List<String> getCategories() {
        return Arrays.stream(Category.values())
                .map(e->e.name().toLowerCase())
                .collect(Collectors.toList());
    }

}
