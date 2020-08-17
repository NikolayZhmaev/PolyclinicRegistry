package net.thumbtack.school.models;

import net.thumbtack.school.serviceClasses.UserCategory;

import java.util.Objects;

/*creating a separate User class, adding data to it that is shared by all server users (patients, administrators , and doctors).
 Adding the "category" field, we will use it to determine which category the user belongs to*/

public class User {

    private int id;
    private UserCategory category;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String password;

    public User(int id, UserCategory category, String firstName, String lastName, String patronymic, String login, String password) {
        this.id = id;
        this.category = category;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
    }

    public User(int id, String category, String firstName, String lastName, String patronymic, String login, String password) {
        this.id = id;
        setCategory(category);
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
    }

    public User(UserCategory category, String firstName, String lastName, String patronymic, String login, String password) {
        this(0, category, firstName, lastName, patronymic, login, password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserCategory getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = UserCategory.valueOf(category);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(category, user.category) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, firstName, lastName, patronymic, login, password);
    }
}