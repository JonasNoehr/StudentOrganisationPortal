package de.hsba.bi.StuOrgPortal.user;

public class User {

    public static String ADMIN_ROLE = "ADMIN";
    public static String LECTURER_ROLE = "LECTURER";
    public static String STUDENT_ROLE = "STUDENT";
    private final String role;
    private String password;
    private String name;

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
