package tn.esprit.rh.achat.dto;

import java.util.Date;

public class FactureDTO {

    private Long idFacture;
    private float montantRemise;
    private float montantFacture;
    private Date dateCreationFacture;
    private Date dateDerniereModificationFacture;
    private Boolean archivee;
    private Long fournisseurId;

    public Long getIdFacture() { return idFacture; }
    public void setIdFacture(Long idFacture) { this.idFacture = idFacture; }

    public float getMontantRemise() { return montantRemise; }
    public void setMontantRemise(float montantRemise) { this.montantRemise = montantRemise; }

    public float getMontantFacture() { return montantFacture; }
    public void setMontantFacture(float montantFacture) { this.montantFacture = montantFacture; }

    public Date getDateCreationFacture() { return dateCreationFacture; }
    public void setDateCreationFacture(Date dateCreationFacture) { this.dateCreationFacture = dateCreationFacture; }

    public Date getDateDerniereModificationFacture() { return dateDerniereModificationFacture; }
    public void setDateDerniereModificationFacture(Date dateDerniereModificationFacture) { this.dateDerniereModificationFacture = dateDerniereModificationFacture; }

    public Boolean getArchivee() { return archivee; }
    public void setArchivee(Boolean archivee) { this.archivee = archivee; }

    public Long getFournisseurId() { return fournisseurId; }
    public void setFournisseurId(Long fournisseurId) { this.fournisseurId = fournisseurId; }
}