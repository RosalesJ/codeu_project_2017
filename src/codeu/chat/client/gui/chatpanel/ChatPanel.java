package codeu.chat.client.gui.chatpanel;

import codeu.chat.client.ClientContext;
import codeu.chat.client.gui.events.MessageEmittedEvent;
import codeu.chat.client.gui.messagebar.MessageBar;
import codeu.chat.client.gui.messagespanel.MessagesPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by GNPMobile on 5/31/17.
 */
public class ChatPanel extends BorderPane {
    private ClientContext context;

    @FXML
    private MessagesPanel messagesPanel;

    @FXML
    private MessageBar messageBar;

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

    @FXML
    public void initialize() {
        this.addEventFilter(MessageEmittedEvent.MESSAGE_EMITTED, (MessageEmittedEvent e) -> {
            System.out.println(e.getMessage());

            context.message.addMessage(context.user.getCurrent().id, context.conversation.getCurrentId(),
                    e.getMessage());
            messagesPanel.update();
        });
    }

    public void update() {
        messagesPanel.update();
        if (!context.conversation.hasCurrent()) {
            messageBar.setDisable(true);
        } else {
            messageBar.setDisable(false);
        }
    }

    public void setContext(ClientContext context) {
        this.context = context;
        messagesPanel.setContext(context);
        update();
    }
}
