package net.minuteware.jgun;

import java.io.*;

class OutputFileCreator {

    static File tmpDir = new File("/var/tmp");
    static File outputFile;

    public static void createOutputFile() throws IOException {
	outputFile = File.createTempFile("gun", ".out", tmpDir);
	outputFile.deleteOnExit();
    }

    public static String getOutputFilePath() throws IOException {
	return outputFile.getCanonicalPath();
    }
}