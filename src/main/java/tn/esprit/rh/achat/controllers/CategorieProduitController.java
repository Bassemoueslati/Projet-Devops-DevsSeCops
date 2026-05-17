package tn.esprit.rh.achat.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.CategorieProduitDTO;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.List;

@RestController
@Tag(name = "Gestion des categories Produit")
@RequestMapping("/categorieProduit")
public class CategorieProduitController {

    private final ICategorieProduitService categorieProduitService;

    // Constructor Injection
    public CategorieProduitController(ICategorieProduitService categorieProduitService) {
        this.categorieProduitService = categorieProduitService;
    }

    @Operation(summary = "Retrieve all categories")
    @GetMapping("/retrieve-all-categorieProduit")
    public List<CategorieProduit> getCategorieProduit() {
        return categorieProduitService.retrieveAllCategorieProduits();
    }

    @Operation(summary = "Retrieve category by ID")
    @GetMapping("/retrieve-categorieProduit/{categorieProduit-id}")
    public CategorieProduit retrieveCategorieProduit(
            @PathVariable("categorieProduit-id") Long categorieProduitId) {
        return categorieProduitService.retrieveCategorieProduit(categorieProduitId);
    }

    @Operation(summary = "Add a category")
    @PostMapping("/add-categorieProduit")
    public CategorieProduit addCategorieProduit(@RequestBody CategorieProduitDTO dto) {
        CategorieProduit cp = new CategorieProduit();
        cp.setIdCategorieProduit(dto.getIdCategorieProduit());
        cp.setCodeCategorie(dto.getCodeCategorie());
        cp.setLibelleCategorie(dto.getLibelleCategorie());

        return categorieProduitService.addCategorieProduit(cp);
    }

    @Operation(summary = "Delete category")
    @DeleteMapping("/remove-categorieProduit/{categorieProduit-id}")
    public void removeCategorieProduit(
            @PathVariable("categorieProduit-id") Long categorieProduitId) {

        categorieProduitService.deleteCategorieProduit(categorieProduitId);
    }

    @Operation(summary = "Modify category")
    @PutMapping("/modify-categorieProduit")
    public CategorieProduit modifyCategorieProduit(@RequestBody CategorieProduitDTO dto) {

        CategorieProduit cp = new CategorieProduit();
        cp.setIdCategorieProduit(dto.getIdCategorieProduit());
        cp.setCodeCategorie(dto.getCodeCategorie());
        cp.setLibelleCategorie(dto.getLibelleCategorie());

        return categorieProduitService.updateCategorieProduit(cp);
    }
}