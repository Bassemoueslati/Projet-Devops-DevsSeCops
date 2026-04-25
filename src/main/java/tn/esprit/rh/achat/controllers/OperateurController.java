package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.OperateurDTO;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;

@RestController
@Api(tags = "Gestion des opérateurs")
@RequestMapping("/operateur")
@CrossOrigin("*")
public class OperateurController {

    private final IOperateurService operateurService;

    public OperateurController(IOperateurService operateurService) {
        this.operateurService = operateurService;
    }

    @GetMapping("/retrieve-all-operateurs")
    public List<Operateur> getOperateurs() {
        return operateurService.retrieveAllOperateurs();
    }

    @GetMapping("/retrieve-operateur/{operateur-id}")
    public Operateur retrieveOperateur(@PathVariable("operateur-id") Long operateurId) {
        return operateurService.retrieveOperateur(operateurId);
    }

	@PostMapping("/add-operateur")
	public Operateur addOperateur(@RequestBody OperateurDTO dto) {
		Operateur op = new Operateur();
		op.setIdOperateur(dto.getIdOperateur());
		op.setNom(dto.getNom());
		op.setPrenom(dto.getPrenom());
		return operateurService.addOperateur(op);
	}

    @DeleteMapping("/remove-operateur/{operateur-id}")
    public void removeOperateur(@PathVariable("operateur-id") Long operateurId) {
        operateurService.deleteOperateur(operateurId);
    }

	@PutMapping("/modify-operateur")
	public Operateur modifyOperateur(@RequestBody OperateurDTO dto) {
		Operateur op = new Operateur();
		op.setIdOperateur(dto.getIdOperateur());
		op.setNom(dto.getNom());
		op.setPrenom(dto.getPrenom());
		return operateurService.updateOperateur(op);
	}
}