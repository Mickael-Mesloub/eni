import java.util.ArrayList;

public class TpMoyenneApp {
    public static void main(String[] args) {

        // ArrayList de notes de Français
        ArrayList<Float> frenchGrades = new ArrayList<Float>() {{
            add(12F);
            add(8F);
            add(13F);
            add(6F);
        }};

        // ArrayList de notes de Maths
        ArrayList<Float> mathsGrades = new ArrayList<Float>() {{
            add(4F);
            add(9F);
            add(16F);
            add(18F);
        }};

        // ArrayList de notes de Sport
        ArrayList<Float> gymGrades = new ArrayList<Float>() {{
            add(15F);
            add(13F);
            add(17F);
            add(2F);
            add(6F);
        }};

        // Instance de Course pour Français
        Course frenchCourse = new Course("Français", frenchGrades);

        // Instance de Course pour Maths
        Course mathsCourse = new Course("Maths", mathsGrades);

        // Instance de Course pour Sport
        Course gymCourse = new Course("Sport", gymGrades);

        // Liste des matières
        ArrayList<Course> courses = new ArrayList<Course>() {{
            add(frenchCourse);
            add(mathsCourse);
            add(gymCourse);
        }};

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