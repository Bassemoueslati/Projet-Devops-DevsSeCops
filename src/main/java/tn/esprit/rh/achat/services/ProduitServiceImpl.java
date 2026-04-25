package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

    private final ProduitRepository produitRepository;
    private final StockRepository stockRepository;
    private final CategorieProduitRepository categorieProduitRepository;

    
    public ProduitServiceImpl(ProduitRepository produitRepository,
                              StockRepository stockRepository,
                              CategorieProduitRepository categorieProduitRepository) {
        this.produitRepository = produitRepository;
        this.stockRepository = stockRepository;
        this.categorieProduitRepository = categorieProduitRepository;
    }

    @Override
    public List<Produit> retrieveAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        for (Produit produit : produits) {
            log.info("Produit : {}", produit);
        }
        return produits;
    }

    @Override
    @Transactional
    public Produit addProduit(Produit p) {
        return produitRepository.save(p);
    }

    @Override
    public void deleteProduit(Long produitId) {
        produitRepository.deleteById(produitId);
    }

    @Override
    public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }

    @Override
    public Produit retrieveProduit(Long produitId) {
        Produit produit = produitRepository.findById(produitId).orElse(null);
        log.info("produit : {}", produit);
        return produit;
    }

    @Override
    public void assignProduitToStock(Long idProduit, Long idStock) {

        Produit produit = produitRepository.findById(idProduit).orElse(null);
        Stock stock = stockRepository.findById(idStock).orElse(null);

        
        if (produit == null) {
            throw new IllegalArgumentException("Produit not found");
        }

        if (stock == null) {
            throw new IllegalArgumentException("Stock not found");
        }

        produit.setStock(stock);
        produitRepository.save(produit);
    }
}