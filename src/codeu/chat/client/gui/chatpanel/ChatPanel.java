package codeu.chat.client.gui.chatpanel;

import codeu.chat.client.ClientContext;
import codeu.chat.client.gui.messagespanel.MessagesPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GNPMobile on 5/31/17.
 */
public class ChatPanel extends VBox {
    private ClientContext context;

    @FXML
    private MessagesPanel messagesPanel;

    public ChatPanel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat-panel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        messagesPanel = new MessagesPanel();

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update() {
        messagesPanel.setContext(context);
    }

    public void setContext(ClientContext context) {
        this.context = context;
        update();
    }
}
