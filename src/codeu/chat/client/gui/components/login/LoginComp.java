package codeu.chat.client.gui.components.login;

import codeu.chat.client.gui.events.AccountEvent;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class LoginComp extends VBox {
    @FXML
    private Label errorTag;

    @FXML
    private TextField username, password;

    @FXML
    private Button login, signup;

    public LoginComp() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize() {
        login.setOnAction((ActionEvent e) -> handleAccountEvent(AccountEvent.ACCOUNT_LOGIN));
        signup.setOnAction((ActionEvent e) -> handleAccountEvent(AccountEvent.ACCOUNT_SIGNUP));
    }

    private void handleAccountEvent(EventType<AccountEvent> type) {
        if ((getUsername().equals("")) || (getPassword().equals(""))) {
            errorTag.setText("Please provide both username and password.");
        } else {
            this.fireEvent(new AccountEvent(this, null, type));
        }
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public void setError(String err) {
        errorTag.setText(err);
    }
}
