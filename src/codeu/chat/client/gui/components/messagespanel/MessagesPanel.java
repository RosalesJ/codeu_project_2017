package codeu.chat.client.gui.components.messagespanel;

import codeu.chat.client.ClientContext;
import codeu.chat.client.gui.components.message.MessageComp;
import codeu.chat.common.ConversationSummary;
import codeu.chat.common.Message;
import codeu.chat.util.Time;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GNPMobile on 6/1/17.
 */
public class MessagesPanel extends VBox {
    private ClientContext context;

    public MessagesPanel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("messages-panel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update() {
        // Remove current children
        this.getChildren().remove(0, this.getChildren().size());

        // Get current ConversationSummary
        ConversationSummary curr = context.conversation.getCurrent();
        List<Message> messages = curr != null ? context.message.getConversationContents(curr) : new ArrayList<>();

        // Update messages
        for (Message m : messages) {
            final String authorName = context.user.getName(m.author);
            final Time creation = m.creation;
            final String content = m.content;

            MessageComp c = new MessageComp();
            c.setAuthor(authorName != null ? authorName : m.author.toString());
            c.setMessage(content);
            c.setTime(creation);

            this.getChildren().add(c);
        }
    }

    public void setContext(ClientContext context) {
        this.context = context;
        update();
    }
}
