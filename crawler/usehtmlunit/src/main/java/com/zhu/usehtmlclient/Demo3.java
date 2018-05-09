package com.zhu.usehtmlclient;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
/**
 * 禁用css 和javascript
 * 提交表单
 * @author Zhu
 *
 */
public class Demo3 {
	public static void main(String[] args) throws Exception{
		WebClient webClient=new WebClient();
		//禁用css 和javascript
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page=webClient.getPage("http://www.zhuchengzhong.cc/login/login.jsp");
		System.out.println(page.asXml());
		List<DomElement> formList=page.getElementsByTagName("form");
		HtmlForm formElement=(HtmlForm) formList.get(0);
		List<HtmlElement> list=formElement.getElementsByTagName("input");
		HtmlTextInput userNameInput=(HtmlTextInput)list.get(0);
		HtmlPasswordInput passwordInput=(HtmlPasswordInput)list.get(1);
		userNameInput.setValueAttribute("Mr.zhu");
		passwordInput.setValueAttribute("199614");
		HtmlButton button=(HtmlButton)formElement.getElementsByTagName("button").get(0);
		HtmlPage resultPage=button.click();
		 System.out.println(resultPage.asXml());
	}
}
