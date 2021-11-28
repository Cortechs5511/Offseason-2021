class Main {
    public static void main(String[] args) {

        Car myCar = new Car();
        System.out.println(myCar.carWheels());
        myCar.editName("Honda");
        myCar.printName();
    }
}

class Car {
    final static int WHEELS = 4;
    static String name;

    public void printName() {
        System.out.println(name);
    }

    public void editName(String newName) {
        name = newName;
    }

    public int carWheels() {
        return (WHEELS);
    }
}