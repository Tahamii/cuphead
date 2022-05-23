package cupheadProject.Controller;

import cupheadProject.Model.User;

public class RegisterMenuController {
    private static RegisterMenuController instance = null;

    private RegisterMenuController(){}

    public static void setInstance(RegisterMenuController instance) {
        RegisterMenuController.instance = instance;
    }

    public static RegisterMenuController getInstance() {
        if(RegisterMenuController.instance == null){
            RegisterMenuController.setInstance(new RegisterMenuController());
        }
        return RegisterMenuController.instance;
    }

    public boolean handleRegister(String username, String password){
        if(User.hasUsername(username)){
            return false;
        }
        new User(username, password);
        return true;
    }
}
