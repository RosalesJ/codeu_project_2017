package codeu.chat.client.gui;

import codeu.chat.client.ClientContext;

import javax.swing.*;

/**
 * Created by GNPMobile on 4/9/17.
 *
 * Meant to serve as the Slack-like side panel for the chat.
 */
@SuppressWarnings("serial")
public class SidePanel extends JPanel  {

    private final ClientContext context;

    public SidePanel(ClientContext context) {
        this.context = context;
        initialize();
    }

    private void initialize() {

    }

    // On any click, bubble up an event change with the conversation name
}
