package net.minuteware.jgun;

import java.io.*;

class UpdateRunner {
    static String emergeCommand = ConfigReader.getSetting("emerge.command");
    static String emergeOptions = ConfigReader.getSetting("emerge.options");

    public static void runUpdate(String outputFile) throws IOException {
	CommandRunner cr = new CommandRunner();
	cr.run(emergeCommand + " " + emergeOptions, outputFile);
    }
}