package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements IPerson, Serializable {
    private String personName;
    private int personNumber;
    public List<Person> personalPool=new ArrayList<>();
    public Person(String personName, int personNumber) {
        this.personName = personName;
        this.personNumber = personNumber;
    }

    public Person() {
    }

    @Override
    public void addUser(Person person) {
        personalPool.add(person);
    }

    @Override
    public void deleteUser(int personNumber) {

    }
}