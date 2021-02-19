/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : kakao login
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindwiki.model.OauthDto;
import com.mindwiki.service.JwtService;

@Controller
@RequestMapping("/mindwiki")
public class KakaoLoginController {
	private final static String REST_API_KEY = "0530ead261a6f23c9a61fdba73622fb7";
	private final static String REDIRECT_URI = "http://localhost:8000/mindwiki/oauth";

	@Autowired
	private JwtService jwtSvc;

	@RequestMapping(value = "/oauth")
	public RedirectView kakaoLogin(HttpSession hs,
			@RequestParam(value = "code", required = false) String authorizationToken) throws MalformedURLException {

		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", REST_API_KEY)); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI)); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", authorizationToken)); // 로그인 과정중 얻은 code 값
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		String jwt = getUserInfo(returnNode);// jwt를 리턴해주지 않아도 생성된것임

		return new RedirectView("http://localhost:8080/?jwt=" + jwt);// 만약에 returnnode를 하면 개인정보가 누출되기때문에 안됨
	}

	public String getUserInfo(JsonNode kakao_access_Json) {
		String jwt = null;

		JsonNode accessToken = kakao_access_Json.get("access_token");

		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		post.addHeader("Authorization", "Bearer " + accessToken);
		JsonNode returnNode = null;
		try {
			final HttpResponse response = client.execute(post);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}


		String kakao_email = null;
		String kakao_name = null;

		JsonNode profile = returnNode.path("properties");
		JsonNode kakao_account = returnNode.path("kakao_account");
		kakao_email = kakao_account.path("email").asText();
		kakao_name = profile.path("nickname").asText();

		System.out.println(kakao_email);
		System.out.println(kakao_name);

		if (kakao_email != null) {

			jwt = jwtSvc.createToken("userInfo", kakao_email, kakao_name);
			// 만들어주면 알아서 서버에저장됨

		}

		System.out.println(accessToken);

		return jwt;

	}

}
