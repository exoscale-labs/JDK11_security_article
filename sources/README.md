JDK 11 TLS 1.3 server and client

First copy the sample.pfx file to the C: drive (so that you can specify it as a key/trust store for the applications).
The sample applications need to use JDK 11.

1. Run the secure TLS server (through the com.exoscale.tls.server.SecureTLSServer) class
2. Run the secure TLS client (through the com.exoscale.tls.client.TLSClient class)
3. Observe that the TLS 1.3 server and client exchange messages correctly in the standard output
4. Enable the SecureManager in the TLS 1.3 server (uncomment the secure manager to be installed in the main() method of the SecureTLSServer class)
5. Observe that you get a java.security.AccessControlException: access denied ("java.util.PropertyPermission" "javax.net.ssl.keyStore" "write") exception
6. Add the following entry to the security.policy file entry in your JDK installation (replace the path to the compiled class files accordingly):

grant codeBase "file:/C:/project/target/" {
		permission java.util.PropertyPermission "javax.net.ssl.keyStore", "write";
		permission java.util.PropertyPermission "javax.net.ssl.keyStorePassword", "write";
		permission java.net.SocketPermission "localhost:4444", "listen,resolve";
};

Note that in JDK 11 the path to the security.policy file is under the conf\security folder.

