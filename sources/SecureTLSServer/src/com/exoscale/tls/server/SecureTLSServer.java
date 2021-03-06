package com.exoscale.tls.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SecureTLSServer {

	public static void main(String[] args) throws IOException {
		
//		System.setSecurityManager(new SecurityManager());
		
		System.setProperty("javax.net.ssl.keyStore", "C:/sample.pfx");
		System.setProperty("javax.net.ssl.keyStorePassword", "sample");

		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(4444);
		ss.setEnabledProtocols(new String[] { "TLSv1.3" });
		ss.setEnabledCipherSuites(new String[] { "TLS_AES_128_GCM_SHA256" });

		while (true) {
			SSLSocket s = (SSLSocket) ss.accept();
			SSLParameters params = s.getSSLParameters();
			s.setSSLParameters(params);

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line = null;
			PrintStream out = new PrintStream(s.getOutputStream());
			while (((line = in.readLine()) != null)) {
				System.out.println(line);
				out.println("Hi, client");
			}
			in.close();
			out.close();
			s.close();
		}

	}

}
