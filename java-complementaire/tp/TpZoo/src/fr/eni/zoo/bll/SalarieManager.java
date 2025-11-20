package fr.eni.zoo.bll;

import fr.eni.zoo.dal.SalarieDaoMock;

public class SalarieManager extends SalarieDaoMock {
    public SalarieManager() {
    }

    public double getMasseSalariale() {
        return getSalaries().stream().mapToDouble(s -> (double)s.getSalaire()).sum();
    }

    public double getMoyenneSalariale(){
        return getMasseSalariale() / getSalaries().size();
    }
}
