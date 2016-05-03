package com.yikang.health.cache;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密生成文件名
 * @author SP-ShenWeiliang
 * 
 */
public class Md5FileNameGenerator {

	private static final String HASH_ALGORITHM = "MD5";
	private static final int RADIX = 10 + 26; 	// 10 digits + 26 letters

	public static String generate(String str) {
		byte[] md5 = getMD5(str.getBytes());
		BigInteger bi = new BigInteger(md5).abs();
		return bi.toString(RADIX);
	}

	private static byte[] getMD5(byte[] data) {
		byte[] hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(data);
			hash = digest.digest();
		} catch (NoSuchAlgorithmException e) {
			LogUtils.e("An error occurred when call the method {Md5FileNameGenerator->getMD5()}", e);
		}
		return hash;
	}
}