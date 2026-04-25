package tn.esprit.rh.achat.dto;

public class CategorieProduitDTO {

    private Long idCategorieProduit;
    private String codeCategorie;
    private String libelleCategorie;

    public Long getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(Long idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getLibelleCategorie() {
        return libelleCategorie;
    }

    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }
}