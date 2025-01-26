public class Copy {

    public static void main(String[] args) throws CloneNotSupportedException {
        int[] numbers = {1,2,9};
        Person jhon = new Person("Jhon", numbers);

        Person doe = (Person) jhon.clone();

        doe.setName("Doe");
        doe.getFavoriteNumbers()[0] = 90;

        System.out.println("Jhon " + jhon);
        System.out.println("Doe " + doe);
    }

}
