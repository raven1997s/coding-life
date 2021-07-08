package com.raven.common.utils;

/**
 * @author qindongyun
 * @Description:
 * @date 2020/6/26
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    /**
     * 对称加密
     *
     * @param input
     * @param key
     * @return
     */
    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
            return new String(Base62.createInstance().encode(crypted));
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 对称解密
     *
     * @param input
     * @param key
     * @return
     */
    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base62.createInstance().decode(input.getBytes()));
            return new String(output);
        } catch (Exception e) {

        }
        return "";
    }

    /*public static void main(String[] args) {
        String key = "1234567890abcdef";
        String data = "973423656,1567934187864,https://www.baidu.com,01051234dijfejfo09";
        String data1 = "{\"a\":\"face\",\"b\":\"1\",\"c\":\"https://www.baidu.com,01051234dijfejfo09\"}";
        String encrypt = encrypt(data1, key);
        //1lzxghGaXExtA9q2HsG3CAwE7kYM15NtPacuk3PAQk08oQYQdFXrjq0YOeReEngkwSxjyIA9Km3InjOqMhMqvsjzNcgqRRehUMhJLYx5RSaO
        System.out.println("加密数据为:" + encrypt);

        String decrypt = decrypt(encrypt, key);
        System.out.println("解密数据为:" + decrypt);

        String code = "46FN1n5vFjjtueygVrXQvZXKSXc2vN7TllWtdAyRAGPff3O6z8HzauyjECydhni1z9eKAh9syt8TjyGwGi3K7EVDolDH0cwVry9mBWDRKnOggUQgqQ20crqGrAz7WYwvC6Lod4yQxYcTlnj6pzmIQOnF4kOPEbvfpOd467AxWYr2MbKPQkZlPXox9yj0dce82Pr6AXOXdfAqp9DVJFyc5bm6YapxwscbJSFRBsEH0a2oK";
        String sskey = "1lzxghGaXExtA9q2HsG3CAwE7kYM15Nt";
        System.out.println("揭秘：" + decrypt(code, sskey));
    }*/
}
