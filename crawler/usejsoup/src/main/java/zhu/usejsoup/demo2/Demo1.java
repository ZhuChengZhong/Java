package zhu.usejsoup.demo2;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo1 {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		   CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpGet get=new HttpGet("http://www.zhuchengzhong.cc");
			get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/57.0");
			CloseableHttpResponse response=httpClient.execute(get);
			HttpEntity entity=response.getEntity();
			ContentType contentType=ContentType.getOrDefault(entity);
			Charset charset=contentType.getCharset();
			System.out.println(contentType.getMimeType());
			System.out.println(charset);
			byte[] b=EntityUtils.toByteArray(entity);
			String html=new String(b,charset);
			
			Document doc=Jsoup.parse(html);
			Elements elements=doc.select(".blogs h3 a");
			for(Element e:elements){
				System.out.println("博客标题为："+e.text());
			}
			System.out.println("=============================");
			Elements imgElements=doc.select(".blogs figure img[src$=.png]");//获取以.png结尾的IMG标签
			for(Element e:imgElements){
				System.out.println(e.toString());
				System.out.println(e.attr("width"));
			}
			System.out.println("=============================");
	  }
}
