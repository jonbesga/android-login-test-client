package com.example.jonander.brokenreality;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by JONANDER on 8/15/2015.
 */
public class LoginUser extends AsyncTask<ArrayList<String>, Void, String> {

    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(ArrayList<String>... passing) {
        ArrayList<String> passed = passing[0];
        String url = "/api/v1/login/";
        String data = "username=" + passed.get(0) + "&password=" + passed.get(1);
        String result = "";
        try {
            result = sendPost(url, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    protected void onPostExecute(String result) {

        delegate.processFinish(result);

    }
    private String sendPost(String url, String data) throws Exception {

        url = "https://android-login-django-jabesga.c9.io" + url;
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = data;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);


        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

            JSONObject json = new JSONObject(response.toString());
            MainActivity.authentication_token = json.getString("token");
            return "Login success";
        }
        else{
            return "Login failed";
        }
    }

}
