package bstorm.akimts.CorrectionExo1.exceptions;

public class InvalidPageNbrException extends Exception {

    private int expectedPageNbr;
    private int totalPageNbr;

    public InvalidPageNbrException( int expectedPageNbr, int totalPageNbr ) {
        super("La page demandée ("+ expectedPageNbr +") n'existe pas. Il n'est possible de récupérer que de la page 0 à la page " + (totalPageNbr-1) );
        this.expectedPageNbr = expectedPageNbr;
        this.totalPageNbr = totalPageNbr;
    }

    public int getExpectedPageNbr() {
        return expectedPageNbr;
    }

    public void setExpectedPageNbr(int expectedPageNbr) {
        this.expectedPageNbr = expectedPageNbr;
    }

    public int getTotalPageNbr() {
        return totalPageNbr;
    }

    public void setTotalPageNbr(int totalPageNbr) {
        this.totalPageNbr = totalPageNbr;
    }
}
