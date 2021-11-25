package top.ahcdc.periodical.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;


public class JWTUtils {
    private static final String SING="M1JxLv3BCUwXr6j2khA4n9fbSNolVtZzdQsRWaHiqcKeD0PIT8F75GYugOpEymcOvYvV9CZb5deWb6HMro25AigweIjtN22XHlzAzEWWrzRScBlAbJcmro4kZNtvZwmf4OjlImYxvV9fRTyN0DIXtK5LcwgoDRug4MOxthuOwVphwVfYXOcvkiiDKJfdGhAntQisLfzooC18k9fDQL5hBM4Wo3MRT839UzC7kqF1GJel7hb6hlSCfxBbGlBDspQY";

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);//默认7天过期
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC384(SING));

        return token;
    }

    /**
     * 验证token合法性
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC384(SING)).build().verify(token);
    }

    /**
     * 返回JWT错误信息
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC384(SING)).build().verify(token);
        return verify;
    }
//    public static Map<String,String> ParseJWT(String token){
//        DecodedJWT decode = JWT.decode(token);
//        decode
//    }
}