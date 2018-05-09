package zhu.usehttpclient.useproxy;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class UseProxy {
  public static void main(String[] args) throws ClientProtocolException, IOException {
	CloseableHttpClient httpClient=HttpClients.createDefault();
	HttpGet get=new HttpGet("http://www.tuicool.com");
	RequestConfig config;
	HttpHost proxy=new HttpHost("61.135.217.7", 80);
	config=RequestConfig.custom().setProxy(proxy).setSocketTimeout(10000).build();
	get.setConfig(config);
	get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/57.0");
	CloseableHttpResponse response=httpClient.execute(get);
	HttpEntity entity=response.getEntity();
	byte[] b=EntityUtils.toByteArray(entity);
	String s=new String(b);
	System.out.println(s);
  }
}
