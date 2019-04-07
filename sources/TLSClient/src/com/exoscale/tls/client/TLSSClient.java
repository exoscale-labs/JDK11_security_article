package com.exoscale.tls.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TLSSClient {

	public static void main(String[] args) {
		
		try {

			System.setProperty("javax.net.ssl.trustStore", "C:/sample.pfx");
			System.setProperty("javax.net.ssl.trustStorePassword", "sample");
			
			SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket s = (SSLSocket) ssf.createSocket("127.0.0.1", 4444);
			s.setEnabledProtocols(new String[] {"TLSv1.3"});
		    	s.setEnabledCipherSuites(new String[] {"TLS_AES_128_GCM_SHA256"});
		    
			SSLParameters params = s.getSSLParameters();
			s.setSSLParameters(params);
			
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("Hi, server.");
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String x = in.readLine();
			System.out.println(x);
			
			out.close();
			in.close();
			s.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		
	}
	
}
