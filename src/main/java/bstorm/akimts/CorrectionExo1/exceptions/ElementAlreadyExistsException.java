package bstorm.akimts.CorrectionExo1.exceptions;

public class ElementAlreadyExistsException extends Exception {

    public ElementAlreadyExistsException() {
        super("L'élément existe deja dans la db.");
    }
}
