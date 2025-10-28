public class Course {
    String name;
    float[] grades;

    public Course(String name, float[] grades) {
        this.name = name;
        this.grades = grades;
    }

    // Méthode pour calculer la moyenne de la matière
    public float calculateCourseAverage(float[] grades) {
        // Longueur du tableau de notes de la matière
        int numberOfGrades = grades.length;
        // Créer une variable sum : somme des notes
        float sum = 0;

        // Boucler sur grades pour additionner toutes les notes et stocker le résultat dans sum
        for(int i = 0; i < numberOfGrades; i++) {
            sum += grades[i];
        }

        // Retourner la moyenne (sum / grades.length)
        return sum / numberOfGrades;
    }
}
