package com.zhu.usehtmlclient;

import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * 获取指定元素+使用代理ip
 * @author Zhu
 *
 */
public class Demo2 {
   public static void main(String[] args)throws Exception {
	 WebClient client=new WebClient(BrowserVersion.FIREFOX_52,"113.110.208.95",9797);
	 HtmlPage page=client.getPage("http://www.zhuchengzhong.cc");
	 DomNodeList<DomElement> list=page.getElementsByTagName("img");
	 for(DomElement element:list){
		 System.out.println(element.asXml());
	 }
	 
	 
	 List<DomElement>domList=page.getByXPath("//nav/ul/a");
	 for(DomElement element:domList){
		 System.out.println(element.asXml());
	 }
   }
}
