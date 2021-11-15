class Main {
    public static void main(String[] args) {
        // An example of a print statement
        System.out.println("Hello world");

        // write whatever you want

        /*
        double line comment
        basically a standard comment but extended
        multi line comment
        */

        // variables
        int age = 17;
        age = age + 1;
        System.out.println(age);
        int height;

        // naming
        final int WHEEL_DIAMETER = 30;
        System.out.println(WHEEL_DIAMETER);
        int helloWorld = 5;

        // String
        String text = "good morning";
        System.out.println(text);

        // Booleans
        boolean buttonPressed = false;

        // Numbers
        height = 0;
        int letters = 26;
        int animals = 3;
        //int petals = 5.4; //this doesn't work because 5.4 isn't an integer
        double petals = 5.4; //this works because 5.4 is not an integer and is a double
        final double PI = 3.1415;

        // conditionals
        if (animals < 3) {
            System.out.println("We have less than 3 animals");
        } else if (animals == 3 && petals == 5.4) {
            System.out.println("We have 3 animals and 5.4 petals");
        } else if (animals == 3) {
            System.out.println("We have 3 animals");
        } else {
            System.out.println("We have more than 3 animals");
        }
        // if we have less than 3 animals: say ^^, otherwise say ^^^

        // conditionals w/ grading
        double grade = 78.3;
        if (grade < 60) {
            System.out.println("you failed");
        } else if (grade < 70) {
            System.out.println("D");
        } else if (grade < 80) {
            System.out.println("C");
        } else if (grade < 90) {
            System.out.println("B");
        } else {
            System.out.println("A");
        }

        if (grade < 100) {
            System.out.println("A");
        } else if (grade < 90) {
            System.out.println("B");
        } else if (grade < 80) {
            System.out.println("C");
        }

        if (grade < 100) {
            System.out.println("A");
        }
        if (grade < 90) {
            System.out.println("B");
        }
        if (grade < 80) {
            System.out.println("C");
        }
        if (grade != 48.3) {
            System.out.println("hello");
        }

        /*
         <, <=, >, >=, ==, !=, &&, ||
         true AND true --> true
         true AND false --> false
         true OR true --> true
         true OR false --> true
         || OR symbol
         && AND symbol
        */

        /*
         AND --> all have to be true for it to output true
         OR --> at least one has to be true for it to output true
        */

        /*
         create a BMI calculator in Java
         weight (in kg) / height (in meters) / height (in meters)
         weight / (height * height)
        */
    }
}