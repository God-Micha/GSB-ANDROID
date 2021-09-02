package micha.gsbandroidapplication.Modele;

public class Medecin {

    private String medNom, medPrenom, medAddr, medSpecialite, medNumTel;

    public Medecin() {
    }

    public Medecin(String medNom, String medPrenom, String medAddr, String medSpecialite, String medNumTel) {
        this.medNom = medNom;
        this.medPrenom = medPrenom;
        this.medAddr = medAddr;
        this.medSpecialite = medSpecialite;
        this.medNumTel = medNumTel;
    }

    public Medecin(String medNom, String medPrenom, String medAddr, String medNumTel) {
        this.medNom = medNom;
        this.medPrenom = medPrenom;
        this.medAddr = medAddr;
        this.medNumTel = medNumTel;
    }

    public String getMedNom() {
        return medNom;
    }

    public void setMedNom(String medNom) {
        this.medNom = medNom;
    }

    public String getMedPrenom() {
        return medPrenom;
    }

    public void setMedPrenom(String medPrenom) {
        this.medPrenom = medPrenom;
    }

    public String getMedAddr() {
        return medAddr;
    }

    public void setMedAddr(String medAddr) {
        this.medAddr = medAddr;
    }

    public String getMedSpecialite() {
        return medSpecialite;
    }

    public void setMedSpecialite(String medSpecialite) {
        this.medSpecialite = medSpecialite;
    }

    public String getMedNumTel() {
        return medNumTel;
    }

    public void setMedNumTel(String medNumTel) {
        this.medNumTel = medNumTel;
    }

    public String getInfos(){
        String infos =  this.medNom + " " + this.medPrenom + "\r\n" + this.medAddr + "\r\n" + this.medNumTel;
        if(this.medSpecialite != null){
            infos += "\r\n" + this.medSpecialite;
        }
        return infos;
    }
}
