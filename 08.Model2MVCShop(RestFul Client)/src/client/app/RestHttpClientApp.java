package client.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;
import com.sun.javafx.collections.MappingChange.Map;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
		//RestHttpClientApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp.LoginTest_Codehaus();		
		
//		System.out.println("\n====================================\n");
//		// 3.1 Http GET ��� Request : JsonSimple lib ���
//		RestHttpClientApp.AddUserGetTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 3.2 Http GET ��� Request : CodeHaus lib ���

		
//		System.out.println("\n====================================\n");
//		// 4.1 Http POST ��� Request : JsonSimple lib ���
//		RestHttpClientApp.AddUserPostTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 4.2 Http POST ��� Request : CodeHaus lib ���	
		RestHttpClientApp.AddUserPostTest_Codehaus();
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println("Response Ȯ��");
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		System.out.println("�����б�(JSON Value Ȯ��)");
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		
		
		/////////////[ ��� 3 : codehaus ���]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		System.out.println(jsonValue);
		//////////////////*
		
		
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
	
	//================================================================//
	//3.1 Http Protocol GET Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void AddUserGetTest_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/user/json/addUser";
		
		//HttpGet or HttpPost ������ Header ����
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
			
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
	}
	
	//3.2 Http Protocol GET Request : FromData ���� / JsonSimple 3rd party lib ���
	//�� navigation�� �ϴϱ� �����Ұ�...
	
	//4.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void AddUserPostTest_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/user/json/addUser";
		
		//HttpGet or HttpPost ������ Header ����
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ JSONObject ����Ͽ� body�� ������ �־� post������� ������]
		JSONObject json = new JSONObject();
		json.put("userId", "user991");
		json.put("userName", "������");
		json.put("password", "1234");
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		httpPost.setEntity(httpEntity01);
	
		/////////////////Enter///////////////////
		HttpResponse httpResponse = httpClient.execute(httpPost);
		/////////////////////////////////////////
		System.out.println("Response Ȯ��");
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		System.out.println("�����б�(JSON Value Ȯ��)");
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
	}
	//4.2 Http Protocol POST Request : FromData ���� / Codehaus 3rd party lib ���
	public static void AddUserPostTest_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/user/json/addUser";
		
		//HttpGet or HttpPost ������ Header ����
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ User domain ��ü�� ����Ͽ� ObjectMapper�� ���� JSONValue��!]
		User user = new User();
		user.setPassword("1234");
		user.setUserId("asdf212311111");
		user.setUserName("�载��");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue.toString(),"utf-8");
		httpPost.setEntity(httpEntity01);
	
		/////////////////Enter///////////////////
		HttpResponse httpResponse = httpClient.execute(httpPost);
		/////////////////////////////////////////
		System.out.println("Response Ȯ��");
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	
		//==> �����б�(JSON Value Ȯ��)
		System.out.println("�����б�(JSON Value Ȯ��)");
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user01 = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		HashMap<String,Object> map = objectMapper.readValue(jsonValue, new TypeReference<HashMap<String,Object>>(){});
		System.out.println("�����б�(JSON Value�� User�� redirect Ȯ��)");
		System.out.println(user01);
		System.out.println(map.get("user"));
		System.out.println(map.get("redirect"));		
	}


	
}