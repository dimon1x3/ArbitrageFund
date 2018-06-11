import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import org.bitcoinj.core.Base58;

/**
 * Created by dimo on 23.05.2018.
 */
public class BitcoinAddressGenerator {
    public static void main(String[] args) {
        KeyPairGenerator keyGen = null;
        ECGenParameterSpec ecSpec;
        MessageDigest sha = null;
        byte[] s1 = null;
        MessageDigest rmd = null;
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
        try {
            keyGen = KeyPairGenerator.getInstance("EC");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        try {
            ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        KeyPair kp = keyGen.generateKeyPair();
        PublicKey pub = kp.getPublic();
        PrivateKey pvt = kp.getPrivate();
        ECPrivateKey epvt = (ECPrivateKey) pvt;
        String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();
        System.out.println("s[" + sepvt.length() + "]: " + sepvt);
        ECPublicKey epub = (ECPublicKey) pub;
        ECPoint pt = epub.getW();
        String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;
        System.out.println("bcPub: " + bcPub);
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            s1 = sha.digest(bcPub.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        System.out.println("  sha: " + bytesToHex(s1).toUpperCase());
        try {
            rmd = MessageDigest.getInstance("RipeMD160", "SC");
        } catch (NoSuchProviderException | NoSuchAlgorithmException nspe) {
            nspe.printStackTrace();
        }
        byte[] r1 = rmd.digest(s1);
        byte[] r2 = new byte[r1.length + 1];
        r2[0] = 0;
        for  (int i = 0; i < r1.length; i++) r2[i + 1] = r1[i];
        System.out.println("  rmd: " + bytesToHex(r2).toUpperCase());
        byte[] s2 = sha.digest(r2);
        System.out.println("  sha: " + bytesToHex(s2).toUpperCase());
        byte[] s3 = sha.digest(s2);
        System.out.println("  sha: " + bytesToHex(s3).toUpperCase());
        byte[] a1 = new byte[25];
        for (int i = 0 ; i < r2.length ; i++) a1[i] = r2[i];
        for (int i = 0 ; i < 5 ; i++) a1[20 + i] = s3[i];
        System.out.println("  adr: " + Base58.encode(a1));
    }

    private static String adjustTo64(String s) {
        switch (s.length()) {
            case 62:
                return "00" + s;
            case 63:
                return "0" + s;
            case 64:
                return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }

    private static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (final byte b : in)
            builder.append(String.format("%02x", b));
        return builder.toString();
    }
}
