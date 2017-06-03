package codeu.chat.client.gui.events;

import codeu.chat.client.gui.components.messagebar.MessageBar;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Created by GNPMobile on 6/2/17.
 */
@SuppressWarnings("serial")
public class MessageEmittedEvent extends Event {
    public static final EventType<MessageEmittedEvent> MESSAGE_EMITTED =
            new EventType<>(Event.ANY, "MESSAGE_EMITTED");

    private String message;

    public MessageEmittedEvent() {
        super(MESSAGE_EMITTED);
    }

    public MessageEmittedEvent(Object source, EventTarget target) {
        super(source, target, MESSAGE_EMITTED);

        if (source instanceof MessageBar) {
            message = ((MessageBar) source).getMessage();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public MessageEmittedEvent copyFor(Object newSource, EventTarget newTarget) {
        return (MessageEmittedEvent) super.copyFor(newSource, newTarget);
    }

    @Override
    @SuppressWarnings("unchecked")
    public EventType<? extends MessageEmittedEvent> getEventType() {
        return (EventType<? extends MessageEmittedEvent>) super.getEventType();
    }

    public String getMessage() {
        return message;
    }
}
