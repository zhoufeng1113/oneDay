package com.homevip.util.thridparty.ping;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by hhq on 2017/4/19.
 */
public class WebhooksVerify {

  /*  private static String pubKeyPath = File.separator+ SystemPath.getClassPath()+"res"+ File.separator+"pingpp_public_key.pem";
    private static String eventPath = File.separator+SystemPath.getClassPath()+"res"+ File.separator+"webhooks_raw_post_data.json";
    private static String signPath = File.separator+SystemPath.getClassPath()+"res"+ File.separator+"signature.txt";
*/
    /**
     * 验证 webhooks 签名，仅供参考

     * @throws Exception
     */

    public static void runDemos(String eventPath,String signPath,String pubKeyPath) throws Exception {
        // 该数据请从 request 中获取原始 POST 请求数据, 以下仅作为示例
        String webhooksRawPostData = getStringFromFile(eventPath);
//        System.out.println("------- POST 原始数据 -------");
//        System.out.println(webhooksRawPostData);
        // 签名数据请从 request 的 header 中获取, key 为 X-Pingplusplus-Signature (请忽略大小写, 建议自己做格式化)
        String signature = getStringFromFile(signPath);
//        System.out.println("------- 签名 -------");
//        System.out.println(signature);
        boolean result = verifyData(webhooksRawPostData, signature, getPubKey(pubKeyPath));
//        System.out.println("验签结果：" + (result ? "通过" : "失败"));
    }

    /**
     * 读取文件, 部署 web 程序的时候, 签名和验签内容需要从 request 中获得
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String getStringFromFile(String filePath) throws Exception {
        FileInputStream in = new FileInputStream(filePath);
        InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
        BufferedReader bf = new BufferedReader(inReader);
        StringBuilder sb = new StringBuilder();
        String line;
        do {
            line = bf.readLine();
            if (line != null) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } while (line != null);

        return sb.toString();
    }

    /**
     * 获得公钥
     * @return
     * @throws Exception
     */
    public static PublicKey getPubKey(String pubKeyPath) throws Exception {
        String pubKeyString = getStringFromFile(pubKeyPath);
        pubKeyString = pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        byte[] keyBytes = Base64.decodeBase64(pubKeyString);

        // generate public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }

    /**
     * 验证签名
     * @param dataString
     * @param signatureString
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifyData(String dataString, String signatureString, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        byte[] signatureBytes = Base64.decodeBase64(signatureString);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(dataString.getBytes("UTF-8"));
        return signature.verify(signatureBytes);
    }
}
