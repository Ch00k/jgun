package net.minuteware.jgun;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;
import org.jivesoftware.smack.XMPPException;

class JGun {
    public static void main(String[] args) throws FileNotFoundException, IOException, XMPPException, MessagingException, ParseException {
	SyncRunner.runSync();
	OutputFileCreator.createOutputFile();
	UpdateRunner.runUpdate(OutputFileCreator.getOutputFilePath());
	new CommonNotifier();
    }
}