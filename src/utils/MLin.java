package utils;

import java.lang.reflect.Method;
import java.util.LinkedList;

class Pe{
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}

public class MLin {
	public static void main(String[] args) throws Exception{
		LinkedList<String> l = new LinkedList<String>();
		l.push("11");
		l.push("22");
		l.push("33");
		
		System.out.println("链表的长度值为 "+l.size());

		System.out.println("弹出数值  ");
		
		System.out.println(l.pop());
		System.out.println(l.pop());
		System.out.println(l.pop());
		
		Class class1 = Class.forName("utils.Pe");
		Pe pe = (Pe)class1.newInstance();
		pe.setAge(12);
		Method m = class1.getMethod("getAge");
		Object res = m.invoke(pe);
		System.out.println("调用方法得到的结果值为   "+res);
		
		Method method = class1.getMethod("setName",String.class);
		Object r = method.invoke(pe, "张三");
		System.out.println("反射得到的结果值为  "+r);
		System.out.println("name 值为  "+pe.getName());
//		class1.getDeclaredMethods();
	}
}
