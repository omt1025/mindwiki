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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin("*")
@Controller
public class KakaoLoginController {
	private final static String REST_API_KEY = "0530ead261a6f23c9a61fdba73622fb7";
	private final static String REDIRECT_URI = "http://localhost:8000/mindwiki/oauth";
	
	
	@RequestMapping(value = "/oauth")
	public String kakaoLogin(@RequestParam(value="code", required=false) String authorizationToken) throws MalformedURLException {

		
		  final String RequestUrl = "https://kauth.kakao.com/oauth/token";
	      final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	      postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
	      postParams.add(new BasicNameValuePair("client_id", REST_API_KEY)); 
	      postParams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));                                                               
	      postParams.add(new BasicNameValuePair("code", authorizationToken)); 
	      final HttpClient client = HttpClientBuilder.create().build();
	      final HttpPost post = new HttpPost(RequestUrl);
	      JsonNode returnNode = null;
	      try {
	         post.setEntity(new UrlEncodedFormEntity(postParams));
	         final HttpResponse response = client.execute(post);

	         ObjectMapper mapper = new ObjectMapper();
	         returnNode = mapper.readTree(response.getEntity().getContent());
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      } catch (ClientProtocolException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } finally {

	      }
	      
	      

	      

	      JsonNode result = getUserInfo(returnNode);
	      
	      
	      
	      return "redirect:http://localhost:8080/";
	}
	
	
	
	public JsonNode getUserInfo(JsonNode kakao_access_Json) {
		
		
		JsonNode accessToken = kakao_access_Json.get("access_token");
		
		
		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
	      final HttpClient client = HttpClientBuilder.create().build();
	      final HttpPost post = new HttpPost(RequestUrl);

	      post.addHeader("Authorization", "Bearer " + accessToken);
	      JsonNode returnNode = null;
	      try {
	         final HttpResponse response = client.execute(post);

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
	
	  	
	  	System.out.println(accessToken);
	  	
		return null;
		
	}

	
}


