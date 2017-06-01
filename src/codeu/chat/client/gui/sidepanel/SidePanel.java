package codeu.chat.client.gui.sidepanel;

import codeu.chat.client.ClientContext;
import codeu.chat.common.ConversationSummary;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GNPMobile on 5/30/17.
 */
public class SidePanel extends VBox {
    private ClientContext context;

    public SidePanel() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("side-panel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void update() {
        context.conversation.updateAllConversations(false);

        for (ConversationSummary s : context.conversation.getConversationSummaries()) {
            System.out.println(s);
        }
    }

    public void setContext(ClientContext context) {
        this.context = context;
        update();
    }
}
