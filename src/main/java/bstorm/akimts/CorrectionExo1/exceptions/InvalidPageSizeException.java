package bstorm.akimts.CorrectionExo1.exceptions;

public class InvalidPageSizeException extends Exception {

    private final String acceptableSizes;

    public InvalidPageSizeException(String acceptableSizes) {
        super("la taille donnée pour la page est invalide. Tailles acceptables :" + acceptableSizes);
        this.acceptableSizes = acceptableSizes;
    }

    public String getAcceptableSizes() {
        return acceptableSizes;
    }
}
