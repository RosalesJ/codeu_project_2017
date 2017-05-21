package codeu.chat.client.gui;

import codeu.chat.client.ClientContext;
import codeu.chat.client.Controller;
import codeu.chat.client.View;
import codeu.chat.util.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Created by GNPMobile on 4/9/17.
 *
 * Borrowed heavily from the SimpleChatGui class.
 */
public final class Gui {
    private final static Logger.Log LOG = Logger.newLog(Gui.class);

    private JFrame frame;
    private final ClientContext context;

    public Gui(Controller controller, View view) {
        // Set up context
        context = new ClientContext(controller, view);
    }

    private void initialize() {
        // Set up GUI
        frame = new JFrame("24 Chat");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        // Add components

        frame.pack();
    }

    public void run() {
        try {
            initialize();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("ERROR: Exception in Gui.run. Check log for details.");
            LOG.error(e, "Exception in Gui.run");
            System.exit(1);
        }
    }
}
