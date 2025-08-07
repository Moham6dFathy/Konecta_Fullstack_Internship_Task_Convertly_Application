package org.example.convertly.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ConversionRequest {

    @NotBlank(message = "Category must not be blank")
    private String category;

    @NotBlank(message = "From unit must not be blank")
    private String fromUnit;

    @NotBlank(message = "To unit must not be blank")
    private String toUnit;

    @NotNull(message = "Value must not be null")
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

}
