package no.hvl.dat110.aciotdevice.client;
import java.io.IOException;

import com.google.gson.Gson;
import okhttp3.*;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		

		AccessMessage m= new AccessMessage(message);
		Gson gson=new Gson();
		
		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(m.getMessage()));
		
		Request request = new Request.Builder()
				
				.url("http://"+ Configuration.host+":"+ Configuration.port+logpath)
				.post(body)
				.build();

		System.out.println(request.toString());

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		
		OkHttpClient client = new OkHttpClient();

		Gson gson=new Gson();
		Request request = new Request.Builder()
		  .url("http://"+ Configuration.host+":"+ Configuration.port+codepath)
		  .get()
		  .build();

		System.out.println(request.toString());
		
		try (Response response = client.newCall(request).execute()) {
		      System.out.println ();
		     code=gson.fromJson(response.body().string(), AccessCode.class);
		    }
	   catch (IOException e) {
		   e.printStackTrace();
	   }
	
		return code;
	}
}
