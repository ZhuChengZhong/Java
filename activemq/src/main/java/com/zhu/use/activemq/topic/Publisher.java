package com.zhu.use.activemq.topic;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {
	public static void main(String[] args) throws Exception {
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
			Topic topic=session.createTopic(subject);
			MessageProducer producer=session.createProducer(topic);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			for(int i=0;i<10;i++) {
				MapMessage message=session.createMapMessage();
				message.setLong("id", i);
				System.out.println("publisher send id:"+i);
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
