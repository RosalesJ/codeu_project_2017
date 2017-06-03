package codeu.chat;

import codeu.chat.client.Controller;
import codeu.chat.client.View;
import codeu.chat.client.gui.Gui;
import codeu.chat.client.gui.events.AccountEvent;
import codeu.chat.client.gui.scenes.ChatScene;
import codeu.chat.client.gui.scenes.LoginScene;
import codeu.chat.util.Logger;
import codeu.chat.util.RemoteAddress;
import codeu.chat.util.connections.ClientConnectionSource;
import codeu.chat.util.connections.ConnectionSource;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by GNPMobile on 5/19/17.
 *
 * Borrowed heavily from SimpleGuiClientMain.
 */
public class GuiClientMain extends Application {

    private static final Logger.Log LOG = Logger.newLog(GuiClientMain.class);

    private static Gui gui;

    private static Scene chatScene, loginScene;

    private static void runClient(String[] args, Controller controller, View view) {
        LOG.info("Created client");

        try {
            gui = new Gui(controller, view);
            launch(args);
        } catch (Exception e) {
            System.out.println("ERROR: Exception in GuiClientMain.start. Check log for details.");
            LOG.error("Exception launching GUI: " + e.getMessage());
            System.exit(1);
        }

        LOG.info("Gui chat client is running.");
    }

    public static void main(String[] args) {

        try {
            Logger.enableFileOutput("chat_gui_client_log.log");
        } catch (IOException ex) {
            LOG.error(ex, "Failed to set logger to write to file");
        }

        LOG.info("============================= START OF LOG =============================");

        LOG.info("Starting GUI chat client...");

        // Start up server connection/interface.

        final RemoteAddress address = RemoteAddress.parse(args[0]);

        LOG.info(String.format("Connecting to %s@%s...", address.host, address.port));

        try (
                final ConnectionSource source = new ClientConnectionSource(address.host, address.port)
        ) {
            final Controller controller = new Controller(source);
            final View view = new View(source);

            LOG.info("Creating GUI client...");

            runClient(args, controller, view);

        } catch (Exception ex) {
            System.out.println("ERROR: Exception setting up GUI client. Check log for details.");
            LOG.error(ex, "Exception setting up client.");
        }
    }

    @Override
    public void start(Stage primary) {
        primary.setTitle("24 Chat");

        loginScene = new LoginScene(new StackPane(), 800, 600);
        loginScene.addEventHandler(AccountEvent.ACCOUNT_LOGIN, (AccountEvent e) -> {
            System.out.println("Login");

            // TODO: Finish
        });
        loginScene.addEventHandler(AccountEvent.ACCOUNT_SIGNUP, (AccountEvent e) -> {
            System.out.println("Signup");

            // TODO: Finish
        });
        chatScene = new ChatScene(new StackPane(), gui, 800, 600);

        primary.setScene(chatScene);
        primary.show();
    }
}
