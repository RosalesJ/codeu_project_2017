package codeu.chat.client.gui.chatpanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GNPMobile on 5/31/17.
 */
public class ChatPanel extends VBox {
    public ChatPanel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-panel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
