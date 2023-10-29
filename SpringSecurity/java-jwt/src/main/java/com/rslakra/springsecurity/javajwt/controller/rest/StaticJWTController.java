package com.rslakra.springsecurity.javajwt.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.rslakra.springsecurity.javajwt.controller.BaseController;
import com.rslakra.springsecurity.javajwt.model.JwtResponse;
import com.rslakra.springsecurity.javajwt.service.SecretsService;
import com.rslakra.springsecurity.javajwt.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

@RestController
public class StaticJWTController extends BaseController {

    private final SecretsService secretsService;

    /**
     * @param secretsService
     */
    @Autowired
    public StaticJWTController(SecretsService secretsService) {
        this.secretsService = secretsService;
    }

    @RequestMapping(value = "/static-builder", method = GET)
    public JwtResponse fixedBuilder() throws UnsupportedEncodingException {
        String jws = Jwts.builder()
            .setIssuer("Rohtash Lakra")
            .setSubject("rslakra")
            .claim("name", "Rohtash Lakra")
            .claim("scope", "admin")
            .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L))) // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
            .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L))) // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
            .signWith(SignatureAlgorithm.HS256, secretsService.getHS256SecretBytes())
            .compact();

        return new JwtResponse(jws);
    }

    @RequestMapping(value = "/parser", method = GET)
    public JwtResponse parser(@RequestParam String jwt) throws UnsupportedEncodingException {
        Jws<Claims> jws = JWTUtils.parseJWTToken(jwt, secretsService.getSigningKeyResolver());
        return new JwtResponse(jws);
    }

    @RequestMapping(value = "/parser-enforce", method = GET)
    public JwtResponse parserEnforce(@RequestParam String jwt) throws UnsupportedEncodingException {
        Jws<Claims> jws = Jwts.parser()
            .requireIssuer("Rohtash Lakra")
            .require("hasAutomobile", true)
            .setSigningKeyResolver(secretsService.getSigningKeyResolver())
            .parseClaimsJws(jwt);

        return new JwtResponse(jws);
    }
}
