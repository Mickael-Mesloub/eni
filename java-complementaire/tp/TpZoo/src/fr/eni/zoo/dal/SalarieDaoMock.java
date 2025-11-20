package fr.eni.zoo.dal;

import fr.eni.zoo.bo.Salarie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalarieDaoMock {
    private List<Salarie> salaries = new ArrayList<Salarie>();
    private int idIndex;

    public SalarieDaoMock() {
    }

    public SalarieDaoMock(List<Salarie> salaries, int idIndex) {
        this.salaries = salaries;
        this.idIndex = idIndex;
    }

    public void ajoutSalarie(Salarie salarie){
        idIndex++;
        salarie.setId(idIndex);
        salaries.add(salarie);
    }
    public void supprimerSalarie(int id){
        salaries.removeIf(s -> s.getId() == id);
    }

    public void majSalarie(Salarie salarie){
        salaries.set(getIdIndex() - 1, salarie);
    }

    @Override
    public String toString() {
        return "SalarieDaoMock{" +
                "salaries=" + salaries +
                ", idIndex=" + idIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SalarieDaoMock that)) return false;
        return idIndex == that.idIndex && Objects.equals(salaries, that.salaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaries, idIndex);
    }

    public List<Salarie> getSalaries() {
        return salaries;
    }

    public Salarie getSalarie(int id) {
        return salaries.stream().filter(salarie -> salarie.getId() == id).findAny().orElse(null);
    }

    public int getIdIndex() {
        return idIndex;
    }

}
