package kitkare.kitkare.app.viewModels;

public class RegisterUserViewModel {
    public String email;
    public String password;
    public String confirmPassowrd;

    public RegisterUserViewModel(String email, String password, String confirmPassowrd){
        this.email = email;
        this.password = password;
        this.confirmPassowrd = confirmPassowrd;
    }
}
