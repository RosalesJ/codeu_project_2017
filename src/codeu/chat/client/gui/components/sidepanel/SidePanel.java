package codeu.chat.client.gui.components.sidepanel;

import codeu.chat.client.ClientContext;
import codeu.chat.client.gui.events.ConversationChangeEvent;
import codeu.chat.common.ConversationSummary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Meant to mimic the SidePanel of Slack!
 *
 * Created by GNPMobile on 5/30/17.
 */
public class SidePanel extends VBox {
    private ClientContext context;

    @FXML
    private Label userLabel;

    @FXML
    private TextField newConversationField;

    @FXML
    private Button add;

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

    @FXML
    public void initialize() {
        add.setOnAction((ActionEvent e) -> {
            if (!newConversationField.getText().isEmpty()) {
                context.conversation.startConversation(newConversationField.getText(), context.user.getCurrent().id);
                update();
            }
        });
        this.setMinWidth(150);
    }

    private void update() {
        if ((context != null) && context.user.hasCurrent()) userLabel.setText(context.user.getCurrent().name);

        // Reset labels (besides header label)
        if (this.getChildren().size() > 3) this.getChildren().remove(3, this.getChildren().size());

        // Update labels and add them back
        context.conversation.updateAllConversations(false);

        // Get curr ConversationSummary
        ConversationSummary curr = context.conversation.getCurrent();
        for (ConversationSummary s : context.conversation.getConversationSummaries()) {
            Label l = new Label(s.listView());
            l.getProperties().put("summary", s);
            l.getStyleClass().add("conversation");
            if ((curr != null) && (curr.id.equals(s.id))) l.getStyleClass().add("selected");

            // Click Handler
            l.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent m) -> {
                // Update current ConversationSummary
                context.conversation.setCurrent(
                        (ConversationSummary)((Node) m.getSource()).getProperties().get("summary")
                );

                // Update SidePanel
                update();

                // Generate ActionEvent for Gui to handle
                this.fireEvent(new ConversationChangeEvent());
            });

            // Add to SidePanel
            this.getChildren().add(l);
        }
    }

    public void setContext(ClientContext context) {
        this.context = context;
        update();
    }
}
