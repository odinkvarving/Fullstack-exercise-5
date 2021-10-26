package ntnu.idatt2015.calculator.repository;

import ntnu.idatt2015.calculator.models.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CalculatorRepository {

    private static final Logger log = LoggerFactory.getLogger(CalculatorRepository.class);

    public double calculateEquation(Input input) {
        log.info("Input from frontend: " + input.getNumber1() + input.getOperator() + input.getNumber2());

        if(input.getOperator().replaceAll(" ", "").equals("+")) {
           return input.getNumber1() + input.getNumber2();

        }else if(input.getOperator().replaceAll(" ", "").equals("-")) {
            return input.getNumber1() - input.getNumber2();

        }else if(input.getOperator().replaceAll(" ", "").equals("*")) {
            return input.getNumber1() * input.getNumber2();

        }else if(input.getOperator().replaceAll(" ", "").equals("/")) {
            return (double) input.getNumber1() / input.getNumber2();

        }else {
            return 0;
        }
    }
}
