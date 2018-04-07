package com.zhu.use.activemq;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 * @author zhu
 *
 */
public class Producer {
	public static void main(String[] args) throws JMSException, InterruptedException {
		Connection connection=null;
		Session session=null;
		try {
			String user=ActiveMQConnection.DEFAULT_USER;
			String password=ActiveMQConnection.DEFAULT_PASSWORD;
			String url=ActiveMQConnection.DEFAULT_BROKER_URL;
			String subject="Tool.DEFAULT";
			ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(user, password, url);
			connection=factory.createConnection();
			connection.start();
			session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
			Destination destination=session.createQueue(subject);
			MessageProducer producer=session.createProducer(destination);
			for(int i=0;i<10;i++) {
				MapMessage message=session.createMapMessage();
				message.setLong("id", i);
				System.out.println("begin send id:"+i);
				producer.send(message);
				TimeUnit.SECONDS.sleep(1);
			}
		}catch(JMSException e) {
			e.printStackTrace();
		}finally {
			session.commit();
			session.close();
			connection.close();
		}
		
	}
}
