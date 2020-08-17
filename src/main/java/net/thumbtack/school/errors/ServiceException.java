package net.thumbtack.school.errors;

public class ServiceException extends Exception {

    private ServiceErrorCode errorCode;

    public ServiceErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ServiceErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(ServiceErrorCode errorCode) {
        super(errorCode.getErrorString());
        setErrorCode(errorCode);
    }

    public ServiceException(ServiceErrorCode errorCode, String param) {
        super(String.format(errorCode.getErrorString(), param));
    }

    public ServiceException(ServiceErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorString(), cause);
    }
}