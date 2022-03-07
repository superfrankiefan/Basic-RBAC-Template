package com.sff.rbacdemo.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

public class MD5Utils {

	protected MD5Utils(){

	}

	@Value("sff.algorithm.salt")
	private static String SALT;

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		return new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
	}

	public static String encrypt(String username, String pswd) {
		return new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username.toLowerCase() + SALT),
				HASH_ITERATIONS).toHex();
	}

}
