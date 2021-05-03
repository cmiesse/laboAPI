package bstorm.akimts.CorrectionExo1.dto.rapport;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Rapport {

    private String message;
    private String chemin;
    private int status;

    public Rapport(String message, String chemin, int status) {
        this.message = message;
        this.chemin = chemin;
        this.status = status;
    }

}
