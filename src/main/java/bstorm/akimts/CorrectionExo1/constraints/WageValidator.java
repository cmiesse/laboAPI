package bstorm.akimts.CorrectionExo1.constraints;

import bstorm.akimts.CorrectionExo1.dto.ProfessorDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class WageValidator implements ConstraintValidator<Waged, ProfessorDTO> {

    @Override
    public boolean isValid(ProfessorDTO value, ConstraintValidatorContext context) {

        long years = Period.between(value.getHireDate().toLocalDate(), LocalDate.now()).getYears();

        int min = 1500;
        if(years > 20){
            min += 100 * ((years-20) / 5);
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("wage min : " + min).addConstraintViolation();

        return value.getWage() >= min;
    }
}
