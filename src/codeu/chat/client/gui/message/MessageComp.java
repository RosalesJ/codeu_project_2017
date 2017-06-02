package codeu.chat.client.gui.message;

import codeu.chat.util.Time;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GNPMobile on 6/2/17.
 */
public class MessageComp extends VBox {
    @FXML
    private Label author;

    @FXML
    private Label message;

    @FXML
    private Label time;

    public MessageComp() {
        this.author = new Label();
        this.message = new Label();
        this.time = new Label();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("message.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setAuthor(String author) {
        this.author.setText(author);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setTime(Time time) {
        this.time.setText(time.toString());
    }
}
