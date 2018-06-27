package com.ycjcjy.gene.web.action.system.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 简单http请求工具类
 * 
 * @author yiqifendou
 * 
 */
@SuppressWarnings("all")
public class HttpUtils {

	/**
	 * 通过流传递数据
	 * Description: <br>
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException 
	 * @see
	 */
    public static String requestByStream(String url,String data) throws IOException{
        HttpURLConnection httpConn = null;
        InputStream is = null;
        try
        {
            httpConn = (HttpURLConnection)new URL(url).openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            OutputStream out = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            osw.write(data);
            osw.flush();
            osw.close();
            out.close();
            is = httpConn.getInputStream();
            return InputStreamUtils.InputStreamTOString(is);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            if(is!=null){
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getHtmlFromURL(String urlString) {
        try {
            StringBuffer html = new StringBuffer();
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));

            String temp;
            while ((temp = br.readLine()) != null) {
                html.append(temp).append("\n");
            }
            br.close();
            is.close();
            return html.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}



