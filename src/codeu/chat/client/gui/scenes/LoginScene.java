package codeu.chat.client.gui.scenes;

import codeu.chat.client.gui.components.login.LoginComp;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class LoginScene extends Scene {
    public LoginScene(StackPane root, double width, double height) {
        super(root, width, height);
        root.getChildren().add(new LoginComp());
    }
}
