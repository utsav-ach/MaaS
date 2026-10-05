package com.example.maas.util;

/**
 * Global constants and configuration identifiers for the MaaS edge web hosting platform.
 */
public final class Constants {

    private Constants() {
        // Prevent instantiation
    }

    public static final String APP_TAG = "MaaS-Platform";
    public static final String TARGET_OS = "Ubuntu 22.04 LTS (aarch64)";
    public static final String RUNTIME_LAYER = "PRoot / Termux Container";

    // Standard Server Ports
    public static final int DEFAULT_HTTP_PORT = 8080;
    public static final int DEFAULT_HTTPS_PORT = 8443;
    public static final int DEFAULT_MYSQL_PORT = 3306;

    // Status Strings
    public static final String STATUS_NOT_CONNECTED = "Not Connected";
    public static final String STATUS_NOT_CONFIGURED = "Not Configured";
    public static final String STATUS_NOT_ACTIVE = "Not Active";
    public static final String STATUS_NOT_IMPLEMENTED = "Not Implemented";
    public static final String STATUS_UNAVAILABLE = "Unavailable";
    public static final String STATUS_OFFLINE = "Offline";
    public static final String STATUS_STOPPED = "Stopped";
}
