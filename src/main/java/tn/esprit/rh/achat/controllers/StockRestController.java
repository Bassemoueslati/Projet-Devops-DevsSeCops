package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import tn.esprit.rh.achat.dto.StockDTO;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;

import java.util.List;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class StockRestController {

    private final IStockService stockService;

    public StockRestController(IStockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/retrieve-all-stocks")
    public List<Stock> getStocks() {
        return stockService.retrieveAllStocks();
    }

    @GetMapping("/retrieve-stock/{stock-id}")
    public Stock retrieveStock(@PathVariable("stock-id") Long stockId) {
        return stockService.retrieveStock(stockId);
    }

	@PostMapping("/add-stock")
	public Stock addStock(@RequestBody StockDTO dto) {
		Stock s = new Stock();
		s.setIdStock(dto.getIdStock());
		s.setLibelleStock(dto.getLibelleStock());
		s.setQte(dto.getQte());
		s.setQteMin(dto.getQteMin());
		return stockService.addStock(s);
	}

    @DeleteMapping("/remove-stock/{stock-id}")
    public void removeStock(@PathVariable("stock-id") Long stockId) {
        stockService.deleteStock(stockId);
    }

	@PutMapping("/modify-stock")
	public Stock modifyStock(@RequestBody StockDTO dto) {
		Stock s = new Stock();
		s.setIdStock(dto.getIdStock());
		s.setLibelleStock(dto.getLibelleStock());
		s.setQte(dto.getQte());
		s.setQteMin(dto.getQteMin());
		return stockService.updateStock(s);
	}
}