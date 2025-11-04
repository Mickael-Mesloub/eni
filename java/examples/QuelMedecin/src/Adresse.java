import java.time.format.DateTimeFormatter;

public class Adresse {
    private String mentionsComplementaires;
    private int numero;
    private String complementNumero;
    private String libelleDeVoie;
    private int codePostal;
    private String nomCommune;

    public Adresse(String _mentionsComplementaires,
                   int _numero,
                   String _complementNumero,
                   String _libelleDeVoie,
                   int _codePostal,
                   String _nomCommune) {
        mentionsComplementaires = _mentionsComplementaires;
        numero = _numero;
        complementNumero = _complementNumero;
        libelleDeVoie = _libelleDeVoie;
        codePostal = _codePostal;
        nomCommune = _nomCommune;
    }

    public Adresse(int _numero,
           String _complementNumero,
            String _libelleDeVoie,
            int _codePostal,
            String _nomCommune) {
        numero = _numero;
        complementNumero = _complementNumero;
        libelleDeVoie = _libelleDeVoie;
        codePostal = _codePostal;
        nomCommune = _nomCommune;
    }

    public void afficher() {
        StringBuilder info = new StringBuilder();
        info.append(afficherMentionsComplementaires())
                .append(getNumero()).append(getComplementNumero()).append(" ").append(" ").append(getLibelleDeVoie()).append("\n")
                .append(getCodePostal()).append(" ").append(getNomCommune().toUpperCase());

        System.out.println(info.toString());
    }

    public String afficherMentionsComplementaires() {
        return (mentionsComplementaires != null) ? String.format("%s\n", mentionsComplementaires) : "";
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public String getMentionsComplementaires() {
        return mentionsComplementaires;
    }

    public void setMentionsComplementaires(String mentionsComplementaires) {
        this.mentionsComplementaires = mentionsComplementaires;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplementNumero() {
        return complementNumero;
    }

    public void setComplementNumero(String complementNumero) {
        this.complementNumero = complementNumero;
    }

    public String getLibelleDeVoie() {
        return libelleDeVoie;
    }

    public void setLibelleDeVoie(String libelleDeVoie) {
        this.libelleDeVoie = libelleDeVoie;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }
}
