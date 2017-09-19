package com.har.unmanned.mfront.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

	//"算法/模式/补码方式"
	private static String TRANSFORMAT = "RSA/ECB/PKCS1Padding";
	
	/**
	 * 私钥
	 */
	private RSAPrivateKey privateKey;

	/**
	 * 公钥
	 */
	private RSAPublicKey publicKey;

	/**
	 * 获取私钥
	 * 
	 * @return 当前的私钥对象
	 */
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}
	
	/**
	 * 获取私钥Base64编码字符串
	 * 
	 * @return 当前的私钥Base64编码字符串
	 * @throws UnsupportedEncodingException 
	 */
	public String getPrivateKeyByBase64() throws UnsupportedEncodingException {
		return new String(Base64.encodeBase64(privateKey.getEncoded()), "UTF-8");
	}

	/**
	 * 获取公钥
	 * 
	 * @return 当前的公钥对象
	 */
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * 获取公钥Base64编码字符串
	 * 
	 * @return 当前的公钥对象
	 * @throws UnsupportedEncodingException 
	 */
	public String getPublicKeyByBase64() throws UnsupportedEncodingException {
		return new String(Base64.encodeBase64(publicKey.getEncoded()), "UTF-8");
	}
	
	/**
	 * 随机生成密钥对
	 * @throws Exception 
	 */
	public void genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法", e);
		}
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
		this.publicKey = (RSAPublicKey) keyPair.getPublic();
	}

	/**
	 * 从文件中输入流中加载公钥
	 * 
	 * @param in
	 *            公钥输入流
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public void loadPublicKey(InputStream in) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPublicKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误", e);
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空", e);
		}
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public void loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64.decodeBase64(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法", e);
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空", e);
		}
	}

	/**
	 * 从文件中加载私钥
	 * 
	 * @param in
	 *            私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public void loadPrivateKey(InputStream in) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPrivateKey(sb.toString());
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误", e);
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空", e);
		}
	}

	public void loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64.decodeBase64(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法", e);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception("私钥非法", e);
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空", e);
		}
	}

	/**
	 * 加密过程
	 *
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public String encrypt(String plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(TRANSFORMAT);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(output), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法", e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法", e);
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏", e);
		}
	}

	/**
	 * 签名过程
	 *
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public String sign(String plainTextData) throws Exception {
		if (privateKey == null) {
			throw new Exception("签名私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(TRANSFORMAT);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(output), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法", e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("签名私钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法", e);
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏", e);
		}
	}
	
	/**
	 * 解密过程
	 *
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public String decrypt(String cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(TRANSFORMAT);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(Base64.decodeBase64(cipherData.getBytes("UTF-8")));
			return new String(output, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法", e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法", e);
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏", e);
		}
	}

	public String verifySign(String cipherData) throws Exception {
		if (publicKey == null) {
			throw new Exception("解密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(TRANSFORMAT);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(Base64.decodeBase64(cipherData.getBytes()));
			return new String(output, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法", e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密公钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法", e);
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏", e);
		}
	}
	
	/**
	 * 读取文件内容
	 * @param file
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("all")
	public String file2String(File file, String encoding) {
		if(file == null){
			return null;
		}
		InputStreamReader reader = null;
		StringWriter writer = new StringWriter();
		try {
			if (encoding != null && !"".equals(encoding.trim())) {
				reader = new InputStreamReader(new FileInputStream(file),
						encoding);
			} else {
				reader = new InputStreamReader(new FileInputStream(file));
			}
			// 将输入流写入输出流
			char[] buffer = new char[1024];
			int n = 0;
			while (-1 != (n = reader.read(buffer))) {
				writer.write(buffer, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		// 返回转换结果
		if (writer != null){
			return writer.toString();
		}else{
			return null;
		}
	}
	
	/**
	 * 字符串转文件
	 * @param res
	 * @param filePath
	 * @return
	 */
	public static boolean string2File(String res, String filePath) {
		boolean flag = true;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			File distFile = new File(filePath);
			if (!distFile.getParentFile().exists())
				distFile.getParentFile().mkdirs();
			bufferedReader = new BufferedReader(new StringReader(res));
			bufferedWriter = new BufferedWriter(new FileWriter(distFile));
			char buf[] = new char[1024]; // 字符缓冲区
			int len;
			while ((len = bufferedReader.read(buf)) != -1) {
				bufferedWriter.write(buf, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
			return flag;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	/**
	 * for test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		RSAUtil rsaUtil = new RSAUtil();
		
		try {
			rsaUtil.genKeyPair();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 加载公钥
		try {
			rsaUtil.loadPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUAOSkU2QdzR6nfjdxC48On14h5KTvGuNN9RrT/3ZcQa6Kulq/JNeWYRaa1jjLIWeBf6xdvYPz8PcWtr9FEHDMkbKo5A/YCB+uBu4ds23PNTcNy4BRkTmIzLFrZvdOL+/KUqqPRuajvPYDC4dAkqvK1zwgOvczV2hiTU58+JTK1wIDAQAB");
			System.out.println(rsaUtil.getPublicKeyByBase64());
			System.out.println("加载公钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载公钥失败");
		}

		// 加载私钥
		try {
			rsaUtil.loadPrivateKey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJQA5KRTZB3NHqd+N3ELjw6fXiHkpO8a4031GtP/dlxBroq6Wr8k15ZhFprWOMshZ4F/rF29g/Pw9xa2v0UQcMyRsqjkD9gIH64G7h2zbc81Nw3LgFGROYjMsWtm904v78pSqo9G5qO89gMLh0CSq8rXPCA69zNXaGJNTnz4lMrXAgMBAAECgYB1gBgFleb/+d8CXqQpbWHP3BphkiKXaaNYOuVntYMET2kA3l45LkiyLLZDixtfuU9xcikn6tOdkYq5T7CeZiUGLu4F+/cchoOs1mJyKnpvocdr10zyhmTzDz4uBvTVGxMQliBzV46vCQfqimYV3osEc3ht/04SCujfGgtQU6LkEQJBAMZAITKaPqIRkkz/zLrmUZOylTllz0YfhUtauLprGwg1VJBTgyQMDMRNgYPCuWDl5oQffyjnssFCCmjlPD8LRyMCQQC/HcJ68pSoSlXQeBmVhXGBo5Qe2RcfvLqJgo9/oaleifpGZOVEJ2bNuDQiKWREebO4Wy2I6dsYKuE2Ke1zMQK9AkEAw8j9gA3YIRIdW7GbrFBc6/IHcOHcqzHh36M+n8u6rIaOduIituy3hv3MFCWwEcO5vW2llO4NGNJxmVoZ/2DR5QJAJbS3ZA5sKe/Qknq/gXJ8fHlLhfKKz2Yq32p06Vd51P8f87eATDwB2mCXWI4WDDR+ssa6ayLIaTI8kLUg9fMbeQJBAJVN1C697ByWF5wes6XCeSmEu68+qbvL3se2AZGJ65iRcL3BI+Q1W1opgQHxAKuvUOLN5j31bdjv60HWeRWURrI=");
			System.out.println(rsaUtil.getPrivateKeyByBase64());
			System.out.println("加载私钥成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载私钥失败");
		}
		

		// 测试字符串
		String encryptStr = "c:/ab/cd/中国人.jpggg&(";

		try {
			// 加密
			String cipher = rsaUtil.encrypt(encryptStr);

			// 解密
			String plainText = rsaUtil.decrypt(cipher);

			System.out.println("密文:" + cipher);
			System.out.println("明文:" + plainText);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * B2B生码系统制作签名
	 * @param rawText 明文
	 * @return
	 * @throws Exception
	 * 制作签名过程中的异常信息
	 */
	public String makeSign(String rawText) throws Exception {
		if (this.privateKey == null) {
			throw new Exception("制作签名私钥加载失败"); // 私钥
		}
		String signHex = null;
		try {
			rawText = Md5Util.md5(rawText);
			byte[] signTarget = rawText.getBytes("UTF-8"); //签名对象
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initSign(this.privateKey);
			signature.update(signTarget);
			byte[] signBytes = signature.sign(); // 二进制签名结果
			signHex = new String(Base64.encodeBase64(signBytes), "UTF-8");
		}catch (InvalidKeyException e) {
			throw new Exception("签名私钥非法,请检查", e);
		}
		return signHex;
	}
	
	
}
