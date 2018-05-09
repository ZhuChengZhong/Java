package zhu.usehttpclient.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GrabPictures {
  public static void main(String[] args) throws ClientProtocolException, IOException {
	CloseableHttpClient httpClient=HttpClients.createDefault();
	HttpGet get=new HttpGet("https://gma.alicdn.com/bao/uploaded/i1/23454997/TB2kTpKeGmgSKJjSspiXXXyJFXa_!!0-saturn_solar.jpg_210x210.jpg");
	CloseableHttpResponse response=httpClient.execute(get);
	HttpEntity httpEntity=response.getEntity();
	System.out.println(httpEntity.getContentType().getValue());
	InputStream inputStream=httpEntity.getContent();
	File targetFile=new File("f://downloadpage/a.jpg"); 
	OutputStream outputStream=new FileOutputStream(targetFile);
	byte[] b=new byte[1024];
	int len;
	while((len=inputStream.read(b))!=-1){
		outputStream.write(b, 0, len);
	}
	inputStream.close();
	outputStream.close();
  }
}
