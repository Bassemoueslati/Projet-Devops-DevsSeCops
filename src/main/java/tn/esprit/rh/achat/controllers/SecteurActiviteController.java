package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.SecteurActiviteDTO;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

import java.util.List;

@RestController
@Api(tags = "Gestion des secteurs activites")
@RequestMapping("/secteurActivite")
@CrossOrigin(origins = "http://localhost:4200")
public class SecteurActiviteController {

    private final ISecteurActiviteService secteurActiviteService;

    public SecteurActiviteController(ISecteurActiviteService secteurActiviteService) {
        this.secteurActiviteService = secteurActiviteService;
    }

    @GetMapping("/retrieve-all-secteurActivite")
    public List<SecteurActivite> getSecteurActivite() {
        return secteurActiviteService.retrieveAllSecteurActivite();
    }

    @GetMapping("/retrieve-secteurActivite/{secteurActivite-id}")
    public SecteurActivite retrieveSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
        return secteurActiviteService.retrieveSecteurActivite(secteurActiviteId);
    }

	@PostMapping("/add-secteurActivite")
	public SecteurActivite addSecteurActivite(@RequestBody SecteurActiviteDTO dto) {
		SecteurActivite sa = new SecteurActivite();
		sa.setIdSecteurActivite(dto.getIdSecteurActivite());
		sa.setCodeSecteurActivite(dto.getCodeSecteurActivite());
		sa.setLibelleSecteurActivite(dto.getLibelleSecteurActivite());
		return secteurActiviteService.addSecteurActivite(sa);
	}

    @DeleteMapping("/remove-secteurActivite/{secteurActivite-id}")
    public void removeSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
        secteurActiviteService.deleteSecteurActivite(secteurActiviteId);
    }

	@PutMapping("/modify-secteurActivite")
	public SecteurActivite modifySecteurActivite(@RequestBody SecteurActiviteDTO dto) {
		SecteurActivite sa = new SecteurActivite();
		sa.setIdSecteurActivite(dto.getIdSecteurActivite());
		sa.setCodeSecteurActivite(dto.getCodeSecteurActivite());
		sa.setLibelleSecteurActivite(dto.getLibelleSecteurActivite());
		return secteurActiviteService.updateSecteurActivite(sa);
	}
}