package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl service;

    private Operateur operateur;

    @BeforeEach
    void setUp() {
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("Malek");
        operateur.setPrenom("Test");
    }

    @Test
    void retrieveAllOperateurs() {
        when(operateurRepository.findAll()).thenReturn(Arrays.asList(operateur));

        List<Operateur> result = service.retrieveAllOperateurs();

        assertEquals(1, result.size());
        verify(operateurRepository).findAll();
    }

    @Test
    void addOperateur() {
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = service.addOperateur(operateur);

        assertNotNull(result);
        assertEquals("Malek", result.getNom());
        verify(operateurRepository).save(operateur);
    }

    @Test
    void deleteOperateur() {
        doNothing().when(operateurRepository).deleteById(1L);

        service.deleteOperateur(1L);

        verify(operateurRepository).deleteById(1L);
    }

    @Test
    void updateOperateur() {
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = service.updateOperateur(operateur);

        assertNotNull(result);
        verify(operateurRepository).save(operateur);
    }

    @Test
    void retrieveOperateur_found() {
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        Operateur result = service.retrieveOperateur(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdOperateur());
        verify(operateurRepository).findById(1L);
    }

    @Test
    void retrieveOperateur_notFound() {
        when(operateurRepository.findById(1L)).thenReturn(Optional.empty());

        Operateur result = service.retrieveOperateur(1L);

        assertNull(result);
    }
}