package codeu.chat;

import codeu.chat.client.Controller;
import codeu.chat.client.View;
import codeu.chat.client.gui.Gui;
import codeu.chat.util.Logger;
import codeu.chat.util.RemoteAddress;
import codeu.chat.util.connections.ClientConnectionSource;
import codeu.chat.util.connections.ConnectionSource;

import java.io.IOException;

/**
 * Created by GNPMobile on 5/19/17.
 *
 * Borrowed heavily from SimpleGuiClientMain.
 */
public class GuiClientMain {

    private static final Logger.Log LOG = Logger.newLog(GuiClientMain.class);

    private static void runClient(Controller controller, View view) {

        final Gui gui = new Gui(controller, view);

        LOG.info("Created client");

        gui.run();

        LOG.info("Gui chat client is running.");
    }

    public static void main(String [] args) {

        try {
            Logger.enableFileOutput("chat_simple_gui_client_log.log");
        } catch (IOException ex) {
            LOG.error(ex, "Failed to set logger to write to file");
        }

        LOG.info("============================= START OF LOG =============================");

        LOG.info("Starting GUI chat client...");

        // Start up server connection/interface.

        final RemoteAddress address = RemoteAddress.parse(args[0]);

        try (
                final ConnectionSource source = new ClientConnectionSource(address.host, address.port)
        ) {
            final Controller controller = new Controller(source);
            final View view = new View(source);

            LOG.info("Creating GUI client...");

            runClient(controller, view);

        } catch (Exception ex) {
            System.out.println("ERROR: Exception setting up GUI client. Check log for details.");
            LOG.error(ex, "Exception setting up client.");
        }
    }
}
