package net.minuteware.jgun;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.*;

class MessageFormatter {

    static Map<String, String> htmlReplacements = new HashMap<String, String>() {
	private static final long serialVersionUID = 1L;
	{
	    put("\u001B[32m", "</span><span style=\"color:darkgreen;font-weight:normal\">");
	    put("\u001B[36;01m", "</span><span style=\"color:turquoise;font-weight:bold\">");
	    put("\u001B[34;01m", "</span><span style=\"color:blue;font-weight:bold\">");
	    put("\u001B[39;49;00m", "</span><span style=\"color:black;font-weight:normal\">");
	    put("\u001B[33;01m", "</span><span style=\"color:orange;font-weight:bold\">");
	    put("\u001B[32;01m", "</span><span style=\"color:limegreen;font-weight:bold\">");
	    put("\u001B[31;01m", "</span><span style=\"color:red;font-weight:bold\">");
	    put("\n", "<br>");
	    put("\u001B[01;34m", "</span><span style=\"color:blue;font-weight:bold\">");
	    put("\u001B[0m", "</span><span style=\"color:black;font-weight:normal\">");
	    put("\u001B[01;36m", "</span><span style=\"color:turquoise;font-weight:bold\">");
	    put("\u001B[30;42m", "</span><span style=\"color:limegreen;font-weight:bold\">");
	}
    };
    
    static Map<String, String> plaintextReplacements = new HashMap<String, String>() {
	private static final long serialVersionUID = 1L;
	{
	    put("\u001B[32m", "");
	    put("\u001B[36;01m", "");
	    put("\u001B[34;01m", "");
	    put("\u001B[39;49;00m", "");
	    put("\u001B[33;01m", "");
	    put("\u001B[32;01m", "");
	    put("\u001B[31;01m", "");
	    put("\u001B[01;34m", "");
	    put("\u001B[0m", "");
	    put("\u001B[01;36m", "");
	    put("\u001B[30;42m", "");
	}
    };
    
    static String output;

    MessageFormatter(String outputFile) throws IOException {
	FileInputStream stream = new FileInputStream(outputFile);
	FileChannel fc = stream.getChannel();
	MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
	output = Charset.defaultCharset().decode(bb).toString();
	stream.close();
    }

    public String toHTML() {
	StringBuilder buffer = new StringBuilder();
	Iterator<String> iter = htmlReplacements.keySet().iterator();
	while (iter.hasNext()) {
	    buffer.append(Pattern.quote(iter.next()));
	    if (iter.hasNext()) {
		buffer.append("|");
	    }
	}
	String regexp = buffer.toString();
	StringBuffer message = new StringBuffer();
	Pattern p = Pattern.compile(regexp);
	Matcher m = p.matcher(output);
	while (m.find()) {
	    m.appendReplacement(message, htmlReplacements.get(m.group()));
	}
	m.appendTail(message);

	return message.toString();
    }
    
    public String toPlaintext() {
	
	StringBuilder buffer = new StringBuilder();
	Iterator<String> iter = plaintextReplacements.keySet().iterator();
	while (iter.hasNext()) {
	    buffer.append(Pattern.quote(iter.next()));
	    if (iter.hasNext()) {
		buffer.append("|");
	    }
	}
	String regexp = buffer.toString();
	StringBuffer message = new StringBuffer();
	Pattern p = Pattern.compile(regexp);
	Matcher m = p.matcher(output);
	while (m.find()) {
	    m.appendReplacement(message, plaintextReplacements.get(m.group()));
	}
	m.appendTail(message);

	return message.toString();
    }
}