package fr.eni.cave.controller;

import fr.eni.cave.bll.BouteilleService;
import fr.eni.cave.bo.vin.Bouteille;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/caveavin/bouteilles")
public class BouteilleController {

    private BouteilleService bService;

    @GetMapping
    public ResponseEntity<?> rechercherToutesBouteilles(){

        List<Bouteille> bouteilles = bService.chargerToutesBouteilles();
        if(bouteilles == null || bouteilles.isEmpty()){
            // Statut 204 : No Content - Pas de body car rien à afficher
            return ResponseEntity.noContent().build();
        }
        // Statut 200 : OK + dans le body bouteilles
        // Le contenu du body est directement injecté dans la méthode ok
        return ResponseEntity.ok(bouteilles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> rechercherBouteilleParId(@PathVariable("id") String idInPath) {
        // Toutes les données transmises par le protocole HTTP sont des chaines de
        // caractères par défaut
        // Il vaut mieux gérer les exceptions des données dans la méthode
        try{
            final int id = Integer.parseInt(idInPath);
            final Bouteille bouteille = bService.chargerBouteilleParId(id);
            return ResponseEntity.ok(bouteille);
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
        }
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<?> rechercherBouteillesParRegion(@PathVariable("id") String idInPath) {
        try{
            final int idRegion = Integer.parseInt(idInPath);
            final List<Bouteille> bouteilles = bService.chargerBouteillesParRegion(idRegion);
            return ResponseEntity.ok(bouteilles);
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
        }
    }

    @GetMapping("/couleur/{id}")
    public ResponseEntity<?> rechercherBouteillesParCouleur(@PathVariable("id") String idInPath) {
        try{
            final int idCouleur = Integer.parseInt(idInPath);
            final List<Bouteille> bouteilles = bService.chargerBouteillesParCouleur(idCouleur);
            return ResponseEntity.ok(bouteilles);
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
        }
    }

    @PostMapping
    public ResponseEntity<?> ajoutBouteille(@RequestBody Bouteille bouteille){

        try{
            bService.ajouter(bouteille);
            return ResponseEntity.ok(bouteille);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<?> miseAJourBouteille(@RequestBody Bouteille bouteille){
        try{
            if(bouteille == null || bouteille.getId() == null || bouteille.getId() <= 0){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La bouteille et l'identifiant sont obligatoires");
            }
            bService.ajouter(bouteille);
            return ResponseEntity.ok(bouteille);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBouteille(@PathVariable("id") String idInPath){
        try{
            final int idBouteille = Integer.parseInt(idInPath);
            bService.supprimer(idBouteille);
            return ResponseEntity.ok("Bouteille ("+ idBouteille+") est supprimée");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}