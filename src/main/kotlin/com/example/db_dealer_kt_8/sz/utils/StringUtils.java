package com.example.db_dealer_kt_8.sz.utils;

/**
 * @createdate 2012-1-5
 */
public class StringUtils  {
	
	/**
	 * Convert hex string to byte[]
	 *
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.length() == 0) {
			return null;
		}
		int length = hexString.length() / 2;
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexString.charAt(pos)) << 4 | charToByte(hexString.charAt(pos + 1)));
		}
		return d;
	}

	private static byte charToByte(char c) {
		int result;
		if (c >= '0' && c <= '9') {
			result = c - '0';
		}
		else if (c >= 'a' && c <= 'f') {
			result = c - 'a' + 10;
		}
		else if (c >= 'A' && c <= 'F') {
			result = c - 'A' + 10;
		}
		else {
			result = -1;
		}
		return (byte) result;
	}

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
