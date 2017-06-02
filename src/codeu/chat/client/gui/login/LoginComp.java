package codeu.chat.client.gui.login;

import codeu.chat.client.gui.events.AccountEvent;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class LoginComp extends VBox {
    @FXML
    private TextField username, password;

    @FXML
    private Button login, signup;

    public LoginComp() {
        username = new TextField();
        password = new TextField();

        // Set up Login handler
        login = new Button();
        login.setOnAction((ActionEvent e) -> {
            System.out.println("Testing");

            // Send Login Event
            handleAccountEvent(AccountEvent.ACCOUNT_LOGIN);
        });

        // Set up signup handler
        signup = new Button();
        signup.setOnAction((ActionEvent e) -> {
            // Send Signup Event
            handleAccountEvent(AccountEvent.ACCOUNT_SIGNUP);
        });

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void handleAccountEvent(EventType<AccountEvent> type) {
        System.out.println("Fired " + type.getName());

        // Send Event
        this.fireEvent(new AccountEvent(this, null, type));

        // TODO: Set up error message
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }
}
