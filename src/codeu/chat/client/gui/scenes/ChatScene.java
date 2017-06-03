package codeu.chat.client.gui.scenes;

import codeu.chat.client.Controller;
import codeu.chat.client.View;
import codeu.chat.client.gui.Gui;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class ChatScene extends Scene {
    public ChatScene(StackPane root, Gui gui, double width, double height) {
        super(root, width, height);
        root.getChildren().add(gui);
    }
}
