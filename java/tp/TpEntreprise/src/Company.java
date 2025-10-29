public class Company {
    String name;
    Address address;
    Employee[] employees;

    public Company(String name, Address address, Employee[] employees) {
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public void showCompanyInfo() {
        StringBuilder companyInfo = new StringBuilder(String.format("%s (%s - %s), %d salarié(e)s", name, address.cp, address.city, employees.length));

        // Boucler sur les employees pour afficher la liste au format :
        // salarié [i] : "lastName firstName" age ans

        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            companyInfo.append(String.format("\n - salarié %d : \"%s %s\" %d ans", i + 1, employee.firstName, employee.lastName, employee.age));
        }

        System.out.println(companyInfo);

    }
}
