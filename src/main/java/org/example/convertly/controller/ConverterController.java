package org.example.convertly.controller;

import jakarta.validation.Valid;
import org.example.convertly.enums.Category;
import org.example.convertly.enums.LengthUnit;
import org.example.convertly.enums.WeightUnit;
import org.example.convertly.exception.InvalidUnitException;
import org.example.convertly.exception.NegativeValueException;
import org.example.convertly.interfaces.UnitCategory;
import org.example.convertly.model.ConversionRequest;
import org.example.convertly.model.ConversionResponse;
import org.example.convertly.service.ConversionService;
import org.example.convertly.service.LengthService;
import org.example.convertly.service.TimeService;
import org.example.convertly.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ConverterController {

   @Autowired
   private ConversionService conversionService;

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@Valid @RequestBody ConversionRequest data, BindingResult bindingResult, @RequestHeader String sessionId) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        String category = data.getCategory();
        String fromUnit = data.getFromUnit();
        String toUnit = data.getToUnit();
        double value = data.getValue();

        if(!category.equals("temperature")&& value < 0){
            throw new NegativeValueException("Value must be greater than or equal to 0");
        }

        if(! conversionService.unitsMatch(category,fromUnit,toUnit)){
            throw new InvalidUnitException("Units don’t match category");
        }

        double result = conversionService.convert(category.toLowerCase(),value,fromUnit,toUnit);

        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setConvertedResult(result);
        conversionResponse.setFormula(conversionService.getFormula(category,value,fromUnit,toUnit,result));
        conversionResponse.setInput(data);
        conversionResponse.setStatus("success");

        if(sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();
        }
        conversionService.saveConversion(sessionId, conversionResponse);

        return ResponseEntity.ok(conversionResponse);
    }

    @GetMapping("/conversion/{sessionId}/download")
    public ResponseEntity<InputStreamResource> downloadConversion(@PathVariable String sessionId) throws IOException {
        return conversionService.downloadConversion(sessionId);
    }

    @GetMapping("/category")
    public List<String> categories(){
        List<String> categories = Category.getCategories();
        return categories;
    }

    @GetMapping("/unit")
    public List<String> units(@RequestParam("category") String category){
        return conversionService.getUnits(category);
    }

    @GetMapping("/sample-payload")
    public ConversionRequest samplePayload(){
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setCategory("Category such as [temperature, length, weight, time]");
        conversionRequest.setFromUnit("such as [meter , kilometer ....]");
        conversionRequest.setToUnit("such as [meter , kilometer ....]");
        conversionRequest.setValue(10.0);
        return conversionRequest;
    }

    @GetMapping("/health")
    public ResponseEntity health(){
        HashMap<String,String> status = new HashMap<>();
        status.put("status", "Unit Converter API is up and running");
        return ResponseEntity.ok(status);
    }
}
