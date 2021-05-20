package com.xiaobo.springboot2.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Copyright (C), 2000-2019, 北京天融信有限公司<br>
 * FileName: DecryptUtils<br>
 *
 * @author: 刘伟<br>
 * Date: 2019/9/11 16:38<br>
 * Description: 解密类
 */
public class DecryptUtils {

  private static final String KEY = "8NONwyJtHesysWpM";

  private static final String IV = "8NONwyJtHesysWpM";

  /**
   * 解密方法
   *
   * @param data 要解密的数据
   * @param key  解密key
   * @param iv   解密iv
   * @return 解密的结果
   */
  public static String desEncrypt(String data, String key, String iv) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
      byte[] encrypted = parseHexStr2Byte(data);
      assert encrypted != null;
      byte[] original = cipher.doFinal(encrypted);
      return new String(original).trim();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String encrypt(String data) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      int blockSize = cipher.getBlockSize();

      byte[] dataBytes = data.getBytes();
      int plaintextLength = dataBytes.length;
      if (plaintextLength % blockSize != 0) {
        plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
      }

      byte[] plaintext = new byte[plaintextLength];
      System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

      SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
      IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());

      cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
      byte[] encrypted = cipher.doFinal(plaintext);
      return byte2hex(encrypted);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  /**
   * 使用默认的key和iv解密
   *
   * @param data
   * @return
   */
  public static String desEncrypt(String data) {
    return desEncrypt(data, KEY, IV);
  }

  /*字节数组转成16进制字符串  */
  public static String byte2hex(byte[] bytes) { // 一个字节的数，
    StringBuilder sb = new StringBuilder(bytes.length * 2);
    String tmp = "";
    for (int n = 0; n < bytes.length; n++) {
      // 整数转成十六进制表示
      tmp = (Integer.toHexString(bytes[n] & 0XFF));
      if (tmp.length() == 1) {
        sb.append("0");
      }
      sb.append(tmp);
    }
    return sb.toString(); // 转成大写
  }

  /**
   * 将16进制转换为二进制
   *
   * @param hexStr 加密文本
   * @return byte[]
   */
  private static byte[] parseHexStr2Byte(String hexStr) {
    if (hexStr.length() < 1) {
      return null;
    }
    byte[] result = new byte[hexStr.length() / 2];
    for (int i = 0; i < hexStr.length() / 2; i++) {
      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
      result[i] = (byte) (high * 16 + low);
    }
    return result;
  }

  public static void main(String[] args) {

    /*try {
      String encrypt = encrypt("talent123");
      System.out.format("解密的结果是:%s\n", encrypt);
    } catch (Exception e) {
      e.printStackTrace();
    }*/

    String encrypt = encrypt("Talent@123");
    System.out.println(encrypt);

   /* String s = desEncrypt("d440d36b2364b4031049bee8faaf0393");
    System.out.format("解密的结果是:%s\n",s);*/

  }
}
