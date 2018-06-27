package com.ycjcjy.gene.web.action.system.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpRequest {

	/**
	 * GET请求当时
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	public static JSONObject sendGetRequest(String url, String authorization) {

		JSONObject ret = null;
		HttpURLConnection connection = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestMethod("GET");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "application/json");
			if (StringUtils.isNotBlank(authorization)) {
				connection.setRequestProperty("Authorization", authorization);
			}
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setConnectTimeout(10 * 1000);
			// 建立实际的连接
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ret = JSONObject.parseObject(getInputStream(connection.getInputStream()));
			} else {
				ret = JSONObject.parseObject(getInputStream(connection.getErrorStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return ret;
	}

	/**
	 * GET请求当时
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	public static JSONObject sendPostRequest(String url, String authorization) {

		JSONObject ret = null;
		HttpURLConnection connection = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestMethod("POST");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "application/json");
			if (StringUtils.isNotBlank(authorization)) {
				connection.setRequestProperty("Authorization", authorization);
			}
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setConnectTimeout(10 * 1000);
			// 建立实际的连接
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ret = JSONObject.parseObject(getInputStream(connection.getInputStream()));
			} else {
				ret = JSONObject.parseObject(getInputStream(connection.getErrorStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return ret;
	}

	/**
	 * POST请求方式 application/json;charset=utf-8
	 * 
	 * @param url
	 *            请求地址
	 * @param param
	 *            提交参数，参数格式为json格式
	 * @return
	 */
	public static JSONObject postJsonRequest(String url, String param, String authorization) {
		return post(url, param, "application/json;charset=utf-8", authorization);
	}

	public static String postJsonRequestAsString(String url, String param, String authorization) {
		return postAsString(url, param, "application/json;charset=utf-8", authorization);
	}

	/**
	 * POST请求方式 x-www-form-urlencoded
	 * 
	 * @param url
	 *            请求地址
	 * @param param
	 *            提交参数，参数格式为json格式
	 * @return
	 */
	public static JSONObject postRequest(String url, String param, String authorization) {
		return post(url, param, "application/x-www-form-urlencoded", authorization);
	}

	/**
	 * POST请求方式
	 * 
	 * @param url
	 *            请求地址
	 * @param param
	 *            提交参数，参数格式为json格式
	 * @return
	 */
	public static JSONObject post(String url, String param, String contentType, String authorization) {

		JSONObject ret = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);
			if (StringUtils.isNotBlank(authorization)) {
				connection.setRequestProperty("Authorization", authorization);
			}
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setConnectTimeout(10 * 1000);
			if (param != null) {
				connection.setDoOutput(true);
				// 建立实际的连接
				connection.connect();
				out = new DataOutputStream(connection.getOutputStream());
				out.write(param.getBytes("utf-8"));
				out.flush();
				out.close();
			}
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				ret = JSONObject.parseObject(getInputStream(connection.getInputStream()));
			} else {
				ret = JSONObject.parseObject(getInputStream(connection.getErrorStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}

	public static String postAsString(String url, String param, String contentType, String authorization) {

		String ret = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);
			if (StringUtils.isNotBlank(authorization)) {
				connection.setRequestProperty("Authorization", authorization);
			}
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setConnectTimeout(10 * 1000);
			if (param != null) {
				connection.setDoOutput(true);
				// 建立实际的连接
				connection.connect();
				out = new DataOutputStream(connection.getOutputStream());
				out.write(param.getBytes("utf-8"));
				out.flush();
				out.close();
			}
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				ret = getInputStream(connection.getInputStream());
			} else {
				ret = getInputStream(connection.getErrorStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * POST请求方式
	 * 
	 * @param url
	 *            请求地址
	 * @param param
	 *            提交参数，参数格式为json格式
	 * @return
	 */
	public static JSONObject postAsString(String url, String param, String authorization) {
		String ParamName = param.substring(0, param.indexOf("=") + 1);
		String ParamValue = param.substring(param.indexOf("=") + 1);
		JSONObject ret = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		try {
			param = ParamName + URLEncoder.encode(ParamValue, "UTF-8");
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			connection.setRequestProperty("Charset", "UTF-8");
			if (StringUtils.isNotBlank(authorization)) {
				connection.setRequestProperty("Authorization", authorization);
			}
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setConnectTimeout(10 * 1000);
			if (param != null) {
				connection.setDoOutput(true);
				// 建立实际的连接
				connection.connect();
				out = new DataOutputStream(connection.getOutputStream());
				out.writeBytes(param);
				out.flush();
				out.close();
			}
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				ret = JSONObject.parseObject(getInputStream(connection.getInputStream()));
			} else {
				ret = JSONObject.parseObject(getInputStream(connection.getErrorStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}

	private static String getInputStream(InputStream stream) {
		if (stream == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		String line;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}



}
