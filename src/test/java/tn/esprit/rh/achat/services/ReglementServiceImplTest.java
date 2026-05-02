package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReglementServiceImplTest {

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl service;

    private Reglement reglement;

    @BeforeEach
    void setUp() {
        reglement = new Reglement();
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(100f);
    }

    @Test
    void retrieveAllReglements() {
        when(reglementRepository.findAll()).thenReturn(Arrays.asList(reglement));

        List<Reglement> result = service.retrieveAllReglements();

        assertEquals(1, result.size());
        verify(reglementRepository).findAll();
    }

    @Test
    void addReglement() {
        when(reglementRepository.save(reglement)).thenReturn(reglement);

        Reglement result = service.addReglement(reglement);

        assertNotNull(result);
        assertEquals(100f, result.getMontantPaye());
        verify(reglementRepository).save(reglement);
    }

    @Test
    void retrieveReglement_found() {
        when(reglementRepository.findById(1L)).thenReturn(Optional.of(reglement));

        Reglement result = service.retrieveReglement(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdReglement());
        verify(reglementRepository).findById(1L);
    }

    @Test
    void retrieveReglement_notFound() {
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty());

        Reglement result = service.retrieveReglement(1L);

        assertNull(result);
    }

    @Test
    void retrieveReglementByFacture() {
        when(reglementRepository.retrieveReglementByFacture(1L))
                .thenReturn(Arrays.asList(reglement));

        List<Reglement> result = service.retrieveReglementByFacture(1L);

        assertEquals(1, result.size());
        verify(reglementRepository).retrieveReglementByFacture(1L);
    }

    @Test
    void getChiffreAffaireEntreDeuxDate() {
        Date start = new Date();
        Date end = new Date();

        when(reglementRepository.getChiffreAffaireEntreDeuxDate(start, end))
                .thenReturn(500f);

        float result = service.getChiffreAffaireEntreDeuxDate(start, end);

        assertEquals(500f, result);
    }
}