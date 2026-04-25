package tn.esprit.rh.achat.dto;

import tn.esprit.rh.achat.entities.CategorieFournisseur;

public class FournisseurDTO {

    private Long idFournisseur;
    private String code;
    private String libelle;
    private CategorieFournisseur categorieFournisseur;

    public Long getIdFournisseur() { return idFournisseur; }
    public void setIdFournisseur(Long idFournisseur) { this.idFournisseur = idFournisseur; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public CategorieFournisseur getCategorieFournisseur() { return categorieFournisseur; }
    public void setCategorieFournisseur(CategorieFournisseur categorieFournisseur) {
        this.categorieFournisseur = categorieFournisseur;
    }
}