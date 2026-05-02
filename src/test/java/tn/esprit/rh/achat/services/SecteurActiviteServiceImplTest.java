package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecteurActiviteServiceImplTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl service;

    private SecteurActivite secteur;

    @BeforeEach
    void setUp() {
        secteur = new SecteurActivite();
        secteur.setIdSecteurActivite(1L);
        secteur.setCodeSecteurActivite("SEC1");
        secteur.setLibelleSecteurActivite("Secteur Test");
    }

    @Test
    void retrieveAllSecteurActivite() {
        when(secteurActiviteRepository.findAll())
                .thenReturn(Arrays.asList(secteur));

        List<SecteurActivite> result = service.retrieveAllSecteurActivite();

        assertEquals(1, result.size());
        verify(secteurActiviteRepository).findAll();
    }

    @Test
    void addSecteurActivite() {
        when(secteurActiviteRepository.save(secteur)).thenReturn(secteur);

        SecteurActivite result = service.addSecteurActivite(secteur);

        assertNotNull(result);
        assertEquals("SEC1", result.getCodeSecteurActivite());
        verify(secteurActiviteRepository).save(secteur);
    }

    @Test
    void deleteSecteurActivite() {
        doNothing().when(secteurActiviteRepository).deleteById(1L);

        service.deleteSecteurActivite(1L);

        verify(secteurActiviteRepository).deleteById(1L);
    }

    @Test
    void updateSecteurActivite() {
        when(secteurActiviteRepository.save(secteur)).thenReturn(secteur);

        SecteurActivite result = service.updateSecteurActivite(secteur);

        assertNotNull(result);
        verify(secteurActiviteRepository).save(secteur);
    }

    @Test
    void retrieveSecteurActivite_found() {
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteur));

        SecteurActivite result = service.retrieveSecteurActivite(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdSecteurActivite());
        verify(secteurActiviteRepository).findById(1L);
    }

    @Test
    void retrieveSecteurActivite_notFound() {
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.empty());

        SecteurActivite result = service.retrieveSecteurActivite(1L);

        assertNull(result);
    }
}