package com.cx.chenxing.utils.zj;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class MyX509TrustManager implements X509TrustManager {

		public boolean isClientTrusted(X509Certificate[] arg0) {
		// TODO Auto-generated method stub
		return true;
		}

		public boolean isServerTrusted(X509Certificate[] arg0) {
		// TODO Auto-generated method stub
		return true;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
}
