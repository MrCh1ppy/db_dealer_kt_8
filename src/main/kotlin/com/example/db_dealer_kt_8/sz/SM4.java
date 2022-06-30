package com.example.db_dealer_kt_8.sz;





import com.example.db_dealer_kt_8.sz.utils.SM4Base;
import com.example.db_dealer_kt_8.sz.utils.SM4_Context;
import com.example.db_dealer_kt_8.sz.utils.StringUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SM4杂凑算法实现
 * 摘自https://gitee.com/snowlandltd/utilx4j
 */
public class SM4 {

	private String secretKey = "";

	private String iv = "";

	private boolean hexString = false;

	public String decryptDataToString_CBC(String cipherText) {
		return decryptDataToString_CBC(cipherText, "GBK");
	}

	public String decryptDataToString_CBC(String cipherText, String charset) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.getDecoder().decode(cipherText));
			return new String(decrypted, charset);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decryptData_CBC(String cipherText) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			return sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.getDecoder().decode(cipherText));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decryptDataToString_CBC(byte[] bytes) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, bytes);
			return new String(decrypted, "GBK");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decryptData_ECB(byte[] bytes) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
			}
			else {
				keyBytes = secretKey.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			return sm4.sm4_crypt_ecb(ctx, bytes);

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decryptData_ECB(String cipherText) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
			}
			else {
				keyBytes = secretKey.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			return sm4.sm4_crypt_ecb(ctx, Base64.getDecoder().decode(cipherText));

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decryptDataToString_ECB(String cipherText) {
		return decryptDataToString_ECB(cipherText, "GBK");
	}

	public String decryptDataToString_ECB(String cipherText, String charset) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
			}
			else {
				keyBytes = secretKey.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.getDecoder().decode(cipherText));
			return new String(decrypted, charset);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String encryptDataToString_CBC(byte[] bytes) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_ENCRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, bytes);
			String cipherText = Base64.getEncoder().encodeToString(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String encryptDataToString_CBC(String plainText) {
		return encryptDataToString_CBC(plainText, "GBK");
	}

	public String encryptDataToString_CBC(String plainText, String charset) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_ENCRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes(charset));
			String cipherText = Base64.getEncoder().encodeToString(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encryptData_CBC(String plainText, String charset) {
		try {
			return encryptData_CBC(plainText.getBytes(charset));
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encryptData_CBC(byte[] bytes) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_ENCRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			return sm4.sm4_crypt_cbc(ctx, ivBytes, bytes);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encryptData_ECB(byte[] bytes) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_ENCRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
			}
			else {
				keyBytes = secretKey.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			return sm4.sm4_crypt_ecb(ctx, bytes);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decryptDataToString_CBC(byte[] bytes, String charset) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_DECRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
				ivBytes = StringUtils.hexStringToBytes(iv);
			}
			else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, bytes);
			return new String(decrypted, charset);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encryptData_ECB(File f) throws IOException {
		if (f.exists()) {
			InputStream inStream = new FileInputStream(f);
			return encryptData_ECB(IOUtils.toByteArray(inStream));
		}
		else {
			throw new IOException("File not Found");
		}

	}

	public byte[] encryptData_ECB(String plainText) {
		return encryptData_ECB(plainText, "GBK");
	}

	public byte[] encryptData_ECB(String plainText, String charset) {
		try {
			return encryptData_ECB(plainText.getBytes(charset));
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public String encryptDataToString_ECB(String plainText) {
		return encryptDataToString_ECB(plainText, "GBK");
	}

	public String encryptDataToString_ECB(String plainText, String charset) {
		try {
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4Base.SM4_ENCRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = StringUtils.hexStringToBytes(secretKey);
			}
			else {
				keyBytes = secretKey.getBytes();
			}

			SM4Base sm4 = new SM4Base();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(charset));
			String cipherText = Base64.getEncoder().encodeToString(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return the iv
	 */
	public String getIv() {
		return iv;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @return the hexString
	 */
	public boolean isHexString() {
		return hexString;
	}

	/**
	 * @param hexString the hexString to set
	 */
	public void setHexString(boolean hexString) {
		this.hexString = hexString;
	}

	/**
	 * @param iv the iv to set
	 */
	public void setIv(String iv) {
		this.iv = iv;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
