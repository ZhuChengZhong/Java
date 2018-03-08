package study.mybatis.entity;

public class Student {
	private Integer id;
	private String  name;
	private int age;
	/**
	 * 测试StringToIntTypeHandler
	 *
	 */
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", number=" + number + "]";
	}

	
}
