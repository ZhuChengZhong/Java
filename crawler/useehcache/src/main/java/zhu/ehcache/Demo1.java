package zhu.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Demo1 {
  public static void main(String[] args) {
	 //通过配置文件实例化缓存管理器
	CacheManager manager=CacheManager.create("src/main/resources/ehcache.xml");
	//获取缓存容器
	Cache cache=manager.getCache("z");
	//实例化一个Element
	Element e=new Element("url", "www.zhuchengzhong.cc");
	Element e2=new Element("url2", "www.zhuchengzhong.cc2");
	//将元素添加至缓存中
	cache.put(e);
	cache.put(e2);
	//通过key获取缓存中的Element
	Element e1=cache.get("url");
	System.out.println(e1);
	e1=cache.get("url2");
	System.out.println(e1);
	cache.flush();
	//关闭
	manager.shutdown();
 }
}
