class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // write whatever you want

        /* 
        double line comment

        Normal comment but extended
        Multiline Comment
        */

        // variables
        int donkey = 20000;
        donkey = donkey + 1;
        System.out.println(donkey);
        int height;

        // naming
        final int ROBOT_HEIGHT = 50;
        System.out.println(ROBOT_HEIGHT);
        int helloWorld = 5;

        //string
        String text = "good morning";
        System.out.println(text);

        // booleans
        boolean buttonPressed = false;

        // numbers
        height = 0;
        int letters = 26;
        int animals = 3;
        //int petals = 5.4; this won't work because 5.4 isn't an integer
        double petals = 5.4; //works because double type allows decimals
        final double pi = 3.1415;

        //conditionals
            if (donkey < 19999) {
                System.out.println("not enough donkeys");
            } else if(donkey == 19999) {
                System.out.println("almost perfect amount of donkey");
            } else if(donkey == 19999 && petals <= 5.4) {
                System.out.println("wow pretty donkey");
            } else {
                System.out.println("Too many donkey");
            };

        // if have more than 19999 donkeys, ^^^ else ^^

        // conditionals w/ grading
        double grade = 78.3; 
        if(grade <= 60) {
            System.out.println("failure");
        } else if(grade < 70) {
            System.out.println("still a failure");
        } else if(grade < 80) {
            System.out.println("slightly smarter failure");
        } else if(grade < 90) {
            System.out.println("not better than failure but close");
        } else if(grade < 100) {
            System.out.println("ehhhhhhh");
        } else if(grade == 100) {
            System.out.println("Average");
        };

        /*
        <, <=, >, >=, ==, !=, &&, ||
        true AND true ---> true
        true AND false ---> false
        true OR true ---> true
        true OR false ---> true
        */

        /*
        Create BMI calculator
        weight (kg) / height (meters) / height (in meters)

        weight / height * height
        */




         // Day 2 of Programming Training; Loops and Arrays
        /*int i=0;
        while (i < 5) {
            System.out.println(i);
            i++; 
        } */
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};

        for (int i = 0; i < cars.length; i++) {
            System.out.print(cars[i]);
        }
        for (String car: cars) {
            System.out.print(car);
        }

        // With for loops, it's "For (initial Value; Condition; how to change original value)"

        String[] someOtherLetters = {"A", "B", "C", "D", "E", "F"};

        for (int z = 0; someOtherLetters.length > z; z++) {
            System.out.println(someOtherLetters);
        }









    }
}