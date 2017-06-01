package codeu.chat.client.gui.events;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Custom event to let SidePanel notify GUI when a selected conversation changes (to update the ChatPanel).
 *
 * Created by GNPMobile on 6/1/17.
 */
public class ConversationChangeEvent extends Event {

    public static final EventType<ConversationChangeEvent> CONVERSATION_CHANGE =
            new EventType<>(Event.ANY, "CONVERSATION_CHANGE");

    public ConversationChangeEvent() {
        super(CONVERSATION_CHANGE);
    }

    public ConversationChangeEvent(Object source, EventTarget target) {
        super(source, target, CONVERSATION_CHANGE);
    }

    @Override
    public ConversationChangeEvent copyFor(Object newSource, EventTarget newTarget) {
        return (ConversationChangeEvent) super.copyFor(newSource, newTarget);
    }

    @Override
    public EventType<? extends ConversationChangeEvent> getEventType() {
        return (EventType<? extends ConversationChangeEvent>) super.getEventType();
    }
}
