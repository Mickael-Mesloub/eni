import java.util.ArrayList;
import java.util.List;

public class TpMoyenneApp {
    public static void main(String[] args) {

        // ArrayList de notes de Français
        ArrayList<Float> frenchGrades = new ArrayList<Float>(List.of(12f,8f,13f,6f));

        // ArrayList de notes de Maths
        ArrayList<Float> mathsGrades = new ArrayList<Float>(List.of(4f,9f,16f,18f));

        // ArrayList de notes de Sport
        ArrayList<Float> gymGrades = new ArrayList<Float>(List.of(15f,13f,17f,2f,6f));

        // Instance de Course pour Français
        Course frenchCourse = new Course("Français", frenchGrades);

        // Instance de Course pour Maths
        Course mathsCourse = new Course("Maths", mathsGrades);

        // Instance de Course pour Sport
        Course gymCourse = new Course("Sport", gymGrades);

        // Liste des matières
        ArrayList<Course> courses = new ArrayList<Course>(List.of(frenchCourse, mathsCourse, gymCourse));

        // Afficher la moyenne générale
        System.out.println("Moyenne générale : " + Math.round(calculateGpa(courses) * 100.0) / 100.0 + "/20");
    }

    // Fonction pour calculer la moyenne générale (gpa en anglais)
    static float calculateGpa(ArrayList<Course> courses) {
        // Nombre de cours
        int numberOfCourses = courses.size();

        // Somme des moyennes
        float sumAverageGrades = 0;

        // Boucler sur la liste des matières
        for (Course course : courses) {

            // Afficher la moyenne de chaque matière
            System.out.println("Moyenne " + course.name + " : " + Math.round(course.calculateCourseAverage() * 100.0) / 100.0 + "/20");

            // Calculer la somme des moyennes de tous les cours
            sumAverageGrades += course.calculateCourseAverage();
        }
        // Retourner la moyenne générale (total des moyennes / nombre de cours (de notes))
        return sumAverageGrades / numberOfCourses;
    }

}