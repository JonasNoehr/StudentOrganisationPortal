package de.hsba.bi.StuOrgPortal.web.error;

public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists(final String message, final Throwable cause) {
        super(message, cause);
    }


}
