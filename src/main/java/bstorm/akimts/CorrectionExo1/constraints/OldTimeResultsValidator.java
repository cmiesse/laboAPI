package bstorm.akimts.CorrectionExo1.constraints;

import bstorm.akimts.CorrectionExo1.dto.StudentDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OldTimeResultsValidator implements ConstraintValidator<OldTimeResults, StudentDTO> {
    @Override
    public boolean isValid(StudentDTO value, ConstraintValidatorContext context) {

        if( value.getBirthdate() == null || value.getResult() == null ){
            return false;
        }

        if( value.getBirthdate().isBefore( LocalDateTime.now().minusYears(50) ) )
        {
            context.disableDefaultConstraintViolation();
            if( value.getResult()< 0 ){
                context.buildConstraintViolationWithTemplate("{ +50ans et rslt < 0 }").addConstraintViolation();
                return false;
            }
            else if ( value.getResult() > 10 ){

                context.buildConstraintViolationWithTemplate("{ +50ans et rslt > 10 }").addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
