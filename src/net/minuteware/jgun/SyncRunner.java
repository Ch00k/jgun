package net.minuteware.jgun;

import java.io.IOException;

class SyncRunner {

    static String treeCommand = ConfigReader.getSetting("tree.command") + " " + ConfigReader.getSetting("tree.options");
    static String overlaysCommand = ConfigReader.getSetting("overlays.command") + " " + ConfigReader.getSetting("overlays.options");

    public static void runSync() throws IOException {
	CommandRunner cr = new CommandRunner();
	cr.run(treeCommand, null);
	if (ConfigReader.getSetting("sync.overlays") == "yes") {
	    cr.run(overlaysCommand, null);
	}
    }
}