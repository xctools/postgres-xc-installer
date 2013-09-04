package common;

import java.io.File;
import java.net.InetAddress;

/*
 * Validation
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Check argument without using linux shell command.
 */

public class Validation {

    /*
     * argIsEmpty
     * Check arg is empty.
     */

    public static boolean argIsEmpty(final String arg) {
        if ("".equals(arg) || arg.matches("\\s+")) {
            return true;
        }
        return false;
    }

    /*
     * directoryPathIsValid
     * Check directory starts "/".
     */

    public static boolean directoryPathIsValid(final String directory) {
        if (directory.indexOf("/") == 0) {
            return true;
        }
        return false;
    }

    /*
     * directoryExist
     * Check directory exists.
     */

    public static boolean directoryExist(final String directory) {
        File file = new File(directory);
        return file.exists();
    }

    /*
     * succeedsPing
     * Check ping to address.
     */

    public static boolean succeedsPing(final String address) {
        boolean result = false;
        try {
            result = InetAddress.getByName(address).isReachable(1000);
        } catch (Exception e) { }
        return result;
    }

    /*
     * portIsValid
     * Check port consists of number
     * and port range is valid.
     */

    public static boolean portIsValid(final String port) {
        if (!port.matches("[0-9]+")) {
            return false;
        } else if (Integer.parseInt(port) < Define.PORT_MIN
                || Define.PORT_MAX < Integer.parseInt(port)) {
            return false;
        }
        return true;
    }

    /*
     * numberOfServersIsValid
     * Check number_of_servers cosists of number
     * and number_of_servers range is valid.
     */

    public static boolean numberOfServersIsValid(
            final String number_of_servers) {
        if (!number_of_servers.matches("[0-9]+")) {
            return false;
        } else if (Integer.parseInt(number_of_servers)
                <= Define.NUMBER_OF_SERVERS_MIN) {
            return false;
        }
        return true;
    }

    /*
     * postgresqlConfExist
     * Check postgresql_conf exists.
     */

    public static boolean postgresqlConfExist(final String postgresql_conf) {
        File file = new File(postgresql_conf);
        return (file.exists() && file.isFile());
    }
}