package com.rslakra.springsecurity.javajwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

@Service
public class SecretsService {

    private Map<String, String> secrets = new HashMap<>();

    /**
     *
     */
    private SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
        @Override
        public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
            return TextCodec.BASE64.decode(secrets.get(header.getAlgorithm()));
        }
    };

    /**
     *
     */
    @PostConstruct
    public void initObject() {
        refreshSecrets();
    }

    /**
     * @return
     */
    public SigningKeyResolver getSigningKeyResolver() {
        return signingKeyResolver;
    }

    /**
     * @return
     */
    public Map<String, String> getSecrets() {
        return secrets;
    }

    /**
     * @param secrets
     */
    public void setSecrets(Map<String, String> secrets) {
        Assert.notNull(secrets);
        Assert.hasText(secrets.get(SignatureAlgorithm.HS256.getValue()));
        Assert.hasText(secrets.get(SignatureAlgorithm.HS384.getValue()));
        Assert.hasText(secrets.get(SignatureAlgorithm.HS512.getValue()));

        this.secrets = secrets;
    }

    /**
     * @return
     */
    public byte[] getHS256SecretBytes() {
        return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS256.getValue()));
    }

    /**
     * @return
     */
    public byte[] getHS384SecretBytes() {
        return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS384.getValue()));
    }

    /**
     * @return
     */
    public byte[] getHS512SecretBytes() {
        return TextCodec.BASE64.decode(secrets.get(SignatureAlgorithm.HS512.getValue()));
    }

    /**
     * @return
     */
    public Map<String, String> refreshSecrets() {
        SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS256);
        secrets.put(SignatureAlgorithm.HS256.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
        key = MacProvider.generateKey(SignatureAlgorithm.HS384);
        secrets.put(SignatureAlgorithm.HS384.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
        key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        secrets.put(SignatureAlgorithm.HS512.getValue(), TextCodec.BASE64.encode(key.getEncoded()));
        return secrets;
    }
}
