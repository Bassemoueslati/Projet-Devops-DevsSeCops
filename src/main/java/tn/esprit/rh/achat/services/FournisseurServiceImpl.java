package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final DetailFournisseurRepository detailFournisseurRepository;
    private final ProduitRepository produitRepository;
    private final SecteurActiviteRepository secteurActiviteRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
                                 DetailFournisseurRepository detailFournisseurRepository,
                                 ProduitRepository produitRepository,
                                 SecteurActiviteRepository secteurActiviteRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.detailFournisseurRepository = detailFournisseurRepository;
        this.produitRepository = produitRepository;
        this.secteurActiviteRepository = secteurActiviteRepository;
    }

    @Override
    public List<Fournisseur> retrieveAllFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        for (Fournisseur fournisseur : fournisseurs) {
            log.info(" fournisseur : " + fournisseur);
        }
        return fournisseurs;
    }

    @Override
    public Fournisseur addFournisseur(Fournisseur f) {
        DetailFournisseur df = new DetailFournisseur();
        df.setDateDebutCollaboration(new Date());

        // Link detail to fournisseur
        f.setDetailFournisseur(df);

        return fournisseurRepository.save(f);
    }

    private DetailFournisseur saveDetailFournisseur(Fournisseur f) {
        DetailFournisseur df = f.getDetailFournisseur();
        return detailFournisseurRepository.save(df);
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur f) {
        DetailFournisseur df = saveDetailFournisseur(f);
        f.setDetailFournisseur(df);
        return fournisseurRepository.save(f);
    }

    @Override
    public void deleteFournisseur(Long fournisseurId) {
        fournisseurRepository.deleteById(fournisseurId);
    }

    @Override
    public Fournisseur retrieveFournisseur(Long fournisseurId) {
        return fournisseurRepository.findById(fournisseurId).orElse(null);
    }

    @Override
    public void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur) {
        Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
        SecteurActivite secteurActivite = secteurActiviteRepository.findById(idSecteurActivite).orElse(null);

        if (fournisseur == null || secteurActivite == null) {
            throw new IllegalArgumentException("Fournisseur or SecteurActivite not found");
        }

        fournisseur.getSecteurActivites().add(secteurActivite);
        fournisseurRepository.save(fournisseur);
    }
}