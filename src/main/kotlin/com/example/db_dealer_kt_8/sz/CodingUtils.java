package com.example.db_dealer_kt_8.sz;


import cn.hutool.crypto.digest.SM3;

import java.io.IOException;

public class CodingUtils {
	public static final String secretKey = "JeF8U9wHFOMfs2Y8";
	public static final String iv = "UISwD9fW6cFh9SNS";
	public static final EncryptAlgorithm_SM4 sm4 = new EncryptAlgorithm_SM4(secretKey, iv);

	public static final ThreadLocal<SM3> dealer=ThreadLocal.withInitial(SM3::create);


	private CodingUtils() {
	}

	public static synchronized String sm3Encode(String str)throws IOException {
		return dealer.get().digestHex(str);
	}

	public static String sm4Decode(String str) {
		return sm4.decrypt(str);
	}

	public static String sm4Encode(String str){
		return sm4.encrypt(str);
	}

	public static String codeDeal(String str) {
		String decode = str.length()<=19?str:CodingUtils.sm4Decode(str);
		try {
			return CodingUtils.sm3Encode(decode);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
