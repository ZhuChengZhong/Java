package com.zhu.compress.hfm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class HuffmanTree  {
	//结束标记
	public static final byte END=(byte)0XFF;
	//无用的空节点标记
	public static final byte EMPTY_NODE_VAL=(byte)0XFE;
	//NULL
	public static final byte NULL_NODE_VAL=(byte)0XFD;
	//分隔标记
	public static final byte SPLIT=(byte)0XFC;
	
	static class TreeNode implements Comparable<TreeNode>{
		TreeNode left;
		TreeNode right;
		byte val;
		int count;
		public TreeNode(TreeNode left, TreeNode right, byte val, int count) {
			super();
			this.left = left;
			this.right = right;
			this.val = val;
			this.count = count;
		}
		@Override
		public int compareTo(TreeNode o) {
			if(this.count<o.count) {
				return -1;
			}else if(this.count>o.count) {
				return 1;
			}
			return 0;
		}
	}
	TreeNode root;
	
	
	public HuffmanTree() {
		super();
	}

	/**
	 * 通过优先级队列构建霍夫曼树
	 * @param queue
	 */
	public HuffmanTree(PriorityQueue<TreeNode> queue) {
		while(queue.size()>1) {
			TreeNode first=queue.poll();
			TreeNode second=queue.poll();
			TreeNode newNode=new TreeNode(first,second,EMPTY_NODE_VAL,first.count+second.count);
			queue.add(newNode);
		}
		if(queue.size()==1) {
			root=queue.poll();
		}
	}
	
	/**
	 * 通过先序遍历序列化哈夫曼树
	 * @return
	 */
	public byte[] serialize() {
		List<Byte>list=new LinkedList<>();
		Stack<TreeNode>stack=new Stack<>();
		if(root==null) {
			return null;
		}
		stack.add(root);
		while(!stack.isEmpty()) {
			TreeNode node=stack.pop();
			if(node==null) {
				list.add(NULL_NODE_VAL);
			}else {
				list.add(node.val);
				stack.push(node.right);
				stack.push(node.left);
			}
		}
		byte[]res=new byte[list.size()+1];
		for(int i=0;i<list.size();i++) {
			res[i]=list.get(i);
		}
		res[list.size()]=SPLIT;
		return res;
	}
	
	/**
	 * 通过先序遍历重建哈夫曼树
	 * @param bs
	 */
	public void deserialize(byte[] bs) {
		if(bs==null||bs.length<1) {
			return ;
		}
		root=build(bs, new int[1]);
	}
	private TreeNode build(byte[]bs,int[] index) {
		byte val=bs[index[0]];
		if(val==NULL_NODE_VAL) {
			return null;
		}
		index[0]++;
		TreeNode left=build(bs,index);
		index[0]++;
		TreeNode right=build(bs,index);
		TreeNode root=new TreeNode(left, right, val,0);
		return root;
	}
	
	/**
	 * 判断树节点是否为叶子节点
	 * @param node
	 * @return
	 */
	public boolean isLeaf(TreeNode node) {
		if(node!=null&&node.val!=EMPTY_NODE_VAL&&node.val!=NULL_NODE_VAL) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		TreeNode node1=new TreeNode(null,null,(byte)'a',1);
		TreeNode node2=new TreeNode(null,null,(byte)'b',2);
		TreeNode node3=new TreeNode(null,null,(byte)'c',3);
		TreeNode node4=new TreeNode(null,null,(byte)'d',4);
		PriorityQueue<TreeNode>queue=new PriorityQueue<>();
		queue.add(node4);
		queue.add(node3);
		queue.add(node2);
		queue.add(node1);
		HuffmanTree tree=new HuffmanTree(queue);
		byte[]bs=tree.serialize();
		System.out.println(Arrays.toString(bs));
		HuffmanTree newTree=new HuffmanTree();
		newTree.deserialize(bs);
		System.out.println(Arrays.toString(newTree.serialize()));
	}
}
