package tn.esprit.rh.achat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.OperateurDTO;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;

@RestController
@Tag(name = "Gestion des opérateurs")
@RequestMapping("/operateur")
@CrossOrigin(origins = "http://localhost:4200")
public class OperateurController {

    private final IOperateurService operateurService;

    public OperateurController(IOperateurService operateurService) {
        this.operateurService = operateurService;
    }

    @Operation(summary = "Retrieve all operators")
    @GetMapping("/retrieve-all-operateurs")
    public List<Operateur> getOperateurs() {
        return operateurService.retrieveAllOperateurs();
    }

    @Operation(summary = "Retrieve operator by ID")
    @GetMapping("/retrieve-operateur/{operateur-id}")
    public Operateur retrieveOperateur(
            @PathVariable("operateur-id") Long operateurId) {

        return operateurService.retrieveOperateur(operateurId);
    }

    @Operation(summary = "Add an operator")
    @PostMapping("/add-operateur")
    public Operateur addOperateur(@RequestBody OperateurDTO dto) {

        Operateur op = new Operateur();
        op.setIdOperateur(dto.getIdOperateur());
        op.setNom(dto.getNom());
        op.setPrenom(dto.getPrenom());

        return operateurService.addOperateur(op);
    }

    @Operation(summary = "Delete operator")
    @DeleteMapping("/remove-operateur/{operateur-id}")
    public void removeOperateur(
            @PathVariable("operateur-id") Long operateurId) {

        operateurService.deleteOperateur(operateurId);
    }

    @Operation(summary = "Modify operator")
    @PutMapping("/modify-operateur")
    public Operateur modifyOperateur(@RequestBody OperateurDTO dto) {

        Operateur op = new Operateur();
        op.setIdOperateur(dto.getIdOperateur());
        op.setNom(dto.getNom());
        op.setPrenom(dto.getPrenom());

        return operateurService.updateOperateur(op);
    }
}