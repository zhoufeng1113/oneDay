package com.homevip.util.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Aes加密
 * 
 */
public class AesUtil {

	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	private static class SingleBase64 {
		private static final Base64 instance = new Base64();
	}

	/**
	 * 加密字符串 in AES-256 with a given key
	 *
	 * @param stringToEncode
	 * @param secretKey
	 * @return String Base64 and AES encoded String
	 */
	public static String encode(String stringToEncode, String secretKey)
			throws NullPointerException {
		if (stringToEncode.length() == 0 || stringToEncode == null) {
			throw new NullPointerException("Please give text");
		}
		try {
			SecretKeySpec skeySpec = getKey(secretKey);
			byte[] clearText = stringToEncode.getBytes("UTF8");

			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			// Cipher is not thread safe
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			byte[] encodeByte = cipher.doFinal(clearText);
			Base64 base64 = SingleBase64.instance;
			encodeByte = base64.encode(encodeByte);
			return new String(encodeByte, encoding);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 解密 using AES-256 and Base64
 	 * @param text
	 * @param secretKey
	 * @return
	 * @throws NullPointerException
     */
	public static String decode(String text, String secretKey)
			throws NullPointerException {

		if (text.length() == 0 || text == null) {
			throw new NullPointerException("Please give text");
		}

		try {
			SecretKey key = getKey(secretKey);

			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			Base64 base64 = SingleBase64.instance;
			byte[] encrypedPwdBytes = base64.decode(text.getBytes());
			// cipher is not thread safe
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

			byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

			String decrypedValue = new String(decrypedValueBytes);
			return decrypedValue;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Generates a SecretKeySpec for given password
	 * @param secretKey
	 * @return
	 * @throws UnsupportedEncodingException
     */
	private static SecretKeySpec getKey(String secretKey)
			throws UnsupportedEncodingException {
		// You can change it to 128 if you wish
		int keyLength = 128;
		byte[] keyBytes = new byte[keyLength / 8];
		// explicitly fill with zeros
		Arrays.fill(keyBytes, (byte) 0x0);

		// if password is shorter then key length, it will be zero-padded
		// to key length
		byte[] passwordBytes = secretKey.getBytes("UTF-8");
		int length = passwordBytes.length < keyBytes.length ? passwordBytes.length
				: keyBytes.length;
		System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}

	/**
	 * 加密文件 in AES-256 with a given key
	 * @param sourceFile
	 * @param encrypfile
	 * @param secretKey
	 * @return
     * @throws Exception
     */
	public static File encodeFile(File sourceFile, File encrypfile,
			String secretKey) throws Exception {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			SecretKeySpec skeySpec = getKey(secretKey);

			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(encrypfile);
			// 以加密流写入文件
			CipherInputStream cipherInputStream = new CipherInputStream(
					inputStream, cipher);
			byte[] cache = new byte[1024];
			int nRead = 0;
			while ((nRead = cipherInputStream.read(cache)) != -1) {
				outputStream.write(cache, 0, nRead);
				outputStream.flush();
			}
			cipherInputStream.close();

		} catch (Exception e) {
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
		return encrypfile;
	}

	/**
	 * 解密文件 in AES-256 with a given key
	 * @param sourceFile
	 * @param decrypfile
	 * @param secretKey
	 * @return
     * @throws Exception
     */
	public static File decodeFile(File sourceFile, File decrypfile,
			String secretKey) throws Exception {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			SecretKey key = getKey(secretKey);
			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(decrypfile);
			// 以加密流写入文件
			CipherOutputStream cipherOutputStream = new CipherOutputStream(
					outputStream, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = inputStream.read(buffer)) >= 0) {
				cipherOutputStream.write(buffer, 0, r);
			}
			cipherOutputStream.close();

		} catch (Exception e) {
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
		return decrypfile;
	}

	public static String encryptNoPadding(String data, String key)
			throws Exception {
		try {
			Base64 base64 = SingleBase64.instance;

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();

			byte[] dataBytes = data.getBytes("UTF-8");
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength
						+ (blockSize - (plaintextLength % blockSize));
			}

			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);

			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);

			return new String(base64.encode(encrypted), encoding);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decodeNoPadding(String data, String secretKey)
			throws NullPointerException {

		if (data.length() == 0 || data == null) {
			throw new NullPointerException("Please give text");
		}
		// FIXME: 2016/4/14 临时替换 +号变-号
//		data = data.replaceAll("\\+", "-");
		try {
			SecretKeySpec keyspec = new SecretKeySpec(secretKey.getBytes(),
					"AES");

			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
			final byte[] iv = new byte[16];
			Arrays.fill(iv, (byte) 0x00);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			Base64 base64 = SingleBase64.instance;
			byte[] encrypedPwdBytes = base64.decode(data.getBytes());
			// cipher is not thread safe
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivParameterSpec);

			byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

			String decrypedValue = new String(decrypedValueBytes);
			return decrypedValue.trim();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		String data = encryptNoPadding(
				"{\"TerminalNo\":\"51516022\",\"LoginName\":\"51516022\",\"BarCode\":\"1_0617215046\",\"CardNo\":\"623084********6292\",\"CardType\":\"1\",\"BusinessTime\":\"2016-06-01 15:03:38\",\"TerminalDealID\":\"000138\",\"UnionpayDealID\":\"150320895735\",\"TotalPayment\":0.01,\"AccountReceived\":0.01,\"State\":\"00\",\"UntreadReasonCode\":\"0\",\"Description\":\"\"}",
				"51homevip1201000");
		System.out.println(data);
		System.out.println(decodeNoPadding(data, "51homevip1201000").trim());

	}
}
