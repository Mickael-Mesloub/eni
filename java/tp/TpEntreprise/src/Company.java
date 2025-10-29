import java.util.ArrayList;

public class Company {
    String name;
    Address address;
    ArrayList<Employee> employees;

    public Company(String name, Address address, ArrayList<Employee> employees) {
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public void showCompanyInfo() {
        int numberOfEmployees = employees.size();
        StringBuilder companyInfo = new StringBuilder(String.format("■ %s (%s - %s), %d salarié(e)s", name, address.cp, address.city, numberOfEmployees));

        // Boucler sur les employees pour afficher la liste au format :
        // salarié [i] : "lastName firstName" age ans
        for (int i = 0; i < numberOfEmployees; i++) {
            Employee employee = employees.get(i);
            companyInfo.append(String.format("\n □ salarié %d : \"%s %s\" %d ans", i + 1, employee.firstName, employee.lastName, employee.age));
        }

        companyInfo.append("\n");

        System.out.println(companyInfo);

    }
}
