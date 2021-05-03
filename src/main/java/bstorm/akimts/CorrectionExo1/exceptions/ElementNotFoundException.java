package bstorm.akimts.CorrectionExo1.exceptions;

public class ElementNotFoundException extends Exception {

    private Object id;

    public ElementNotFoundException(Object id) {
        super("L'element d'id "+id+" n'a pas été trouvé");
    }
}
