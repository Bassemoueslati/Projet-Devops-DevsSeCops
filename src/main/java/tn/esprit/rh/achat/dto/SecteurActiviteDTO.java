package tn.esprit.rh.achat.dto;

public class SecteurActiviteDTO {

    private Long idSecteurActivite;
    private String codeSecteurActivite;
    private String libelleSecteurActivite;

    public Long getIdSecteurActivite() { return idSecteurActivite; }
    public void setIdSecteurActivite(Long idSecteurActivite) { this.idSecteurActivite = idSecteurActivite; }

    public String getCodeSecteurActivite() { return codeSecteurActivite; }
    public void setCodeSecteurActivite(String codeSecteurActivite) {
        this.codeSecteurActivite = codeSecteurActivite;
    }

    public String getLibelleSecteurActivite() { return libelleSecteurActivite; }
    public void setLibelleSecteurActivite(String libelleSecteurActivite) {
        this.libelleSecteurActivite = libelleSecteurActivite;
    }
}