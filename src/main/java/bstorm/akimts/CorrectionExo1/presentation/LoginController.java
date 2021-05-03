package bstorm.akimts.CorrectionExo1.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ResponseEntity<Boolean> login(){
        return ResponseEntity.ok(true);
    }

}
