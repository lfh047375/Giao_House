package com.uploadGPS;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class HttpConnect {

	// Http连接对象
		/**
		 * 
		 * @param xmlInfo
		 *            传入接口请求boby(json字符串)
		 * @param URL
		 *            传入接口请求地址
		 * @return 返回请求结果(jason字符串)
		 * @throws IOException
		 */
		public String httpConnect(String xmlInfo, String URL) throws IOException {
			byte[] xmlData = xmlInfo.getBytes();
//			System.out.println(URL);
			URL url = new URL(URL);
			URLConnection urlCon = url.openConnection();
			/*
			 * 当我们要采用非get请求给一个http网络地址传参 就是使用connection.getOutputStream().write()
			 * 方法时我们就需要setDoOutput(true)
			 * 当我们要获取我们请求的http地址访问的数据时就是使用
			 * connection.getInputStream().read()方式时我们就需要setDoInput(true)，
			 * 根据api文档我们可知doInput默认就是为true。我们可以不用手动设置了
			 * ，如果不需要读取输入流的话那就setDoInput(false)。
			 */
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
			printout.write(xmlData); // 将Json协议内容写入输出流
			printout.flush();
			printout.close();
			urlCon.connect();
			// System.out.println("http.getResponseCode()"+((HttpURLConnection)
			// urlCon).getResponseCode());

			InputStream instr = urlCon.getInputStream();
			byte[] bis = IOUtils.toByteArray(instr);
			String responseString = new String(bis, "UTF-8");// 定义http请求的返回值
			System.out.println(responseString);
			return responseString;// Http返回值显示
		}
}
