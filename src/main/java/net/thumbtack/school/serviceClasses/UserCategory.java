package net.thumbtack.school.serviceClasses;

public enum UserCategory {

    ADMINISTRATOR("administrator"),
    PATIENT("patient"),
    DOCTOR("doctor");

    private String categoty;

    UserCategory(final String categoty) {
        this.categoty = categoty;
    }

    public String getValue() {
        return this.categoty;
    }
}