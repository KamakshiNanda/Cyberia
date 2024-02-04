/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author KAMAKSHI
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class VerifyRecaptcha {
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6Ldd1L4UAAAAAH6jIG_xf5JT0chdQuS1U1_0nmRX";//server key
        //public static final String secret = "6LcWsb4UAAAAACySHKO2m9WcEqfMZErh6mBFFXyz";//key for localhost
	private final static String USER_AGENT = "Chrome";

	public static String verify(String gRecaptchaResponse) throws IOException {
            System.out.println(gRecaptchaResponse+"ab");
            
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return "yash";
		}
		try{
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                System.out.println("connection opened for recaptcha");
		// add request header
                con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String postParams = "secret=" + secret + "&response="
				+ gRecaptchaResponse;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                System.out.println("Response in string:-");
		// print result
		System.out.println(response.toString());
		//parse JSON response and return 'success' value
		JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		System.out.println(jsonObject.getBoolean("success"));
		return response.toString();
		}catch(Exception e){
                    System.out.println("exception aa gayi verifyRecaptcha m");
			e.printStackTrace();
                        System.out.println(e);
			return "";
		}
	}
}
