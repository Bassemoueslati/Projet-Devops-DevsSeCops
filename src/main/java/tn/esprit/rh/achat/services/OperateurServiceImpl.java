package tn.esprit.rh.achat.services;

import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.List;

@Service
public class OperateurServiceImpl implements IOperateurService {

    private final OperateurRepository operateurRepository;

    
    public OperateurServiceImpl(OperateurRepository operateurRepository) {
        this.operateurRepository = operateurRepository;
    }

    @Override
    public List<Operateur> retrieveAllOperateurs() {
        return operateurRepository.findAll();
    }

    @Override
    public Operateur addOperateur(Operateur o) {
        return operateurRepository.save(o);
    }

    @Override
    public void deleteOperateur(Long id) {
        operateurRepository.deleteById(id);
    }

    @Override
    public Operateur updateOperateur(Operateur o) {
        return operateurRepository.save(o);
    }

    @Override
    public Operateur retrieveOperateur(Long id) {
        return operateurRepository.findById(id).orElse(null);
    }
}