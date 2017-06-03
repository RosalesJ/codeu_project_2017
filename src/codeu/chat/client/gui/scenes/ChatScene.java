package codeu.chat.client.gui.scenes;

import codeu.chat.client.ClientContext;
import codeu.chat.client.gui.Gui;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class ChatScene extends Scene {
    public ChatScene(StackPane root, ClientContext context, double width, double height) {
        super(root, width, height);
        root.getChildren().add(new Gui(context));
    }
}
