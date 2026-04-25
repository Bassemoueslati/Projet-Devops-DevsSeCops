package tn.esprit.rh.achat.dto;

public class StockDTO {

    private Long idStock;
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;

    public Long getIdStock() { return idStock; }
    public void setIdStock(Long idStock) { this.idStock = idStock; }

    public String getLibelleStock() { return libelleStock; }
    public void setLibelleStock(String libelleStock) { this.libelleStock = libelleStock; }

    public Integer getQte() { return qte; }
    public void setQte(Integer qte) { this.qte = qte; }

    public Integer getQteMin() { return qteMin; }
    public void setQteMin(Integer qteMin) { this.qteMin = qteMin; }
}