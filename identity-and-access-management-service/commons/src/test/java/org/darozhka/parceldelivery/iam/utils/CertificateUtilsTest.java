package org.darozhka.parceldelivery.iam.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jose.jwk.RSAKey;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author S.Darozhka
 */
class CertificateUtilsTest {

    @Test
    void testGenerateRsaKey() {
        RSAKey rsaKey = CertificateUtils.generateRsaKey();

        Assertions.assertNotNull(rsaKey.getPrivateExponent());
        Assertions.assertNotNull(rsaKey.getPublicExponent());
        Assertions.assertNotNull(rsaKey.getModulus());
        Assertions.assertEquals(KeyType.RSA, rsaKey.getKeyType());
    }
}