public class CleanStringsExampleApp {
    public static void main(String[] args) {

        // BAD
        String firstName = "Paul";
        System.out.println("Bonjour " + firstName);

        // GOOD
        String firstName2 = "Jack";
        int age = 15;
        System.out.println(String.format("Bonjour %s, tu as %d ans", firstName2, age));
        System.out.printf("Rebonjour %s, tu as %d ans", firstName2, age);
    }
}