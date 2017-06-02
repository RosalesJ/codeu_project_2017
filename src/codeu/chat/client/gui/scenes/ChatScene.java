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
    private StackPane root;

    public ChatScene(StackPane root, Gui gui) {
        super(root);

        this.root = root;
        root.getChildren().add(gui);
    }

    public ChatScene(StackPane root, Gui gui, double width, double height) {
        super(root, width, height);

        this.root = root;
        root.getChildren().add(gui);
    }
}
