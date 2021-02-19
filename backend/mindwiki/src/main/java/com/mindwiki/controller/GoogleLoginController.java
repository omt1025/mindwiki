/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : google로그인
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindwiki.model.OauthDto;
import com.mindwiki.service.JwtService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/mindwiki")
public class GoogleLoginController {
	OauthDto oauth = new OauthDto();
	private final static String CLIENT_ID = "659791765906-faeludmkkn7l8vqlk37pqlhhisu4n1hb.apps.googleusercontent.com";
	private final static String CLIENT_SECRET = "wIZsG63vDx-o1lJbZW-5Kp_N";
	private final static String REDIRECT_URI = "http://localhost:8000/mindwiki/GoogleOAuth";

	@Autowired
	private JwtService jwtSvc;

	@RequestMapping(value = "/GoogleOAuth")
	public RedirectView GoogleLogin(HttpSession hs,
			@RequestParam(value = "code", required = false) String authorizationToken) throws MalformedURLException {

		System.out.println("code" + authorizationToken);

		final String HostUrl = "https://oauth2.googleapis.com/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", CLIENT_ID)); // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", CLIENT_SECRET)); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI)); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", authorizationToken)); // 로그인 과정중 얻은 code 값
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(HostUrl);
		JsonNode returnJson = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnJson = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}


		String jwt = getUserInfo(returnJson);

		return new RedirectView("http://localhost:8080/?jwt=" + jwt);
	}

	private String getUserInfo(JsonNode GoogleRequestJson) {
		String jwt = null;

		JsonNode accessToken = GoogleRequestJson.get("access_token");

		final String RequestUrl = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet get = new HttpGet(RequestUrl);

		get.addHeader("Authorization", "Bearer " + accessToken);
		JsonNode returnJson = null;
		try {

			final HttpResponse response = client.execute(get);
			// JSON 형태 반환값 처리

			ObjectMapper mapper = new ObjectMapper();
			returnJson = mapper.readTree(response.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}


		String Google_email = null;
		String Google_name = null;
		Google_email = returnJson.path("email").asText();
		Google_name = returnJson.path("name").asText();
		System.out.println(Google_email);
		System.out.println(Google_name);


		if (Google_email != null) {

			jwt = jwtSvc.createToken("userInfo", Google_email, Google_name);

		}
		return jwt;
	}

}
