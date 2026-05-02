package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FactureServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private OperateurRepository operateurRepository;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private ReglementServiceImpl reglementService;

    @InjectMocks
    private FactureServiceImpl service;

    private Facture facture;
    private Fournisseur fournisseur;
    private Operateur operateur;

    @BeforeEach
    void setUp() {
        facture = new Facture();
        facture.setIdFacture(1L);
        facture.setArchivee(false);

        fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setFactures(new HashSet<>(Collections.singletonList(facture)));

        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setFactures(new HashSet<>());
    }


    @Test
    void retrieveAllFactures() {
        when(factureRepository.findAll()).thenReturn(Arrays.asList(facture));

        List<Facture> result = service.retrieveAllFactures();

        assertEquals(1, result.size());
        verify(factureRepository).findAll();
    }


    @Test
    void addFacture() {
        when(factureRepository.save(facture)).thenReturn(facture);

        Facture result = service.addFacture(facture);

        assertNotNull(result);
        verify(factureRepository).save(facture);
    }


    @Test
    void cancelFacture_existing() {
        when(factureRepository.findById(1L)).thenReturn(Optional.of(facture));

        service.cancelFacture(1L);

        assertTrue(facture.getArchivee());
        verify(factureRepository).save(facture);
        verify(factureRepository).updateFacture(1L);
    }


    @Test
    void cancelFacture_notFound() {
        when(factureRepository.findById(1L)).thenReturn(Optional.empty());

        service.cancelFacture(1L);

        verify(factureRepository).save(any(Facture.class));
        verify(factureRepository).updateFacture(1L);
    }


    @Test
    void retrieveFacture_found() {
        when(factureRepository.findById(1L)).thenReturn(Optional.of(facture));

        Facture result = service.retrieveFacture(1L);

        assertNotNull(result);
        verify(factureRepository).findById(1L);
    }


    @Test
    void retrieveFacture_notFound() {
        when(factureRepository.findById(1L)).thenReturn(Optional.empty());

        Facture result = service.retrieveFacture(1L);

        assertNull(result);
    }


    @Test
    void getFacturesByFournisseur_success() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));

        List<Facture> result = service.getFacturesByFournisseur(1L);

        assertEquals(1, result.size());
        verify(fournisseurRepository).findById(1L);
    }


    @Test
    void getFacturesByFournisseur_notFound() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.getFacturesByFournisseur(1L);
        });
    }


    @Test
    void assignOperateurToFacture_success() {
        when(factureRepository.findById(1L)).thenReturn(Optional.of(facture));
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        service.assignOperateurToFacture(1L, 1L);

        assertTrue(operateur.getFactures().contains(facture));
        verify(operateurRepository).save(operateur);
    }

    @Test
    void assignOperateurToFacture_notFound() {
        when(factureRepository.findById(1L)).thenReturn(Optional.empty());
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        assertThrows(IllegalArgumentException.class, () -> {
            service.assignOperateurToFacture(1L, 1L);
        });
    }

    @Test
    void pourcentageRecouvrement() {
        Date start = new Date();
        Date end = new Date();

        when(factureRepository.getTotalFacturesEntreDeuxDates(start, end)).thenReturn(100f);
        when(reglementService.getChiffreAffaireEntreDeuxDate(start, end)).thenReturn(50f);

        float result = service.pourcentageRecouvrement(start, end);

        assertEquals(50f, result);
    }

    @Test
    void pourcentageRecouvrement_divisionByZero() {
        Date start = new Date();
        Date end = new Date();

        when(factureRepository.getTotalFacturesEntreDeuxDates(start, end)).thenReturn(0f);
        when(reglementService.getChiffreAffaireEntreDeuxDate(start, end)).thenReturn(50f);

        float result = service.pourcentageRecouvrement(start, end);

        assertTrue(Float.isInfinite(result));
    }
}