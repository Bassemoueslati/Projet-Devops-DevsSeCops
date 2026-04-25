package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.FournisseurDTO;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.services.IFournisseurService;

import java.util.List;

@RestController
@Api(tags = "Gestion des fournisseurss")
@RequestMapping("/fournisseur")
public class FournisseurRestController {

    private final IFournisseurService fournisseurService;

    public FournisseurRestController(IFournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping("/retrieve-all-fournisseurs")
    public List<Fournisseur> getFournisseurs() {
        return fournisseurService.retrieveAllFournisseurs();
    }

    @GetMapping("/retrieve-fournisseur/{fournisseur-id}")
    public Fournisseur retrieveFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
        return fournisseurService.retrieveFournisseur(fournisseurId);
    }

	@PostMapping("/add-fournisseur")
	public Fournisseur addFournisseur(@RequestBody FournisseurDTO dto) {
		Fournisseur f = new Fournisseur();
		f.setIdFournisseur(dto.getIdFournisseur());
		f.setCode(dto.getCode());
		f.setLibelle(dto.getLibelle());
		f.setCategorieFournisseur(dto.getCategorieFournisseur());
		return fournisseurService.addFournisseur(f);
	}

    @DeleteMapping("/remove-fournisseur/{fournisseur-id}")
    public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
        fournisseurService.deleteFournisseur(fournisseurId);
    }

	@PutMapping("/modify-fournisseur")
	public Fournisseur modifyFournisseur(@RequestBody FournisseurDTO dto) {
		Fournisseur f = new Fournisseur();
		f.setIdFournisseur(dto.getIdFournisseur());
		f.setCode(dto.getCode());
		f.setLibelle(dto.getLibelle());
		f.setCategorieFournisseur(dto.getCategorieFournisseur());
		return fournisseurService.updateFournisseur(f);
	}

    @PutMapping("/assignSecteurActiviteToFournisseur/{idSecteurActivite}/{idFournisseur}")
    public void assignProduitToStock(@PathVariable Long idSecteurActivite, @PathVariable Long idFournisseur) {
        fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);
    }
}