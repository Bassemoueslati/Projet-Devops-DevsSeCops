package tn.esprit.rh.achat.services;

import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Date;
import java.util.List;

@Service
public class ReglementServiceImpl implements IReglementService {

    private final ReglementRepository reglementRepository;

    // ✅ FIXED constructor (removed unused dependency)
    public ReglementServiceImpl(ReglementRepository reglementRepository) {
        this.reglementRepository = reglementRepository;
    }

    @Override
    public List<Reglement> retrieveAllReglements() {
        return reglementRepository.findAll();
    }

    @Override
    public Reglement addReglement(Reglement r) {
        return reglementRepository.save(r);
    }

    @Override
    public Reglement retrieveReglement(Long id) {
        return reglementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reglement> retrieveReglementByFacture(Long idFacture) {
        return reglementRepository.retrieveReglementByFacture(idFacture);
    }

    @Override
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        return reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }
}