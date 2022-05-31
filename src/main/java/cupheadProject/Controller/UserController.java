package cupheadProject.Controller;

import cupheadProject.Model.User;

public class UserController {
    private static UserController instance = null;
    private User loggedInUser = null;

    private UserController() {
    }

    public static void setInstance(UserController instance) {
        UserController.instance = instance;
    }

    public static UserController getInstance() {
        if (UserController.instance == null) {
            UserController.setInstance(new UserController());
        }
        return UserController.instance;
    }

    public boolean handleLogin(String username, String password) {
        if (!User.hasUsername(username)) {
            return false;
        }
        if (!User.getUserByUsername(username).getPassword().equals(password)) {
            return false;
        }
        loggedInUser = User.getUserByUsername(username);
        return true;
    }

    public boolean handleRegister(String username, String password) {
        if (User.hasUsername(username)) {
            return false;
        }
        new User(username, password);
        return true;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void deleteAccount() {
        User.deleteUser(loggedInUser.getUsername());
    }

    public boolean isUsernameNew(String username) {
        if (this.loggedInUser.getUsername().equals(username)) {
            return false;
        }
        return true;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void updateInfo(String username, String password) {
        User.updateInfo(loggedInUser.getUsername(), username, password);
    }
}
