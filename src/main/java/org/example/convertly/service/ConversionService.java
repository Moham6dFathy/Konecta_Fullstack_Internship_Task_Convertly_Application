package org.example.convertly.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.convertly.enums.Category;
import org.example.convertly.exception.InvalidUnitException;
import org.example.convertly.interfaces.UnitCategory;
import org.example.convertly.model.ConversionResponse;
import org.example.convertly.model.ConversionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ConversionService {

    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private LengthService lengthService ;

    @Autowired
    private TimeService timeService ;

    @Autowired
    private WeightService weightService ;

    public boolean unitsMatch(String category,String fromUnit, String toUnit){
        Category categoryEnum = Category.valueOf(category.toUpperCase());

        Class<? extends UnitCategory> unit = categoryEnum.getUnitEnum();

        UnitCategory firstEnum = unit.getEnumConstants()[0];

        List<String> units = firstEnum.getUnits();

        return units.contains(fromUnit.toLowerCase()) && units.contains(toUnit.toLowerCase());

    }

    public double convert(String category , double value, String fromUnit , String toUnit) {
        switch (category) {
            case "temperature":
                return temperatureService.convert(value, fromUnit, toUnit);
            case "length":
                return lengthService.convert(value,fromUnit,toUnit);
            case "time":
                return timeService.convert(value,fromUnit,toUnit);
            case "weight":
                return weightService.convert(value,fromUnit,toUnit);
        }
        return value;
    }

    public String getFormula(String category,double value, String fromUnit, String toUnit,double result) {
        switch (category) {
            case "temperature":
                return temperatureService.getFormula(value,fromUnit,toUnit,result);
            case "length":
                return lengthService.getFormula(value,fromUnit,toUnit,result);
            case "time":
                return timeService.getFormula(value,fromUnit,toUnit,result);
            case "weight":
                return weightService.getFormula(value,fromUnit,toUnit,result);
        }
        return "";
    }

    public void saveConversion(String sessionId, ConversionResponse conversionResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File directory = new File("conversion-sessions");
        if (!directory.exists()) directory.mkdirs();

        File file = new File(directory, "session_" + sessionId + ".json");

        ConversionSession session;

        if(file.exists()){
            session = mapper.readValue(file, ConversionSession.class);
        }else {
            session = new ConversionSession();
            session.setSessionId(sessionId);
        }

        session.getConversions().add(conversionResponse);

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, session);

    }

    public ResponseEntity<InputStreamResource> downloadConversion(String sessionId) throws IOException {
        String filename = "conversion-sessions/session_" + sessionId + ".json";
        File file = new File(filename);

        if (!file.exists()) {
            throw new FileNotFoundException("Session file not found: " + sessionId);
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"session_" + sessionId + ".json\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }

    public List<String> getUnits(String category){
        Category categoryEnum;
        try {
            categoryEnum = Category.valueOf(category.toUpperCase());

            Class<? extends UnitCategory> unit = categoryEnum.getUnitEnum();

            UnitCategory firstEnum = unit.getEnumConstants()[0];

            List<String> result = firstEnum.getUnits();

            return ResponseEntity.ok(result).getBody();
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid category: " + category);
        }
    }
}
