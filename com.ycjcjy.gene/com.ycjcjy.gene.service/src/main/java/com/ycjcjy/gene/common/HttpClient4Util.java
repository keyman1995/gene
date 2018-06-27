package com.ycjcjy.gene.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/***
 * HTTP请求.
 */
public final class HttpClient4Util {
    /**
     * 注释内容
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient4Util.class);

    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 100000;

    /**
     * 读取超时时间
     */
    public static final int SO_TIMEOUT = 300000;

    private HttpClient4Util() {
    }

    /**
     * <HttpClient直接连接接口> <功能详细描述>
     *
     * @param url      接口URL
     * @param params   NameValuePair参数
     * @param encoding 编码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getResponseAsString(String url, Map<String, Object> params, String encoding) {
        return post(url, params, encoding);
    }

    /**
     * <HttpClient直接连接接口> <功能详细描述>
     *
     * @param url
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
//    public static Map<String, Object> getResponseAsMap(String url, Map<String, Object> params, String encoding) {
//        String response = post(url, params, encoding);
//        if (!StringUtils.isEmpty(response)) {
//            return JsonUtil.json2Map(response);
//        }
//        return null;
//    }

    /**
     * <HttpClient直接连接接口> <功能详细描述>
     *
     * @param url
     * @param encoding
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String getResponseAsMap4Get(String url, String encoding) {
        return get(url, encoding);
    }

    /**
     * HttpClient直接连接接口，直接返回数据
     *
     * @param url
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     */
    private static String post(String url, Map<String, Object> params, String encoding, HttpClient httpClient) {
        LOGGER.info("执行Http Post请求,地址: " + url + ",参数: " + params);

        String response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>(params.size());
            if (!params.isEmpty()) {
                Set<Entry<String, Object>> entrySet = params.entrySet();
                for (Entry<String, Object> entry : entrySet) {
                    nvps.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
                }
            }
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Accept-Language", "zh-cn");
            // 及时释放连接，不缓存连接(防止close_wait)
            httpPost.addHeader("Connection", "close");

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

            httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
            httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                response = EntityUtils.toString(httpEntity, encoding).replaceAll("\r\n", "");
                EntityUtils.consume(httpEntity);
            }

            httpPost.abort();
        } catch (Exception e) {
            LOGGER.error("执行Http Post请求失败! Exception: " + e.getMessage(), e);
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        LOGGER.info("Http Post执行后响应内容： " + response);
        return response;
    }

    /**
     * HttpClient直接连接接口，直接返回数据
     *
     * @param url
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     */
    private static String post(String url, Map<String, Object> params, String encoding) {
        return post(url, params, encoding, new DefaultHttpClient());
    }


    /**
     * HttpClient直接连接接口，直接返回数据
     *
     * @param url
     * @param encoding
     * @return
     * @throws Exception
     */
    private static String get(String url, String encoding) {
        LOGGER.info("执行Http get请求,地址: " + url);

        String response = null;
        HttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("Accept-Language", "zh-cn");
            // 及时释放连接，不缓存连接(防止close_wait)
            httpGet.addHeader("Connection", "close");

            httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
            httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                response = EntityUtils.toString(httpEntity, encoding).replaceAll("\r\n", "");
                EntityUtils.consume(httpEntity);
            }

            httpGet.abort();
        } catch (Exception e) {
            LOGGER.error("执行Http GET请求失败! Exception: " + e.getMessage(), e);
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        LOGGER.info("Http GET执行后响应内容： " + response);
        return response;
    }

    /**
     * Get请求
     *
     * @return
     * @author of1245
     * @date 2016-2-19 下午 3:34:03
     */
    public static JSONObject requestDataByGet(String url, String encoding) {
        StringBuffer sb = new StringBuffer();
        URL u = null;
        BufferedReader br = null;
        try {
            u = new URL(url);
            URLConnection connection = u.openConnection();
            connection.setConnectTimeout(5000);
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            String ret = br.readLine();
            while (ret != null) {
                sb.append(ret);
                ret = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONObject.parseObject(sb.toString());
    }

    /**
     * Post请求
     *
     * @return
     * @author of1245
     * @date 2016-2-19 下午 3:34:03
     */
    public static JSONObject requestDataByPost(String url, JSONObject param, String encoding) {
        JSONObject header = new JSONObject();
        header.put("Content-Type", "application/json;charset=utf-8");
        return requestDataByPost(url, param, encoding, header);
    }

    /**
     * Post请求
     *
     * @return
     * @author of1245
     * @date 2016-2-19 下午 3:34:03
     */
    public static JSONObject requestDataByPost(String url, JSONObject param, String encoding, JSONObject header) {
        StringBuffer sb = new StringBuffer();
        URL u = null;
        BufferedReader br = null;
        try {
            u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            //设置请求参数Content-Type:application/json
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.getString(key));
            }
            //实体参数的类型//允许对外输出 json参数
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(param.toJSONString().getBytes());
            os.flush();
            os.close();

            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            String ret = br.readLine();
            while (ret != null) {
                sb.append(ret);
                ret = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONObject.parseObject(sb.toString());
    }

    public static JSONObject requestData(HttpClient httpClient, String url, Map<String, Object> params, String type) {
        JSONObject jsonResult = new JSONObject();
        if ("get".equalsIgnoreCase(type)) {
            HttpRequestBase method = new HttpGet(url);
            try {
                HttpResponse result = httpClient.execute(method);
                if (result.getStatusLine().getStatusCode() == 200) {
                    try {
                        String str = EntityUtils.toString(result.getEntity());
                        jsonResult = JSONObject.parseObject(str);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        } else {
            return JSONObject.parseObject(post(url, params, "UTF-8", httpClient));
        }
    }


    public static void main(String[] args) {
        //demo:代理访问
        String url = "http://api.map.baidu.com/place/v2/search?query=南京楚翘城&tag=地铁&region=南京&output=json&ak=DE5f1796d87e020918d8ef08db0b4986";


        String sr=HttpClient4Util.get(url,"UTF-8");
        System.out.println(sr);
    }

}
