package com.mrinalraj.instaprofilephoto.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mrinal on 21-10-2016.
 */

public class JSONCustom {

    public JSONObject getJSONObjectFromURL(String urlString, String METHOD) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod(METHOD);
        if (METHOD=="GET"){
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
        }
        else if (METHOD=="POST"){
            OutputStreamWriter writer=new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write(4);
            writer.flush();
        }


        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        String jsonString;

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        return new JSONObject(jsonString);
    }
}
