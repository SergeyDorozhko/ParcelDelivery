package org.darozhka.parceldelivery.iam.domain;


/**
 * @author S.Darozhka
 */
public enum SystemUser {
    ADMIN("root_admin", "root_admin", true, "root_admin", "root_admin", SecurityRole.ADMIN);

    private String name;
    private String surname;
    private Boolean isActive;
    private String username;
    private String password;
    private SecurityRole role;

    SystemUser(String name, String surname, Boolean isActive, String username, String password, SecurityRole role) {
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public SecurityRole getRole() {
        return role;
    }
}
