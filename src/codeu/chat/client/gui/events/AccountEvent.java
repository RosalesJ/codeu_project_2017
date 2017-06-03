package codeu.chat.client.gui.events;

import codeu.chat.client.gui.components.login.LoginComp;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Created by GNPMobile on 6/2/17.
 */
@SuppressWarnings("serial")
public class AccountEvent extends Event {
    public static final EventType<AccountEvent> ACCOUNT_GENERIC =
            new EventType<>(Event.ANY, "ACCOUNT_GENERIC");

    public static final EventType<AccountEvent> ACCOUNT_LOGIN =
            new EventType<>(Event.ANY, "ACCOUNT_LOGIN");

    public static final EventType<AccountEvent> ACCOUNT_SIGNUP =
            new EventType<>(Event.ANY, "ACCOUNT_SIGNUP");

    private String username, password;

    public AccountEvent() {
        super(ACCOUNT_GENERIC);
    }

    public AccountEvent(Object source, EventTarget target, EventType<AccountEvent> type) {
        super(source, target, type);

        if (source instanceof LoginComp) {
            username = ((LoginComp) source).getUsername();
            password = ((LoginComp) source).getPassword();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public AccountEvent copyFor(Object newSource, EventTarget newTarget) {
        return (AccountEvent) super.copyFor(newSource, newTarget);
    }

    @Override
    @SuppressWarnings("unchecked")
    public EventType<? extends AccountEvent> getEventType() {
        return (EventType<? extends AccountEvent>) super.getEventType();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
