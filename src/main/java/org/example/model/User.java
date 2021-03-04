package org.example.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int userGroupId;
    private String role;

    public User(String username, String email, String password, int userGroupId, String role){
        this.username = username;
        this.email = email;
        this.hashPassword(password);
        this.userGroupId = userGroupId;
        this.role = role;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", username: " + this.username + ", email: " + this.email + ", group id: " + this.userGroupId + ", role: " + this.getRole();
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (userGroupId != user.userGroupId)
            return false;
        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + userGroupId;
        return result;
    }
}
