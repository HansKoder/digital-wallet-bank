import java.util.Arrays;

public class Person implements Cloneable {

    private String name;
    private int[] favoriteNumbers;

    public Person(String name, int[] favoriteNumbers) {
        this.name = name;
        this.favoriteNumbers = favoriteNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getFavoriteNumbers() {
        return favoriteNumbers;
    }

    public void setFavoriteNumbers(int[] favoriteNumbers) {
        this.favoriteNumbers = favoriteNumbers;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person copy = (Person) super.clone();
        copy.setFavoriteNumbers(copy.getFavoriteNumbers().clone());
        return copy;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", favoriteNumbers=" + Arrays.toString(favoriteNumbers) +
                '}';
    }
}
