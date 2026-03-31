package fr.eni.demo.controller;

import fr.eni.demo.bll.EmployeService;
import fr.eni.demo.bo.Employe;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/eniecole/employes")
public class EmployeController {
    private EmployeService employeService;

    @GetMapping
    public ResponseEntity<?> lireTousLEsEmployes(){
        List<Employe> employes = employeService.lireTousLesEmployes();

        if(employes != null && !employes.isEmpty()) {
            return ResponseEntity.ok(employes);
        }

        return ResponseEntity.noContent().build();
    }
}
