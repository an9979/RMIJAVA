package shared;


public interface IPerson {
    void addUser(Person person);
    void deleteUser(int rowNumber);

    void showUsers();

    void showUser(int rowNumber);

    void editUser(int rowNumber, String changingParam, String userEditModeChangedParam );
}
