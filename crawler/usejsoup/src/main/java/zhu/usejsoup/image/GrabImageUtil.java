package zhu.usejsoup.image;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
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

public class GrabImageUtil {
	public static String getHtmlFromUrl(String url){
		String html=null;
		try{
			if(url!=null&&!"".equals(url)){
				CloseableHttpClient httpClient=HttpClients.createDefault();
				HttpGet get=new HttpGet(url);
				CloseableHttpResponse response=httpClient.execute(get);
				HttpEntity entity=response.getEntity();
				ContentType contentType=ContentType.get(entity);
				Charset charset=contentType.getCharset();
				html=EntityUtils.toString(entity,charset);
				response.close();
				httpClient.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return html;
	}
	
   public static Set<String> getImageUrlFromHtml(String html){
	   
		   Set<String>urlSet=new HashSet<String>();
		   Document doc=Jsoup.parse(html);
		   Elements elements=doc.select("img[src$=.png]");
		   for(Element e:elements){
			   String imageUrl=e.attr("src");
			   urlSet.add(imageUrl);
		   }
		   return urlSet;
   }
   
   public static Set<String> getImageUrlFromUrl(String url){
		String html=getHtmlFromUrl(url);
		return getImageUrlFromHtml(html);
		
	}
   
   public static byte[] getImageFromUrl(String imageUrl){
	   byte[] imageBytes=null;
	   try{
			if(imageUrl!=null&&!"".equals(imageUrl)){
				CloseableHttpClient httpClient=HttpClients.createDefault();
				HttpGet get=new HttpGet(imageUrl);
				CloseableHttpResponse response=httpClient.execute(get);
				HttpEntity entity=response.getEntity();
				ContentType contentType=ContentType.get(entity);
				String type=contentType.getMimeType();
				if(type==null||!type.startsWith("image")){
					return null;
				}
				imageBytes=EntityUtils.toByteArray(entity);
				response.close();
				httpClient.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	   return imageBytes;
   }
   public static List<byte[]> getImageFromUrl(Collection<String>imageUrl){
	   List<byte[]>list=new ArrayList<byte[]>();
	   if(imageUrl!=null){
		   for(String url:imageUrl){
			   byte[] imageBytes=getImageFromUrl(url);
			   if(imageBytes!=null){
				   list.add(imageBytes);
			   }
		   }
	   }
	   return list;
   }
}
