package net.thumbtack.school.dto.response;

import net.thumbtack.school.errors.ServiceErrorCode;

import java.util.Objects;

public class ErrorResponse {

    ServiceErrorCode errorCode; //error code
    String field; //field of the request that caused the error
    String message; //cause of error

    public ErrorResponse() {
    }

    public ErrorResponse(ServiceErrorCode errorCode, String field, String message) {
        this.errorCode = errorCode;
        this.field = field;
        this.message = message;
    }

    public ServiceErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ServiceErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return errorCode == that.errorCode &&
                Objects.equals(field, that.field) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, field, message);
    }
}
