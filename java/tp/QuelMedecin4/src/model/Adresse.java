package model;

public class Adresse {
    private String mentionsComplementaires;
    private final int numVoie;
    private final String complementNumVoie;
    private final String libelleVoie;
    private final int codePostal;
    private final String nomCommune;

    public Adresse(String mentionsComplementaires, int numVoie, String complementNumVoie, String libelleVoie, int codePostal, String nomCommune) {
        this.mentionsComplementaires = mentionsComplementaires;
        this.numVoie = numVoie;
        this.complementNumVoie = complementNumVoie;
        this.libelleVoie = libelleVoie;
        this.codePostal = codePostal;
        this.nomCommune = nomCommune;
    }

    public Adresse(int numVoie, String complementNumVoie, String libelleVoie, int codePostal, String nomCommune){
        this.numVoie = numVoie;
        this.complementNumVoie = (complementNumVoie != null) ? complementNumVoie : "";
        this.libelleVoie = libelleVoie;
        this.codePostal = codePostal;
        this.nomCommune = nomCommune;
    }

    private String formatMentionsComplementaires(){
        return (getMentionsComplementaires() != null) ? String.format("%s \n", getMentionsComplementaires()) : "";
    }

    public void afficher(){
        StringBuilder info = new StringBuilder();
        info.append(formatMentionsComplementaires())
                .append(getNumVoie()).append(getComplementNumVoie()).append(" ").append(getLibelleVoie()).append("\n")
                .append(getCodePostal()).append(" ").append(getNomCommune().toUpperCase());

        System.out.println( info);
    }

    public String getAdresseInfo() {
        StringBuilder info = new StringBuilder();
        info.append(formatMentionsComplementaires())
                .append(getNumVoie()).append(getComplementNumVoie()).append(" ").append(getLibelleVoie()).append("\n")
                .append(getCodePostal()).append(" ").append(getNomCommune().toUpperCase());

       return info.toString();
    }

    public String getMentionsComplementaires() {
        return mentionsComplementaires;
    }

    public int getNumVoie() {
        return numVoie;
    }

    public String getComplementNumVoie() {
        return complementNumVoie;
    }

    public String getLibelleVoie() {
        return libelleVoie;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getNomCommune() {
        return nomCommune;
    }
}
