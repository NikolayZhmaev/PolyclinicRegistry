package net.thumbtack.school.serviceClasses;

public enum State {
    FREE("FREE"),
    BUSY("BUSY");

    private String state;

    State(final String state) {
        this.state = state;
    }

    public String getValue() {
        return this.state;
    }
}