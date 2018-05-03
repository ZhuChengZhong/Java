package com.zhu.test;

import java.util.Stack;

import com.zhu.test.FindMaxBST.TreeNode;

public class TreeBuilder {
	
	/**
	 * 反序列化
	 * @param s
	 * @return
	 */
	public static TreeNode build(String s) {
		if(s==null) {
			return null;
		}
		String []vals=s.split("!");
		Stack<String> stack=new Stack<>();
		for(int i=vals.length-1;i>=0;i--) {
			stack.push(vals[i]);
		}
		return build(stack);
	}
	private static TreeNode build(Stack<String>stack) {
		String val=stack.pop();
		if("#".equals(val)) {
			return null;
		}
		TreeNode root=new TreeNode(new Integer(val));
		root.left=build(stack);
		root.right=build(stack);
		return root;
	}
	
	/**
	 * 序列化
	 * @param root
	 * @return
	 */
	public static String serialize(TreeNode root) {
		if(root==null) {
			return "";
		}
		StringBuilder s=new StringBuilder();
		serialize(root,s);
		return s.toString();
	}
	private static void serialize(TreeNode root,StringBuilder s) {
		if(root==null) {
			s.append("#!");
			return ;
		}
		s.append(root.val+"!");
		serialize(root.left, s);
		serialize(root.right, s);
	}
}
