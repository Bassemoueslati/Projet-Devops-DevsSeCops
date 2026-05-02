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
class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private FournisseurServiceImpl service;

    private Fournisseur fournisseur;
    private DetailFournisseur detail;
    private SecteurActivite secteur;

    @BeforeEach
    void setUp() {
        fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setSecteurActivites(new HashSet<>());

        detail = new DetailFournisseur();
        detail.setIdDetailFournisseur(1L);

        fournisseur.setDetailFournisseur(detail);

        secteur = new SecteurActivite();
        secteur.setIdSecteurActivite(1L);
    }


    @Test
    void retrieveAllFournisseurs() {
        when(fournisseurRepository.findAll()).thenReturn(Arrays.asList(fournisseur));

        List<Fournisseur> result = service.retrieveAllFournisseurs();

        assertEquals(1, result.size());
        verify(fournisseurRepository).findAll();
    }


    @Test
    void addFournisseur() {
        when(fournisseurRepository.save(any(Fournisseur.class))).thenAnswer(i -> i.getArgument(0));

        Fournisseur result = service.addFournisseur(fournisseur);

        assertNotNull(result.getDetailFournisseur());
        assertNotNull(result.getDetailFournisseur().getDateDebutCollaboration());
        verify(fournisseurRepository).save(fournisseur);
    }

    @Test
    void updateFournisseur() {
        when(detailFournisseurRepository.save(detail)).thenReturn(detail);
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Fournisseur result = service.updateFournisseur(fournisseur);

        assertNotNull(result);
        verify(detailFournisseurRepository).save(detail);
        verify(fournisseurRepository).save(fournisseur);
    }


    @Test
    void deleteFournisseur() {
        doNothing().when(fournisseurRepository).deleteById(1L);

        service.deleteFournisseur(1L);

        verify(fournisseurRepository).deleteById(1L);
    }

    @Test
    void retrieveFournisseur_found() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));

        Fournisseur result = service.retrieveFournisseur(1L);

        assertNotNull(result);
        verify(fournisseurRepository).findById(1L);
    }

    @Test
    void retrieveFournisseur_notFound() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.empty());

        Fournisseur result = service.retrieveFournisseur(1L);

        assertNull(result);
    }


    @Test
    void assignSecteurActiviteToFournisseur_success() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteur));

        service.assignSecteurActiviteToFournisseur(1L, 1L);

        assertTrue(fournisseur.getSecteurActivites().contains(secteur));
        verify(fournisseurRepository).save(fournisseur);
    }


    @Test
    void assignSecteurActiviteToFournisseur_fournisseurNotFound() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.assignSecteurActiviteToFournisseur(1L, 1L);
        });
    }


    @Test
    void assignSecteurActiviteToFournisseur_secteurNotFound() {
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.assignSecteurActiviteToFournisseur(1L, 1L);
        });
    }
}