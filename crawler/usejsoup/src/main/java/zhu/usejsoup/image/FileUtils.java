package zhu.usejsoup.image;

import java.io.FileOutputStream;
import java.io.InputStream;

public final class FileUtils {
  public static boolean StreamToFile(InputStream input,String fileName){
	  boolean result=false;
	  try{
		  if(fileName!=null&&!"".equals(fileName)&&input!=null){
			  FileOutputStream out=new FileOutputStream(fileName);
			  byte[] b=new byte[1024];
			  int len;
			  while((len=input.read(b))!=-1){
				  out.write(b,0,len);
			  }
			  input.close();
			  out.close();
			  result=true;
		  }  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return result;
  }
  public static boolean StreamToFile(byte[] imageBytes,String fileName){
	  boolean result=false;
	  try{
		  if(fileName!=null&&!"".equals(fileName)&&imageBytes!=null){
			  FileOutputStream out=new FileOutputStream(fileName);
			  out.write(imageBytes);
			  out.close();
			  result=true;
		  }  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return result;
  }
}
