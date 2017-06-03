package codeu.chat.client.gui.messagebar;

import codeu.chat.client.gui.events.MessageEmittedEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class MessageBar extends BorderPane {
    @FXML
    private TextField editor;

    public MessageBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("message-bar.fxml"));
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
        editor.addEventFilter(KeyEvent.ANY, e -> {
            if (e.getCode().equals(KeyCode.ENTER) && e.getEventType().equals(KeyEvent.KEY_PRESSED)
                    && !editor.getText().isEmpty()) {
                // Fire event to be handled by ChatPanel
                this.fireEvent(new MessageEmittedEvent(this, null));

                // Reset TextField
                editor.setText("");
            }
        });
    }

    public String getMessage() {
        return editor.getText();
    }
}
