package codeu.chat.client.gui.scenes;

import codeu.chat.client.gui.login.LoginComp;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class LoginScene extends Scene {
    private StackPane root;
    private LoginComp login;

    public LoginScene(StackPane root) {
        super(root);
        this.root = root;
        setup();
    }

    public LoginScene(StackPane root, double width, double height) {
        super(root, width, height);
        this.root = root;
        setup();
    }

    private void setup() {
        login = new LoginComp();
        root.getChildren().add(login);
    }
}
