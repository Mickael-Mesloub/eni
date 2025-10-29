import java.util.ArrayList;

public class Course {
    String name;
    ArrayList<Float> grades;

    public Course(String name, ArrayList<Float> grades) {
        this.name = name;
        this.grades = grades;
    }

    // Méthode pour calculer la moyenne de la matière
    public float calculateCourseAverage() {
        // Nombre de matières (= taille de la liste)
        int numberOfGrades = grades.size();
        // Créer une variable sum : somme des notes
        float sum = 0;

        // Boucler sur grades pour additionner toutes les notes et stocker le résultat dans sum
        for (float grade : grades) {
            sum += grade;
        }

        // Retourner la moyenne (somme des notes / nombre de notes)
        return sum / numberOfGrades;
    }
}
