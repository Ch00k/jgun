package net.minuteware.jgun;

import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;

class EmailNotifier implements Notifier {

    Session session;
    String to = ConfigReader.getSetting("mail.to");

    public EmailNotifier(final String host, final int port, final String user,
	    final String password, final String sender) {
	Properties props = new Properties();
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", port);
	props.put("mail.from", sender);
	props.put("mail.smtp.auth", "true");
	Authenticator auth = new Authenticator() {
	    public PasswordAuthentication getPasswordAuthentication(){

		return new PasswordAuthentication(user, password);
	    }
	};	
	session = Session.getInstance(props, auth);
    }

    public void send() throws MessagingException, ParseException, IOException {
	MimeMessage msg = new MimeMessage(session);
	msg.setRecipients(Message.RecipientType.TO, to);
	msg.setSubject("[" + InetAddress.getLocalHost().getHostName() + "] " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " Packages to update");
	msg.setContent(new MessageFormatter(OutputFileCreator.getOutputFilePath()).toHTML(), "text/html");
	Transport.send(msg);
    }
}