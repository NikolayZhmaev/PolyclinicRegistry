package net.thumbtack.school.dto.response;

import java.util.Objects;

public class RegisterAdministratorDtoResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String position;

    public RegisterAdministratorDtoResponse() {
    }

    public RegisterAdministratorDtoResponse(int id, String firstName, String lastName, String patronymic, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterAdministratorDtoResponse)) return false;
        RegisterAdministratorDtoResponse that = (RegisterAdministratorDtoResponse) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, position);
    }
}
