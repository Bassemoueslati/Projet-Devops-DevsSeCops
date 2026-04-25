package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "Gestion des reglements")
@RequestMapping("/reglement")
@CrossOrigin("*")
public class ReglementRestController {

    private final IReglementService reglementService;

    public ReglementRestController(IReglementService reglementService) {
        this.reglementService = reglementService;
    }

    @PostMapping("/add-reglement")
    public Reglement addReglement(@RequestBody Reglement r) {
        return reglementService.addReglement(r);
    }

    @GetMapping("/retrieve-all-reglements")
    public List<Reglement> getReglement() {
        return reglementService.retrieveAllReglements();
    }

    @GetMapping("/retrieve-reglement/{reglement-id}")
    public Reglement retrieveReglement(@PathVariable("reglement-id") Long reglementId) {
        return reglementService.retrieveReglement(reglementId);
    }

    @GetMapping("/retrieveReglementByFacture/{facture-id}")
    public List<Reglement> retrieveReglementByFacture(@PathVariable("facture-id") Long factureId) {
        return reglementService.retrieveReglementByFacture(factureId);
    }

    @GetMapping("/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    public float getChiffreAffaireEntreDeuxDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }
}