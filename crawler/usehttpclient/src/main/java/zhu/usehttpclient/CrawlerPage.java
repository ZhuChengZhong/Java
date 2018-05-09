package zhu.usehttpclient;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerPage {
    public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet get=new HttpGet("http://www.tuicool.com");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/57.0");
		CloseableHttpResponse response=httpClient.execute(get);
		HttpEntity entity=response.getEntity();
		ContentType contentType=ContentType.getOrDefault(entity);
		Charset charset=contentType.getCharset();
		System.out.println(contentType.getMimeType());
		System.out.println(charset);
		byte[] b=EntityUtils.toByteArray(entity);
		String html=new String(b,charset);
		System.out.println(html);
		response.close();
		httpClient.close();
	}
}
