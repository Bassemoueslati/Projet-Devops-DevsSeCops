package tn.esprit.rh.achat.dto;

import java.util.Date;

public class ProduitDTO {

    private Long idProduit;
    private String codeProduit;
    private String libelleProduit;
    private float prix;
    private Date dateCreation;
    private Date dateDerniereModification;
    private Long stockId;
    private Long categorieProduitId;

    public Long getIdProduit() { return idProduit; }
    public void setIdProduit(Long idProduit) { this.idProduit = idProduit; }

    public String getCodeProduit() { return codeProduit; }
    public void setCodeProduit(String codeProduit) { this.codeProduit = codeProduit; }

    public String getLibelleProduit() { return libelleProduit; }
    public void setLibelleProduit(String libelleProduit) { this.libelleProduit = libelleProduit; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

    public Date getDateDerniereModification() { return dateDerniereModification; }
    public void setDateDerniereModification(Date dateDerniereModification) { this.dateDerniereModification = dateDerniereModification; }

    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Long getCategorieProduitId() { return categorieProduitId; }
    public void setCategorieProduitId(Long categorieProduitId) {
        this.categorieProduitId = categorieProduitId;
    }
}