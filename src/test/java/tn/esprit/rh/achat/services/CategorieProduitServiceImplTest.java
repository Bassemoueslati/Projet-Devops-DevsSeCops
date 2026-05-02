package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository repository;

    @InjectMocks
    private CategorieProduitServiceImpl service;

    private CategorieProduit cp;

    @BeforeEach
    void setUp() {
        cp = new CategorieProduit();
        cp.setIdCategorieProduit(1L);
        cp.setCodeCategorie("CAT1");
        cp.setLibelleCategorie("Categorie 1");
    }

    @Test
    void retrieveAllCategorieProduits() {
        when(repository.findAll()).thenReturn(Arrays.asList(cp));

        List<CategorieProduit> result = service.retrieveAllCategorieProduits();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }


    @Test
    void addCategorieProduit() {
        when(repository.save(cp)).thenReturn(cp);

        CategorieProduit result = service.addCategorieProduit(cp);

        assertNotNull(result);
        assertEquals("CAT1", result.getCodeCategorie());
        verify(repository).save(cp);
    }

    @Test
    void deleteCategorieProduit() {
        doNothing().when(repository).deleteById(1L);

        service.deleteCategorieProduit(1L);

        verify(repository).deleteById(1L);
    }


    @Test
    void updateCategorieProduit_success() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(cp)).thenReturn(cp);

        CategorieProduit result = service.updateCategorieProduit(cp);

        assertNotNull(result);
        verify(repository).existsById(1L);
        verify(repository).save(cp);
    }

    @Test
    void updateCategorieProduit_notFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateCategorieProduit(cp);
        });

        verify(repository).existsById(1L);
        verify(repository, never()).save(any());
    }


    @Test
    void retrieveCategorieProduit_found() {
        when(repository.findById(1L)).thenReturn(Optional.of(cp));

        CategorieProduit result = service.retrieveCategorieProduit(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdCategorieProduit());
        verify(repository).findById(1L);
    }

    @Test
    void retrieveCategorieProduit_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        CategorieProduit result = service.retrieveCategorieProduit(1L);

        assertNull(result);
        verify(repository).findById(1L);
    }
}