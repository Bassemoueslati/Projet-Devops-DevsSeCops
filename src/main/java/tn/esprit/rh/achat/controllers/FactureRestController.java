package tn.esprit.rh.achat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.FactureDTO;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "Gestion des factures")
@RequestMapping("/facture")
@CrossOrigin(origins = "http://localhost:4200")
public class FactureRestController {

    private final IFactureService factureService;

    public FactureRestController(IFactureService factureService) {
        this.factureService = factureService;
    }

    @Operation(summary = "Retrieve all invoices")
    @GetMapping("/retrieve-all-factures")
    public List<Facture> getFactures() {
        return factureService.retrieveAllFactures();
    }

    @Operation(summary = "Retrieve invoice by ID")
    @GetMapping("/retrieve-facture/{facture-id}")
    public Facture retrieveFacture(
            @PathVariable("facture-id") Long factureId) {
        return factureService.retrieveFacture(factureId);
    }

    @Operation(summary = "Add a new invoice")
    @PostMapping("/add-facture")
    public Facture addFacture(@RequestBody FactureDTO dto) {

        Facture f = new Facture();
        f.setIdFacture(dto.getIdFacture());
        f.setMontantFacture(dto.getMontantFacture());
        f.setMontantRemise(dto.getMontantRemise());
        f.setDateCreationFacture(dto.getDateCreationFacture());
        f.setDateDerniereModificationFacture(dto.getDateDerniereModificationFacture());
        f.setArchivee(dto.getArchivee());

        return factureService.addFacture(f);
    }

    @Operation(summary = "Cancel an invoice")
    @PutMapping("/cancel-facture/{facture-id}")
    public void cancelFacture(
            @PathVariable("facture-id") Long factureId) {
        factureService.cancelFacture(factureId);
    }

    @Operation(summary = "Get invoices by supplier")
    @GetMapping("/getFactureByFournisseur/{fournisseur-id}")
    public List<Facture> getFactureByFournisseur(
            @PathVariable("fournisseur-id") Long fournisseurId) {

        return factureService.getFacturesByFournisseur(fournisseurId);
    }

    @Operation(summary = "Assign operator to invoice")
    @PutMapping("/assignOperateurToFacture/{idOperateur}/{idFacture}")
    public void assignOperateurToFacture(
            @PathVariable Long idOperateur,
            @PathVariable Long idFacture) {

        factureService.assignOperateurToFacture(idOperateur, idFacture);
    }

    @Operation(summary = "Calculate recovery percentage")
    @GetMapping("/pourcentageRecouvrement/{startDate}/{endDate}")
    public float pourcentageRecouvrement(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date startDate,

            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date endDate) {

        try {
            return factureService.pourcentageRecouvrement(startDate, endDate);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Error calculating pourcentageRecouvrement", e);
        }
    }
}