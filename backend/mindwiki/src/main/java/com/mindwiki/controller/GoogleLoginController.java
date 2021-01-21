package com.mindwiki.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@Controller
public class GoogleLoginController {

	private final static String CLIENT_ID = "659791765906-faeludmkkn7l8vqlk37pqlhhisu4n1hb.apps.googleusercontent.com";
	private final static String CLIENT_SECRET = "wIZsG63vDx-o1lJbZW-5Kp_N";
	private final static String REDIRECT_URI = "http://localhost:8000/mindwiki/GoogleOAuth";

	
	@RequestMapping(value = "/GoogleOAuth")
	public String GoogleLogin(@RequestParam(value="code", required=false) String authorizationToken) throws MalformedURLException {

		System.out.println("code"+authorizationToken);
		
		
		
		  final String HostUrl = "https://oauth2.googleapis.com/token";
	      final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	      postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
	      postParams.add(new BasicNameValuePair("client_id", CLIENT_ID)); 
	      postParams.add(new BasicNameValuePair("client_secret", CLIENT_SECRET)); 
	      postParams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));                                                               
	      postParams.add(new BasicNameValuePair("code", authorizationToken)); 
	      final HttpClient client = HttpClientBuilder.create().build();
	      final HttpPost post = new HttpPost(HostUrl);
	      JsonNode returnJson = null;
	      try {
	         post.setEntity(new UrlEncodedFormEntity(postParams));
	         final HttpResponse response = client.execute(post);

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
	      
	      

	      

	      JsonNode result = getUserInfo(returnJson);
	      
	      
	      
	      return "redirect:http://localhost:8080/";
	}


	private JsonNode getUserInfo(JsonNode GoogleRequestJson) {

		
		JsonNode accessToken = GoogleRequestJson.get("access_token");
		

		  final String RequestUrl ="https://www.googleapis.com/oauth2/v1/userinfo?alt=json";		  
	      final HttpClient client = HttpClientBuilder.create().build();
	      final HttpGet get = new HttpGet(RequestUrl);
	 
	      get.addHeader("Authorization", "Bearer " + accessToken);
	      JsonNode returnJson = null;
	      try {

	         final HttpResponse response = client.execute(get);

	     
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

	      
	      
	      return null;
	}
	
	


	
}
