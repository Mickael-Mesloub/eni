public class Main {
    public static void main(String[] args) {

        // Tableau de notes de Français
        float[] frenchGrades = new float[]{12, 8, 13, 6};

        // Tableau de notes de Maths
        float[] mathsGrades = new float[]{4, 9, 16, 18};

        // Tableau de notes de Sport
        float[] gymGrades = new float[]{15, 13, 17, 2, 6};

        // Instance de Course pour Français
        Course frenchCourse = new Course("Français", frenchGrades);

        // Instance de Course pour Maths
        Course mathsCourse = new Course("Maths", mathsGrades);

        // Instance de Course pour Sport
        Course gymCourse = new Course("Sport", gymGrades);

        // Liste des matières
        Course[] courses = new Course[]{frenchCourse, mathsCourse, gymCourse};

        // Afficher la moyenne générale
        System.out.println("Moyenne générale : " + Math.round(calculateGpa(courses) * 100.0) / 100.0 + "/20");
    }

    // Fonction pour calculer la moyenne générale (gpa en anglais)
    static float calculateGpa(Course[] courses) {
        // Nombre de cours
        int numberOfCourses = courses.length;

        // Somme des moyennes
        float sumAverageGrades = 0;

        // Nombre total de notes (toutes les matières additionnées)
        int totalNumberOfGrades = 0;

        // Boucler sur la liste des matières
        for(int i = 0; i < numberOfCourses; i++) {

            // Je stocke la matière dans une variable pour une meilleure lisibilité du code
            Course course = courses[i];

            // Afficher la moyenne de chaque matière
            System.out.println("Moyenne " + course.name + " : " + Math.round(course.calculateCourseAverage() * 100.0) / 100.0 + "/20");

            for(int j = 0; j < course.grades.length; j++) {
                // Pour chaque matière, calculer sa moyenne
                // et stocker la somme des moyennes de toutes les matières dans une variable sumAverageGrades
                sumAverageGrades += course.calculateCourseAverage();

                // Incrémenter le nombre total de notes à chaque itération
                totalNumberOfGrades++;
            }
        }

        // Retourner la moyenne générale (total des moyennes / nombre total de notes)
        return sumAverageGrades / totalNumberOfGrades;

    }

}