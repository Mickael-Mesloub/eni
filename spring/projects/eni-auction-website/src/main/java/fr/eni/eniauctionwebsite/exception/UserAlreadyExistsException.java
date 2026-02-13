package fr.eni.eniauctionwebsite.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private final boolean usernameExists;
    private final boolean emailExists;

    public UserAlreadyExistsException(boolean usernameExists, boolean emailExists) {
        this.usernameExists = usernameExists;
        this.emailExists = emailExists;
    }

    public boolean isUsernameExists() {
        return usernameExists;
    }

    public boolean isEmailExists() {
        return emailExists;
    }
}

