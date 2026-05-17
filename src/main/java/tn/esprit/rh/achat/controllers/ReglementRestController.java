package tn.esprit.rh.achat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.ReglementDTO;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "Gestion des reglements")
@RequestMapping("/reglement")
@CrossOrigin(origins = "http://localhost:4200")
public class ReglementRestController {

    private final IReglementService reglementService;

    public ReglementRestController(IReglementService reglementService) {
        this.reglementService = reglementService;
    }

    @Operation(summary = "Add a payment")
    @PostMapping("/add-reglement")
    public Reglement addReglement(@RequestBody ReglementDTO dto) {

        Reglement r = new Reglement();
        r.setIdReglement(dto.getIdReglement());
        r.setMontantPaye(dto.getMontantPaye());
        r.setMontantRestant(dto.getMontantRestant());
        r.setPayee(dto.getPayee());
        r.setDateReglement(dto.getDateReglement());

        return reglementService.addReglement(r);
    }

    @Operation(summary = "Retrieve all payments")
    @GetMapping("/retrieve-all-reglements")
    public List<Reglement> getReglement() {
        return reglementService.retrieveAllReglements();
    }

    @Operation(summary = "Retrieve payment by ID")
    @GetMapping("/retrieve-reglement/{reglement-id}")
    public Reglement retrieveReglement(
            @PathVariable("reglement-id") Long reglementId) {

        return reglementService.retrieveReglement(reglementId);
    }

    @Operation(summary = "Retrieve payments by invoice")
    @GetMapping("/retrieveReglementByFacture/{facture-id}")
    public List<Reglement> retrieveReglementByFacture(
            @PathVariable("facture-id") Long factureId) {

        return reglementService.retrieveReglementByFacture(factureId);
    }

    @Operation(summary = "Calculate turnover between two dates")
    @GetMapping("/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    public float getChiffreAffaireEntreDeuxDate(

            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date startDate,

            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date endDate) {

        return reglementService.getChiffreAffaireEntreDeuxDate(
                startDate,
                endDate
        );
    }
}