
// from: https://codereview.stackexchange.com/questions/61294/bouncycastle-rijndael-256-implementation

@Grapes(
      @Grab(group='org.apache.directory.studio', module='org.bouncycastle.bcprov.jdk15', version='140')
  )
@Grapes(
      @Grab(group='org.bouncycastle', module='bcprov-ext-jdk15on', version='1.70')
  )

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Base64;

/*
    PKCS12ParametersGenerator pGen
        - generator for PBE derived keys
        - PBE: Password Based Encryption
        - operates on password, salt, iterationCount

    BufferedBlockCipher
        - wrapper class, base class

    PaddedBufferedBlockCipher
        - takes cbc and PKCS7Padding

    PKCS7Padding
        - implements same interface as ZeroBytePadding
*/

class MyTools {
    def password
    def salt
    def iterationCount

    MyTools(def password, def salt, def iterationCount) {
        this.password = password
        this.salt = salt
        this.iterationCount = iterationCount
    }

    def buildPGen() {
        PKCS12ParametersGenerator pGen = new PKCS12ParametersGenerator(new SHA3Digest(256));
        char[] passwordChars = password;

        final byte[] pkcs12PasswordBytes = PBEParametersGenerator.PKCS12PasswordToBytes(passwordChars);
        pGen.init(pkcs12PasswordBytes, salt , iterationCount);
        return pGen
    }

    def buildCipher() {
        BlockCipher engine = new RijndaelEngine(256);
        CBCBlockCipher cbc = new CBCBlockCipher(engine);
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbc, new ZeroBytePadding());
        return cipher;
    }

    String encrypt(def inputText) {
        def pGen = buildPGen();

        def cipher = buildCipher()

        ParametersWithIV aesCBCParams = (ParametersWithIV) pGen.generateDerivedParameters(256, 256);
        cipher.init(true, aesCBCParams);

        byte[] input = inputText.getBytes();
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];

        int cipherLength = cipher.processBytes(input, 0, input.length, cipherText, 0);
        cipher.doFinal(cipherText, cipherLength);

        String result = new String(Base64.encode(cipherText));
        return result;
    }

    String decrypt(def text) {
        def pGen = buildPGen();

        def cipher = buildCipher()

        ParametersWithIV aesCBCParams = (ParametersWithIV) pGen.generateDerivedParameters(256, 256);
        cipher.init(false, aesCBCParams);

        byte[] output = Base64.decode(text.getBytes());
        byte[] cipherText = new byte[cipher.getOutputSize(output.length)];

        int cipherLength = cipher.processBytes(output, 0, output.length, cipherText, 0);
        int outputLength = cipher.doFinal(cipherText, cipherLength);
        outputLength += cipherLength;

        byte[] resultBytes = cipherText;
        if (outputLength != output.length) {
            resultBytes = new byte[outputLength];
            System.arraycopy(
                    cipherText, 0,
                    resultBytes, 0,
                    outputLength
                    );
        }

        String result = new String(resultBytes);
        return result
    }
}

// --------

def inputText = "The quick brown fox jumped over the lazy dog."

def password = "0123456789abcdef0123456789abcdef".toCharArray();
def salt = "0123456789".getBytes();
def iterationCount = 0;
def myTools = new MyTools(password, salt, iterationCount)

def encText = myTools.encrypt(inputText)
def decText = myTools.decrypt(encText)

println "TRACER encText  : " + encText
println "TRACER decText  : " + decText
println "TRACER equal?   : " + (inputText == decText)

println "Ready."
