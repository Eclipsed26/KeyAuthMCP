package Client.Auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWID {
    private static final char[] hexArray;

    public static String getHWID() {
        return bytesToHex(generateHWID());
    }

    public static byte[] generateHWID() {
        try {
            final MessageDigest hash = MessageDigest.getInstance("MD5");
            final String s = System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + Runtime.getRuntime().availableProcessors() + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS");
            return hash.digest(s.getBytes());
        }
        catch (final NoSuchAlgorithmException e) {
            throw new Error("Algorithm wasn't found.", e);
        }
    }

    public static byte[] hexStringToByteArray(final String s) {
        final int len = s.length();
        final byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String bytesToHex(final byte[] bytes) {
        final char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; ++j) {
            final int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HWID.hexArray[v >>> 4];
            hexChars[j * 2 + 1] = HWID.hexArray[v & 0xF];
        }
        return new String(hexChars);
    }

    static {
        hexArray = "0123456789ABCDEF".toCharArray();
    }
}