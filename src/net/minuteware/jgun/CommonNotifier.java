package net.minuteware.jgun;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import javax.mail.MessagingException;
import org.jivesoftware.smack.XMPPException;

class CommonNotifier {

    String smtpHost = ConfigReader.getSetting("smtp.server").split(":")[0];
    int smtpPort = Integer.parseInt(ConfigReader.getSetting("smtp.server").split(":")[1]);
    String smtpUser = ConfigReader.getSetting("smtp.user");
    String smtpPassword = ConfigReader.getSetting("smtp.password");
    String smtpFrom = ConfigReader.getSetting("mail.from");

    String xmppHost = ConfigReader.getSetting("jabber.from").split("@")[1];
    int xmppPort = Integer.parseInt(ConfigReader.getSetting("jabber.port"));
    String xmppUser = ConfigReader.getSetting("jabber.from").split("@")[0];
    String xmppPassword = ConfigReader.getSetting("jabber.password");

    public CommonNotifier() throws XMPPException, MessagingException, ParseException, IOException {

	List<Notifier> notifiers=new ArrayList<Notifier>();
	if (ConfigReader.getSetting("notify.email").equals("yes")) {
	    notifiers.add(new EmailNotifier(smtpHost, smtpPort, smtpUser, smtpPassword, smtpFrom));
	}
	if (ConfigReader.getSetting("notify.jabber").equals("yes")) {
	    notifiers.add(new JabberNotifier(xmppHost, xmppPort, xmppUser, xmppPassword));
	}
	for (Notifier notifier : notifiers) {
	    notifier.send();
	}
    }
}