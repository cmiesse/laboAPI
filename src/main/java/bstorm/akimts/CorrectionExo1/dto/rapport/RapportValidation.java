package bstorm.akimts.CorrectionExo1.dto.rapport;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RapportValidation extends Rapport {

    private final List<String> globalErrors = new ArrayList<>();
    private final List<String> fieldErrors = new ArrayList<>();

    public RapportValidation(String message, String chemin, int status) {
        super(message, chemin, status);
    }

    public void addError(boolean global, String error){

        if(global)
            globalErrors.add(error);
        else
            fieldErrors.add(error);

    }

    public List<String> getGlobalErrors() {
        return new ArrayList<>(this.globalErrors);
    }

    public List<String> getFieldErrors() {
        return new ArrayList<>(this.fieldErrors);
    }
}
