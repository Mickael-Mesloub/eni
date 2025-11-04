package patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private Gender gender;
    private long nationalInsuranceNumber;
    private LocalDate birthDate;
    private String comments;

    public Patient(String _lastName, String _firstName, String _phoneNumber, Gender _gender, long _nationalInsuranceNumber, LocalDate _birthDate, String _comments ) {
        lastName = _lastName;
        firstName = _firstName;
        phoneNumber = _phoneNumber;
        gender = _gender;
       nationalInsuranceNumber = _nationalInsuranceNumber;
       birthDate = _birthDate;
       comments = _comments;
    }

    public String showInfo() {
        StringBuilder info = new StringBuilder();
        info.append(lastName.toUpperCase()).append(" ")
                .append(firstName).append("\n")
                .append("Téléphone : ").append(phoneNumber).append("\n")
                .append("Sexe : ").append(getDisplayGender()).append("\n")
                .append("Numéro de sécurité sociale : ").append(nationalInsuranceNumber).append("\n")
                .append("Date de naissance : ").append(birthDate.format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"))).append("\n")
                .append("Commentaires : ").append(getDisplayComments());

        return info.toString();
    }

    /**
     * Retourne le texte à afficher pour le sexe
     * @return Une String : "Féminin" si Gender.F, et "Masculin" si Gender.M
     */
    public String getDisplayGender() {
        return (gender == Gender.F) ? "Féminin" : "Masculin";
    }

    /**
     * Retourne le texte à afficher pour les commentaires
     * @return Le commentaire si fournit, sinon, "[aucun commentaire]" comme valeur par défaut
     */
    public String getDisplayComments() {
        return (comments.isBlank()) ? "[aucun commentaire]" : comments;
    }

    // ---------------------------------------- \\
    // ------------ GETTERS SETTERS ----------- \\
    // ---------------------------------------- \\

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(long nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
