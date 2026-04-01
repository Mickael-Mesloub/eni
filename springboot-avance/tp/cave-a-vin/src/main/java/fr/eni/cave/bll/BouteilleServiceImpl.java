package fr.eni.cave.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.CouleurRepository;
import fr.eni.cave.dal.RegionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BouteilleServiceImpl implements BouteilleService {
	private BouteilleRepository bRepository;
	private RegionRepository rRepository;
	private CouleurRepository cRepository;

	@Override
	public List<Bouteille> chargerToutesBouteilles() {
		return bRepository.findAll();
	}

	@Override
	public Bouteille chargerBouteilleParId(int idBouteille) {
		// Validation de l'identifiant
		if (idBouteille <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Bouteille> opt = bRepository.findById(idBouteille);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune bouteille ne correspond");
	}

	@Override
	public List<Bouteille> chargerBouteillesParRegion(int idRegion) {
		final Region rDB = validerRegion(idRegion);

		final List<Bouteille> listeDB = bRepository.findByRegion(rDB);
		if (listeDB == null || listeDB.isEmpty()) {
			throw new RuntimeException("Aucune bouteille ne correspond");
		}
		return listeDB;
	}

	private Region validerRegion(int idRegion) {
		// Valider la Region
		if (idRegion <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Region> opt = rRepository.findById(idRegion);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune région ne correspond");
	}

	@Override
	public List<Bouteille> chargerBouteillesParCouleur(int idCouleur) {
		final Couleur cDB = validerCouleur(idCouleur);

		final List<Bouteille> listeDB = bRepository.findByCouleur(cDB);
		if (listeDB == null || listeDB.isEmpty()) {
			throw new RuntimeException("Aucune bouteille ne correspond");
		}
		return listeDB;
	}

	private Couleur validerCouleur(int idCouleur) {
		// Valider la Couleur
		if (idCouleur <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Couleur> opt = cRepository.findById(idCouleur);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune couleur de vin ne correspond");
	}

	@Override
	public Bouteille ajouter(Bouteille bouteille) {
		if (bouteille == null) {
			throw new RuntimeException("Bouteille est obligatoire");
		}
		validerBouteille(bouteille);
		try {
			final Bouteille bDB = bRepository.save(bouteille);
			return bDB;
		} catch (Exception e) {
			throw new RuntimeException("Impossible de sauver - " + bouteille.toString());
		}
	}

	@Override
	public void supprimer(int idBouteille) {
		if (idBouteille <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}
		try {
			bRepository.deleteById(idBouteille);
		} catch (Exception e) {
			throw new RuntimeException("Impossible de supprimer la bouteille (id = " + idBouteille + ")");
		}
	}

	private void validerBouteille(Bouteille bouteille) {
		if (bouteille == null) {
			throw new RuntimeException("Bouteille est obligatoire");
		}

		if (bouteille.getRegion() == null) {
			throw new RuntimeException("Région est obligatoire");
		}

		if (bouteille.getCouleur() == null) {
			throw new RuntimeException("Couleur est obligatoire");
		}

		final Region rDB = validerRegion(bouteille.getRegion().getId());
		final Couleur cDB = validerCouleur(bouteille.getCouleur().getId());

		// Valider que le nom n'est pas nule ou vide
		validerChaineNonNulle(bouteille.getNom(), "Le nom n'a pas été renseigné");


		validerNomUnique(bouteille);

		if (bouteille.getQuantite() <= 0) {
			throw new RuntimeException("Le nombre de bouteilles doit être positif");
		}

		if (bouteille.getPrix() <= 0) {
			throw new RuntimeException("Le prix doit être positif");
		}

		// associer la Region et la Couleur de la base à la Bouteille
		bouteille.setRegion(rDB);
		bouteille.setCouleur(cDB);
	}

	private void validerNomUnique(Bouteille bouteille) {

		// nom doit être unique
		// Appel de la méthode de requête spécifique : findByNom
		final Bouteille bDB = bRepository.findByNom(bouteille.getNom());
		if (bouteille.getId() == null && bDB != null ||
				bouteille.getId() != null && bDB != null && !bouteille.getId().equals(bDB.getId())) {
			throw new RuntimeException("Le nom doit être unique");
		}
	}

	private void validerChaineNonNulle(String chaine, String msgErreur) {
		if (chaine == null || chaine.isBlank())
			throw new RuntimeException(msgErreur);
	}
}