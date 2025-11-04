public class GeneralPractitioner {
    private final String lastName;
    private final String firstName;
    private final String phoneNumber;
    private final int tariff;

    public GeneralPractitioner(String _lastName, String _firstName, String _phoneNumber) {
        lastName = _lastName;
        firstName = _firstName;
        phoneNumber = _phoneNumber;
        tariff = 25;
    }

    /**
     * Affiche les informations du médecin
     */
    public void showInfo() {
        StringBuilder info = new StringBuilder();
        info.append(lastName.toUpperCase()).append(" ")
                .append(firstName).append("\n")
                .append("Téléphone : ").append(phoneNumber).append("\n")
                .append("Tarif : ").append(tariff).append("€")
                .append("\n------------------------------------------------------------------");

        System.out.println(info);
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
}
