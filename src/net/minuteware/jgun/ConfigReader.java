package net.minuteware.jgun;

import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {
	try {
	    ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    properties.load(loader.getResourceAsStream("net/minuteware/jgun/gun.properties"));
	} catch (IOException e) {
	    throw new ExceptionInInitializerError(e);
	}
    }

    public static String getSetting(String key) {
	return properties.getProperty(key);
    }

}