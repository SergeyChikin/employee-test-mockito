package pro.sky.employeetestmockito.exceptions;

public class EmployeeAlreadyAddException extends RuntimeException{
    public EmployeeAlreadyAddException(String message) {
        super(message);
    }
}
