package com.wlswnwns.chosung_android.utils;


import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by user on 2017-12-31.
 */
public class APiAsyc extends AsyncTask<Void, Void, String> {

    private String url = "https://1686tyz2d2.execute-api.ap-northeast-2.amazonaws.com/dev/", routes, method;
    private ContentValues values;
    private JSONObject jsonobject;
    HttpURLConnection con;

    public int getByteLength(String str) {


        int strLength = 0;

        char tempChar[] = new char[str.length()];


        for (int i = 0; i < tempChar.length; i++) {

            tempChar[i] = str.charAt(i);


            if (tempChar[i] < 128) {

                strLength++;

            } else {

                strLength += 2;

            }

        }


        return strLength;

    }

    public APiAsyc(String routes, String method, JSONObject jsonobject) {

        this.routes = routes;
        this.method = method;
        this.jsonobject = jsonobject;
    }

    @Override
    protected String doInBackground(Void... params) {
        url = url + routes;
        URL url = null;//url을 가져온다.
        con = null;

        switch (method) {
            case "GET":
                try {
                    url = new URL(this.url);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Accept", "text/html");
                    if (this.jsonobject != null) {
                        Iterator keys = this.jsonobject.keys();
                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            con.setRequestProperty(key, this.jsonobject.getString(key));
                            Log.d("key", key);
                        }
                    }
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    url = new URL(this.url);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(this.method);//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect(); //서버로 보내기위해서 스트림 만듬
                    if (this.jsonobject != null) {

                        String body = jsonobject.toString().trim();
                        Log.e("요청값==>",body);

                        OutputStream outStream = con.getOutputStream();

                        //버퍼를 생성하고 넣음
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                        try {
                            writer.write(body);
                        } catch (Exception e) {
                        }

                        writer.flush();
                        writer.close();//버퍼를 받아줌
                        outStream.close();
                    }
                    //return "";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        StringBuffer buffer = null;
        InputStream stream = null;
        try {

            int status = con.getResponseCode();
            Log.e("응답코드==>",""+status);


            stream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            con.disconnect();
            return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임
        } catch (IOException e) {
            e.printStackTrace();
            con.disconnect();
            return null;
        }
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

    public void disconnectTask() {
        con.disconnect();
    }
}