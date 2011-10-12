package net.minuteware.jgun;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.mail.MessagingException;

public interface Notifier {
    public void send() throws MessagingException, UnknownHostException, ParseException, IOException;
}