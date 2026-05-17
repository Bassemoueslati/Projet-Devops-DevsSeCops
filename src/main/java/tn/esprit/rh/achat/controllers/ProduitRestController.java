package tn.esprit.rh.achat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.ProduitDTO;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitRestController {

    private final IProduitService produitService;

    public ProduitRestController(IProduitService produitService) {
        this.produitService = produitService;
    }

    @Operation(summary = "Retrieve all products")
    @GetMapping("/retrieve-all-produits")
    public List<Produit> getProduits() {
        return produitService.retrieveAllProduits();
    }

    @Operation(summary = "Retrieve product by ID")
    @GetMapping("/retrieve-produit/{produit-id}")
    public Produit retrieveRayon(
            @PathVariable("produit-id") Long produitId) {

        return produitService.retrieveProduit(produitId);
    }

    @Operation(summary = "Add a product")
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

    @Operation(summary = "Delete product")
    @DeleteMapping("/remove-produit/{produit-id}")
    public void removeProduit(
            @PathVariable("produit-id") Long produitId) {

        produitService.deleteProduit(produitId);
    }

    @Operation(summary = "Modify product")
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

    @Operation(summary = "Assign product to stock")
    @PutMapping("/assignProduitToStock/{idProduit}/{idStock}")
    public void assignProduitToStock(
            @PathVariable Long idProduit,
            @PathVariable Long idStock) {

        produitService.assignProduitToStock(idProduit, idStock);
    }
}