package shared;

import java.util.ArrayList;
import java.util.List;

public interface IPerson {
    public List<Person> personalPool=new ArrayList<>();
    public void addUser(Person person);
    public void deleteUser(int rowNumber);

    void showUsers();

    void showUser(int rowNumber);

    void editUser(int rowNumber, String changingParam, String userEditModeChangedParam );
}
