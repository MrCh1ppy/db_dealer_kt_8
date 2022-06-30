package com.example.db_dealer_kt_8.sz;


import com.example.db_dealer_kt_8.sz.utils.StringUtils;

/**
 * SM4加密算法的实现
 * SM4有ECB和CBC两种加密模式，本类采用的是SM4算法的CBC加密模式，安全性更好
 * 参考：https://www.cnblogs.com/yanzi-meng/p/9640578.html
 */
public class EncryptAlgorithm_SM4 {

	private final SM4 sm4;

	/**
	 * 定义sm4算法默认加密解密编码为utf-8，
	 * 算法内部默认gbk编码支持的汉字有限，可能会导致部分汉字加密解密出现乱码
	 */
	private static final String CHARSET = "UTF-8";

	public EncryptAlgorithm_SM4(String secretKey, String iv) {
		this.sm4 = new SM4();
		this.sm4.setSecretKey(secretKey);
		this.sm4.setIv(iv);
		this.sm4.setHexString(false);
	}

	public String encrypt(String value) {
		//加密传递的字符串可能是空字符串，空字符串不需要加密，返回原值
		if (StringUtils.isBlank(value))
			return value;
		try {
			return this.sm4.encryptDataToString_CBC(value, CHARSET);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String value) {
		//解密传递的字符串可能是空字符串，空字符串不需要解密，返回原值
		if (StringUtils.isBlank(value))
			return value;
		try {
			return this.sm4.decryptDataToString_CBC(value, CHARSET);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
