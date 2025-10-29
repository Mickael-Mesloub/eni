public class TpEntrepriseApp {
    public static void main(String[] args) {

        // Instances des salariés

        // Oranges
        Employee orangesEmployee1 = new Employee("Grieeman", "Natacha", 32);
        Employee orangesEmployee2 = new Employee("Dupont", "Jean-Luc", 37);

        Employee[] orangesEmployees = new Employee[]{orangesEmployee1, orangesEmployee2};

        // GoGoDev
        Employee gogodevEmployee = new Employee("Brechet", "Stephane", 29);

        Employee[] gogodevEmployees = new Employee[]{gogodevEmployee};

        // Next Digital
        Employee nextdigitalEmployee1 = new Employee("Lemoine", "Sasha", 24);
        Employee nextdigitalEmployee2 = new Employee("Durand", "Esteban", 31);
        Employee nextdigitalEmployee3 = new Employee("Laporte", "Mélanie", 34);

        Employee[] nextdigitalEmployees = new Employee[]{nextdigitalEmployee1, nextdigitalEmployee2, nextdigitalEmployee3};

        // Instances des adresses
        Address orangesAddress = new Address("44300", "Nantes");
        Address gogodevAddress = new Address("29820", "Bohars");
        Address nextdigitalAddress = new Address("29770", "Cléden Cap Sizun");

        // Instances des entreprises
        Company oranges = new Company("Oranges", orangesAddress, orangesEmployees);
        Company gogodev = new Company("GoGoDev", gogodevAddress, gogodevEmployees);
        Company nextdigital = new Company("NextDigital", nextdigitalAddress, nextdigitalEmployees);

        Company[] companies = new Company[]{oranges, gogodev, nextdigital};

        getCompaniesInfo(companies);
    }

    static void getCompaniesInfo(Company[] companies) {
        // Boucler sur les entreprises pour afficher leurs infos
        for (int i = 0; i < companies.length; i++) {
            companies[i].showCompanyInfo();
        }
    }
}