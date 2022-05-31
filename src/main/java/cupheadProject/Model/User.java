package cupheadProject.Model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<User> USERS = new ArrayList<>();
    private String password;
    private String username;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        USERS.add(this);
    }

    public static boolean hasUsername (String username){
        for (User user : USERS) {
            if(user.username.equals(username)){
                System.out.println("username in user");
                return true;
            }
        }
        return false;
    }

    public static User getUserByUsername(String username){
        for (User user : USERS) {
            if(user.username.equals(username)){
                return user;
            }
        }
        return null;
    }

    public static void updateInfo(String username, String newUsername, String password) {
        for (User user : USERS) {
            if(user.username.equals(username)){
                user.setUsername(newUsername);
                user.setPassword(password);
                return;
            }
        }
    }

    public static void deleteUser(String username){
        for (User user : USERS) {
            if(user.username.equals(username)){
                USERS.remove(user);
                return;
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
