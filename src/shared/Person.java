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

    public String getPersonName() {
        return personName;
    }

    private void setPersonName(String personName) {
        this.personName = personName;
    }

    private void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    @Override
    public void addUser(Person person) {
        personalPool.add(person);
    }

    @Override
    public void deleteUser(int personNumber) {
        personalPool.remove(personNumber);
    }

    @Override
    public void showUsers() {
        for (Person person:personalPool){
            System.out.println("ID: "+person.getPersonNumber()+"\n"+"Name: "+person.getPersonName());
        }
    }

    @Override
    public void showUser(int rowNumber) {
        System.out.println("ID: "+personalPool.get(rowNumber).getPersonNumber()+"\n"+"Name: "+personalPool.get(rowNumber).getPersonName());
    }

    @Override
    public void editUser(int rowNumber, String changingParam, String userEditModeChangedParam) {
        if (changingParam.equals("nameChange")){
            personalPool.get(rowNumber).setPersonName(userEditModeChangedParam);
        }
        else {
            personalPool.get(rowNumber).setPersonNumber(Integer.parseInt(userEditModeChangedParam));
        }
    }
}
