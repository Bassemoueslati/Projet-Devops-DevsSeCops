package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.IFactureService;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "Gestion des factures")
@RequestMapping("/facture")
@CrossOrigin("*")
public class FactureRestController {

    private final IFactureService factureService;

    public FactureRestController(IFactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("/retrieve-all-factures")
    public List<Facture> getFactures() {
        return factureService.retrieveAllFactures();
    }

    @GetMapping("/retrieve-facture/{facture-id}")
    public Facture retrieveFacture(@PathVariable("facture-id") Long factureId) {
        return factureService.retrieveFacture(factureId);
    }

    @PostMapping("/add-facture")
    public Facture addFacture(@RequestBody Facture f) {
        return factureService.addFacture(f);
    }

    @PutMapping("/cancel-facture/{facture-id}")
    public void cancelFacture(@PathVariable("facture-id") Long factureId) {
        factureService.cancelFacture(factureId);
    }

    @GetMapping("/getFactureByFournisseur/{fournisseur-id}")
    public List<Facture> getFactureByFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
        return factureService.getFacturesByFournisseur(fournisseurId);
    }

    @PutMapping("/assignOperateurToFacture/{idOperateur}/{idFacture}")
    public void assignOperateurToFacture(@PathVariable Long idOperateur, @PathVariable Long idFacture) {
        factureService.assignOperateurToFacture(idOperateur, idFacture);
    }

    @GetMapping("/pourcentageRecouvrement/{startDate}/{endDate}")
    public float pourcentageRecouvrement(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        try {
            return factureService.pourcentageRecouvrement(startDate, endDate);
        } catch (Exception e) {
            throw new IllegalStateException("Error calculating pourcentageRecouvrement", e);
}
    }
}