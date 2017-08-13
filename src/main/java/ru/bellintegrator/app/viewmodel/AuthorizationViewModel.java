package ru.bellintegrator.app.viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.UserService;

public class AuthorizationViewModel extends AbstractViewModel {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationViewModel.class);
    private Stage stage;
    private User user;
    UserService userService;

    public AuthorizationViewModel(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField pwdPasswordField;

    @FXML
    private void loginButtonClick() {
        log.debug("loginButtonClick");
        String login = loginTextField.getText();
        String password = pwdPasswordField.getText();

        if (!login.isEmpty() && !password.isEmpty()) {
            try {
                user = userService.getUserByCredential(login, password);

                stage.close();

            } catch (DAOException e) {
                log.debug("Exception while authorization: " + e);
            }
        }
    }

    @FXML
    private void cancelButtonClick() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User getUser() {
        return user;
    }

}
