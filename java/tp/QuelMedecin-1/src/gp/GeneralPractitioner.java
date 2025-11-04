package gp;

public class GeneralPractitioner {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private int tariff;

    public GeneralPractitioner(String _lastName, String _firstName, String _phoneNumber) {
        lastName = _lastName;
        firstName = _firstName;
        phoneNumber = _phoneNumber;
        tariff = 25;
    }

    /**
     * Affiche les informations du médecin
     */
    public String showInfo() {
        StringBuilder info = new StringBuilder();
        info.append(lastName.toUpperCase()).append(" ")
                .append(firstName).append("\n")
                .append("Téléphone : ").append(phoneNumber).append("\n")
                .append("Tarif : ").append(tariff).append("€");

       return info.toString();
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

    public int getTariff() {
        return tariff;
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

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }
}
