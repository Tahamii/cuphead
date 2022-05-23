package cupheadProject.Controller;

import cupheadProject.Model.User;

public class LoginMenuController {
    private static LoginMenuController instance = null;

    private LoginMenuController(){}

    public static void setInstance(LoginMenuController instance) {
        LoginMenuController.instance = instance;
    }

    public static LoginMenuController getInstance() {
        if(LoginMenuController.instance == null){
            LoginMenuController.setInstance(new LoginMenuController());
        }
        return LoginMenuController.instance;
    }

    public boolean handleLogin(String username, String password){
        if(!User.hasUsername(username)){
            return false;
        }
        if(!User.getUserByUsername(username).getPassword().equals(password)){
            return false;
        }
        return true;
    }
}
