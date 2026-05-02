package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl service;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Stock A");
        stock.setQte(5);
        stock.setQteMin(10);
    }

    @Test
    void retrieveAllStocks() {
        when(stockRepository.findAll()).thenReturn(Arrays.asList(stock));

        List<Stock> result = service.retrieveAllStocks();

        assertEquals(1, result.size());
        verify(stockRepository).findAll();
    }

    @Test
    void addStock() {
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock result = service.addStock(stock);

        assertNotNull(result);
        verify(stockRepository).save(stock);
    }

    @Test
    void deleteStock() {
        doNothing().when(stockRepository).deleteById(1L);

        service.deleteStock(1L);

        verify(stockRepository).deleteById(1L);
    }

    @Test
    void updateStock() {
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock result = service.updateStock(stock);

        assertNotNull(result);
        verify(stockRepository).save(stock);
    }

    @Test
    void retrieveStock_found() {
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock result = service.retrieveStock(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdStock());
        verify(stockRepository).findById(1L);
    }

    @Test
    void retrieveStock_notFound() {
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        Stock result = service.retrieveStock(1L);

        assertNull(result);
    }

    @Test
    void retrieveStatusStock() {
        when(stockRepository.retrieveStatusStock()).thenReturn(Arrays.asList(stock));

        String result = service.retrieveStatusStock();

        assertNotNull(result);
        assertTrue(result.contains("Stock A"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("10"));

        verify(stockRepository).retrieveStatusStock();
    }

    @Test
    void retrieveStatusStock_empty() {
        when(stockRepository.retrieveStatusStock()).thenReturn(Collections.emptyList());

        String result = service.retrieveStatusStock();

        assertNotNull(result);
        assertEquals("", result); // no stock → empty message
    }
}