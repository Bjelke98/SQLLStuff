package test.dbApp.plattfot;

import java.util.ArrayList;
import java.util.List;

public class Medlem {
    private String epost;
    private String navn;
    private String adresse;
    private String tlf;

    public Medlem(String epost, String navn, String adresse, String tlf) {
        this.epost = epost;
        this.navn = navn;
        this.adresse = adresse;
        this.tlf = tlf;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public List<String> toStringList(){
        List<String> l = new ArrayList<>();
        l.add(epost);
        l.add(navn);
        l.add(adresse);
        l.add(tlf);
        return l;
    }

    @Override
    public String toString() {
        return "Medlem{" +
                "epost='" + epost + '\'' +
                ", navn='" + navn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tlf='" + tlf + '\'' +
                '}';
    }
}
