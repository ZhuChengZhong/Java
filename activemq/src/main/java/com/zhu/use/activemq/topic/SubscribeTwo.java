package com.zhu.use.activemq.topic;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SubscribeTwo {
	public static void main(String[] args) throws Exception {
		String user=ActiveMQConnection.DEFAULT_USER;
		String password=ActiveMQConnection.DEFAULT_PASSWORD;
		String url=ActiveMQConnection.DEFAULT_BROKER_URL;
		String subject="Tool.DEFAULT";
		ConnectionFactory factory=new ActiveMQConnectionFactory(user,password,url);
		Connection connection=factory.createConnection();
		connection.start();
		Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic topic=session.createTopic(subject);
		MessageConsumer consumer=session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				try {
					MapMessage mapMessage=(MapMessage)message;
					long id=mapMessage.getLong("id");
					System.out.println("SubscribeTwo receive id:"+id);
				} catch (JMSException e) {
				}
				
			}
		});
		TimeUnit.SECONDS.sleep(15);
		System.out.println("OVER !!");
		session.close();
		connection.close();
	}
}
