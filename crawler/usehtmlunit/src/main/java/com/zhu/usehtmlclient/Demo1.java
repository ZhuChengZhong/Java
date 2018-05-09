package com.zhu.usehtmlclient;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Demo1 {
	public static void main(String[] args)throws Exception {
		WebClient webClient=new WebClient();
		HtmlPage page=webClient.getPage("http://www.zhuchengzhong.cc");
		System.out.println(page.asXml());
		System.out.println("==========================================");
		System.out.println(page.asText());
	}
}
