package ntnu.idatt2015.calculator.controller;

import ntnu.idatt2015.calculator.models.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ntnu.idatt2015.calculator.service.CalculatorService;

@RequestMapping("/calculate")

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/equation")
    public ResponseEntity calculateEquation(@RequestBody Input input) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(calculatorService.calculateEquation(input));
        } catch (ArithmeticException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not calculate equation");
        }
    }
}

