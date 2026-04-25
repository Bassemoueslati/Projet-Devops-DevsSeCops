package tn.esprit.rh.achat.services;

import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService {

    private final SecteurActiviteRepository secteurActiviteRepository;

    
    public SecteurActiviteServiceImpl(SecteurActiviteRepository secteurActiviteRepository) {
        this.secteurActiviteRepository = secteurActiviteRepository;
    }

    @Override
    public List<SecteurActivite> retrieveAllSecteurActivite() {
        
        List<SecteurActivite> list = new ArrayList<>();
        secteurActiviteRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public SecteurActivite addSecteurActivite(SecteurActivite sa) {
        return secteurActiviteRepository.save(sa); 
    }

    @Override
    public void deleteSecteurActivite(Long id) {
        secteurActiviteRepository.deleteById(id);
    }

    @Override
    public SecteurActivite updateSecteurActivite(SecteurActivite sa) {
        return secteurActiviteRepository.save(sa); 
    }

    @Override
    public SecteurActivite retrieveSecteurActivite(Long id) {
        return secteurActiviteRepository.findById(id).orElse(null);
    }
}