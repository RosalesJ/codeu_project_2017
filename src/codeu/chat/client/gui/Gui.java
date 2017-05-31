package codeu.chat.client.gui;

import codeu.chat.client.ClientContext;
import codeu.chat.client.Controller;
import codeu.chat.client.View;
import codeu.chat.client.gui.sidepanel.SidePanel;
import codeu.chat.util.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by GNPMobile on 4/9/17.
 *
 * Borrowed heavily from the SimpleChatGui class.
 */
public final class Gui extends BorderPane implements Initializable {
    private final static Logger.Log LOG = Logger.newLog(Gui.class);

    private final ClientContext context;

    @FXML
    private SidePanel sidePanel;

    public Gui(Controller controller, View view) {
        context = new ClientContext(controller, view);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidePanel.setContext(context);
    }
}
