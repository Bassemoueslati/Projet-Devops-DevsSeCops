package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProduitServiceImpl service;

    private Produit produit;
    private Stock stock;

    @BeforeEach
    void setUp() {
        produit = new Produit();
        produit.setIdProduit(1L);
        produit.setLibelleProduit("Test Produit");

        stock = new Stock();
        stock.setIdStock(1L);
    }

    @Test
    void retrieveAllProduits() {
        when(produitRepository.findAll()).thenReturn(java.util.Arrays.asList(produit));

        List<Produit> result = service.retrieveAllProduits();

        assertEquals(1, result.size());
        verify(produitRepository).findAll();
    }


    @Test
    void addProduit() {
        when(produitRepository.save(produit)).thenReturn(produit);

        Produit result = service.addProduit(produit);

        assertNotNull(result);
        verify(produitRepository).save(produit);
    }


    @Test
    void deleteProduit() {
        doNothing().when(produitRepository).deleteById(1L);

        service.deleteProduit(1L);

        verify(produitRepository).deleteById(1L);
    }

    @Test
    void updateProduit() {
        when(produitRepository.save(produit)).thenReturn(produit);

        Produit result = service.updateProduit(produit);

        assertNotNull(result);
        verify(produitRepository).save(produit);
    }


    @Test
    void retrieveProduit_found() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        Produit result = service.retrieveProduit(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdProduit());
        verify(produitRepository).findById(1L);
    }

    @Test
    void retrieveProduit_notFound() {
        when(produitRepository.findById(1L)).thenReturn(Optional.empty());

        Produit result = service.retrieveProduit(1L);

        assertNull(result);
    }

    @Test
    void assignProduitToStock_success() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        service.assignProduitToStock(1L, 1L);

        assertEquals(stock, produit.getStock());
        verify(produitRepository).save(produit);
    }

    @Test
    void assignProduitToStock_produitNotFound() {
        when(produitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.assignProduitToStock(1L, 1L);
        });
    }

    @Test
    void assignProduitToStock_stockNotFound() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.assignProduitToStock(1L, 1L);
        });
    }
}