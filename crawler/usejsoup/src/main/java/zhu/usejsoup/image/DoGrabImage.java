package zhu.usejsoup.image;

import java.io.InputStream;
import java.util.Set;

public class DoGrabImage {
 public static void main(String[] args) {
   Set<String>urlSet=GrabImageUtil.getImageUrlFromUrl("http://www.zhuchengzhong.cc");
   int k=1;
   for(String imageUrl:urlSet){
	   byte[] imageBytes=GrabImageUtil.getImageFromUrl("http://www.zhuchengzhong.cc"+imageUrl);
	   FileUtils.StreamToFile(imageBytes, "f://downloadimage/"+k+++".png");
   }
 }
}
