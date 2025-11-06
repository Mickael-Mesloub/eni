package model;

import java.util.Comparator;

public class MedecinComparator implements Comparator<MedecinSpecialiste> {

    @Override
    public int compare(MedecinSpecialiste medSpe1, MedecinSpecialiste medSpe2){
        return medSpe1.getSpecialite().compareTo(medSpe2.getSpecialite());
    };
}
