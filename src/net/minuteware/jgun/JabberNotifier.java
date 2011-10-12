package net.minuteware.jgun;

import java.io.IOException;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;


class JabberNotifier implements Notifier {

    Connection connection;
    String to = ConfigReader.getSetting("jabber.to");

    public JabberNotifier(final String host, final int port,
	    final String user, final String password) throws XMPPException {
	ConnectionConfiguration config = new ConnectionConfiguration(host, port);
	connection = new XMPPConnection(config);
	connection.connect();
	connection.login(user, password);
    }

    public void send() throws IOException {
	Message message = new Message();
	message.setTo(to);
	message.setBody(new MessageFormatter(OutputFileCreator.getOutputFilePath()).toPlaintext());
	connection.sendPacket(message);
    }
}