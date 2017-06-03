package codeu.chat.client.gui;

import codeu.chat.client.ClientContext;
import codeu.chat.client.gui.chatpanel.ChatPanel;
import codeu.chat.client.gui.events.ConversationChangeEvent;
import codeu.chat.client.gui.sidepanel.SidePanel;
import codeu.chat.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by GNPMobile on 4/9/17.
 *
 * Borrowed heavily from the SimpleChatGui class.
 */
public final class Gui extends BorderPane {
    private final static Logger.Log LOG = Logger.newLog(Gui.class);

    private final ClientContext context;

    @FXML
    private SidePanel sidePanel;

    @FXML
    private ChatPanel chatPanel;

    public Gui(ClientContext context) {
        this.context = context;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui.fxml"));
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
        sidePanel.setContext(context);
        chatPanel.setContext(context);

        // When SidePanel notifies of a selection change, update chatPanel
        sidePanel.addEventHandler(ConversationChangeEvent.CONVERSATION_CHANGE, (ConversationChangeEvent e) -> {
            System.out.println("Conversation clicked");
            chatPanel.update();
        });
    }
}
