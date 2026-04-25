package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.ProduitDTO;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.List;

@RestController
@CrossOrigin("*")
@Api(tags = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitRestController {

    private final IProduitService produitService;

    public ProduitRestController(IProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/retrieve-all-produits")
    public List<Produit> getProduits() {
        return produitService.retrieveAllProduits();
    }

    @GetMapping("/retrieve-produit/{produit-id}")
    public Produit retrieveRayon(@PathVariable("produit-id") Long produitId) {
        return produitService.retrieveProduit(produitId);
    }

	@PostMapping("/add-produit")
	public Produit addProduit(@RequestBody ProduitDTO dto) {
		Produit p = new Produit();
		p.setIdProduit(dto.getIdProduit());
		p.setCodeProduit(dto.getCodeProduit());
		p.setLibelleProduit(dto.getLibelleProduit());
		p.setPrix(dto.getPrix());
		p.setDateCreation(dto.getDateCreation());
		p.setDateDerniereModification(dto.getDateDerniereModification());
		return produitService.addProduit(p);
	}

    @DeleteMapping("/remove-produit/{produit-id}")
    public void removeProduit(@PathVariable("produit-id") Long produitId) {
        produitService.deleteProduit(produitId);
    }

	@PutMapping("/modify-produit")
	public Produit modifyProduit(@RequestBody ProduitDTO dto) {
		Produit p = new Produit();
		p.setIdProduit(dto.getIdProduit());
		p.setCodeProduit(dto.getCodeProduit());
		p.setLibelleProduit(dto.getLibelleProduit());
		p.setPrix(dto.getPrix());
		p.setDateCreation(dto.getDateCreation());
		p.setDateDerniereModification(dto.getDateDerniereModification());
		return produitService.updateProduit(p);
	}

    @PutMapping("/assignProduitToStock/{idProduit}/{idStock}")
    public void assignProduitToStock(@PathVariable Long idProduit, @PathVariable Long idStock) {
        produitService.assignProduitToStock(idProduit, idStock);
    }
}