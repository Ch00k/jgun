package net.minuteware.jgun;

import java.io.*;

class CommandRunner {

    public void run(String command, String outputFile) throws IOException {
	Process run = Runtime.getRuntime().exec(command);
	if (outputFile != null) {
	    BufferedReader stdout = new BufferedReader(new InputStreamReader(run.getInputStream()));
	    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
	    String line;
	    while ((line = stdout.readLine()) != null) {
		bufferedWriter.write(line);
		bufferedWriter.newLine();
	    }
	    bufferedWriter.close();
	}
	else {
	    return;
	}
    }
}