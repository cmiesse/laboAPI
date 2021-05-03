package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.dto.rapport.Rapport;
import bstorm.akimts.CorrectionExo1.dto.rapport.RapportValidation;
import bstorm.akimts.CorrectionExo1.exceptions.*;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<String> demoHandler(DemoException ex, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(request.getRequestURI() + " -> depuis le controller advice");
    }

    @ExceptionHandler({ElementNotFoundException.class, ElementAlreadyExistsException.class})
    public ResponseEntity<Rapport> handleElementNotFound(Exception ex, HttpServletRequest request){

        Rapport rapport = new Rapport(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(rapport);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RapportValidation> handleConstraint(ConstraintViolationException ex, WebRequest request){

        RapportValidation rv = new RapportValidation(
                "Constraintes invalidées",
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            rv.addError(true, constraintViolation.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(rv);
    }

    @ExceptionHandler(WrongPageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Rapport handleWrongPage(WrongPageException ex, WebRequest request){
        return new Rapport(
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler({InvalidPageSizeException.class, InvalidPageNbrException.class})
    public ResponseEntity<Rapport> handleInvalidPageRequest(Exception ex, WebRequest request){
        return ResponseEntity.badRequest().body(
                new Rapport(
                        ex.getMessage(),
                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST.value()
                )
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        RapportValidation rp = new RapportValidation(
                "Erreur de validation",
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );

        for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
            rp.addError(true, globalError.getDefaultMessage() );
        }

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            rp.addError(false, error.getField() + " - " + error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(rp);
    }



}
