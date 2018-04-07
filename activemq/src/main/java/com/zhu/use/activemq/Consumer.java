package com.zhu.use.activemq;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	public static void main(String[] args) throws JMSException, InterruptedException {
		String user=ActiveMQConnection.DEFAULT_USER;
		String password=ActiveMQConnection.DEFAULT_PASSWORD;
		String url=ActiveMQConnection.DEFAULT_BROKER_URL;
		String subject="Tool.DEFAULT";
		ConnectionFactory factory=new ActiveMQConnectionFactory(user,password,url);
		Connection connection=factory.createConnection();
		connection.start();
		Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination=session.createQueue(subject);
		MessageConsumer consumer=session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				try {
					MapMessage mapMessage=(MapMessage)message;
					long id=mapMessage.getLong("id");
					System.out.println("consumer receive id:"+id);
				} catch (JMSException e) {
				}
				
			}
		});
		TimeUnit.SECONDS.sleep(15);
		System.out.println("OVER !!");
		session.commit();
		session.close();
		connection.close();
	}
}
