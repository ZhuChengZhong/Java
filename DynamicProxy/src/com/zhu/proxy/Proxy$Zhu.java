package com.zhu.proxy;
import java.lang.reflect.Method;
public class Proxy$Zhu implements com.zhu.proxy.Service{
InvocationHandler h ;
public Proxy$Zhu(InvocationHandler h) {
this.h=h ;
}
public void method()throws Throwable{
Method m=com.zhu.proxy.Service.class.getDeclaredMethod("method");
this.h.invoke(this,m,(Object[])null);
}
}
