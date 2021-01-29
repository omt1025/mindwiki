package com.mindwiki.service;




import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LoginDao;
import com.mindwiki.dao.MemberInfoDao;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.OauthDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	@Autowired
	private SqlSession session;
	
	
	static String secretKey="itsmyfirstSecretKeyforProject39";
	private static final String SECRET_KEY=  Base64.getEncoder().encodeToString(secretKey.getBytes());
	 
	Long expiredTime = 1000 * 60L * 60L * 2L; // 토큰 유효 시간 (2시간)
	

	   @Override
	    public String createToken(String subject, String email, String nickName) {
		   

	        Map<String, Object> headers = new HashMap<>();
	        headers.put("typ", "JWT");
	        headers.put("alg", "HS256");


	        Map<String, Object> payloads = new HashMap<>();

	        payloads.put("email", email);
	        payloads.put("nickName", nickName);
	        

	        Date ext = new Date(); // 토큰 만료 시간
	        ext.setTime(ext.getTime() + expiredTime);
	     
	        // 토큰 Builder
	        String jwt = Jwts.builder()
	                .setHeader(headers) // Headers 설정
	                .setClaims(payloads) // Claims 설정
	                .setSubject(subject) // 토큰 용도 
	                .setExpiration(ext) // 토큰 만료 시간 설정
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // HS256과 Key로 Sign
	                .compact(); // 토큰 생성

	        return jwt;
	
	    }


	//토큰 검증
	@Override
    public Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        
        System.out.println(jwt);
        System.out.println("jwt가 문제없이 인증된경우");
        
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            claimMap = claims;

            
        } catch (ExpiredJwtException e) { 
            System.out.println(e);
            
        } catch (Exception e) { 
            System.out.println(e);
           
        }
        return claimMap;
    }


	@Override
	public ProfileDto takeMemberInfo(String email) throws SQLException {
		ProfileDto member=new ProfileDto();
		member=session.getMapper(MemberInfoDao.class).login(email);//sql실행한 뒤에 member
		return member;
	}


	
	 

}
