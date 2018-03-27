package com.zhu.pool;

import java.sql.Connection;

public class Main {
	public static void main(String[] args) {
		ConnectionBlockingPool pool = new ConnectionBlockingPool(1, new ConnectionValidator(),
				new ConnectionFactory("jdbc:mysql://localhost:3306/hhoj", "root", "121314", "com.mysql.jdbc.Driver"));
		Connection con=pool.get();
	}
}
