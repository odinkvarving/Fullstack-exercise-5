package ntnu.idatt2015.calculator.service;

import ntnu.idatt2015.calculator.models.Input;
import ntnu.idatt2015.calculator.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Autowired
    private CalculatorRepository calculatorRepository;

    public double calculateEquation(Input input) {
        return calculatorRepository.calculateEquation(input);
    }
}
