package com.lin.ynnzbigdata.service;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: linbaobao
 * @Date: 2022/6/14
 * @explain:
 */
public class JWToken {

    private static long time = 1000*60*30;
    private static String signature = "admin";
    //创建token的方法
    public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","tom")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return jwtToken;
    }
    //校验token，布尔类型
    public static boolean checkToken(String token){
        if (token ==null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
