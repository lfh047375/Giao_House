package com.uploadGPS;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class HttpConnect {

	// Http���Ӷ���
		/**
		 * 
		 * @param xmlInfo
		 *            ����ӿ�����boby(json�ַ���)
		 * @param URL
		 *            ����ӿ������ַ
		 * @return ����������(jason�ַ���)
		 * @throws IOException
		 */
		public String httpConnect(String xmlInfo, String URL) throws IOException {
			byte[] xmlData = xmlInfo.getBytes();
//			System.out.println(URL);
			URL url = new URL(URL);
			URLConnection urlCon = url.openConnection();
			/*
			 * ������Ҫ���÷�get�����һ��http�����ַ���� ����ʹ��connection.getOutputStream().write()
			 * ����ʱ���Ǿ���ҪsetDoOutput(true)
			 * ������Ҫ��ȡ���������http��ַ���ʵ�����ʱ����ʹ��
			 * connection.getInputStream().read()��ʽʱ���Ǿ���ҪsetDoInput(true)��
			 * ����api�ĵ����ǿ�֪doInputĬ�Ͼ���Ϊtrue�����ǿ��Բ����ֶ�������
			 * ���������Ҫ��ȡ�������Ļ��Ǿ�setDoInput(false)��
			 */
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
			printout.write(xmlData); // ��JsonЭ������д�������
			printout.flush();
			printout.close();
			urlCon.connect();
			// System.out.println("http.getResponseCode()"+((HttpURLConnection)
			// urlCon).getResponseCode());

			InputStream instr = urlCon.getInputStream();
			byte[] bis = IOUtils.toByteArray(instr);
			String responseString = new String(bis, "UTF-8");// ����http����ķ���ֵ
			System.out.println(responseString);
			return responseString;// Http����ֵ��ʾ
		}
}
