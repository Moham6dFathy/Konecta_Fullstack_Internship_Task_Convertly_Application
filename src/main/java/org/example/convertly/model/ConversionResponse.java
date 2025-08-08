package org.example.convertly.model;

public class ConversionResponse {
    private Double convertedResult;

    private String formula;

    private ConversionRequest input;

    private String status;

    public Double getConvertedResult() {
        return convertedResult;
    }

    public void setConvertedResult(Double convertedResult) {
        this.convertedResult = convertedResult;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public ConversionRequest getInput() {
        return input;
    }

    public void setInput(ConversionRequest input) {
        this.input = input;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
