package bstorm.akimts.CorrectionExo1.exceptions;

public class WrongPageException extends Exception {

    private int searchedPage;
    private int maxPageNbr;

    public WrongPageException(int searchedPage, int maxPageNbr) {
        super("La page recherché {"+ searchedPage +"} n'existe pas, la dernière page disponible actuellement est : "+ maxPageNbr);
        this.searchedPage = searchedPage;
        this.maxPageNbr = maxPageNbr;
    }
}
