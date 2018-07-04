package com.zhu.compress.hfm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import com.zhu.compress.AbstractCompressor;
import com.zhu.compress.hfm.HuffmanTree.TreeNode;


/**
 * HUffman算法实现压缩工具
 * 
 * @author zhu
 *
 */
public class HFMCompressor extends AbstractCompressor{
	
	//标识符加版本号
	private static final byte[] HFM_MARK= {-66,1};
	/**
	 * 压缩字节数组
	 * 
	 * @param sources
	 * @return
	 */
	public  byte[] compress(byte[] sources) {
		if(sources==null) {
			return null;
		}
		// 构建Huffman树
		HuffmanTree tree = buildHuffman(sources);
		//哈夫曼树序列化内容
		byte[]treeBytes=tree.serialize();
		// 编码
		byte[] cBytes = encode(sources, tree);
		//连接哈夫曼树序列化后的数组与内容编码后的数组
		return concat(concat(HFM_MARK, treeBytes),cBytes);
	}
	

	/**
	 * 统计每个byte出现的次数 并将每个byte的统计结果 封装至TreeNode,构建成 Huffman数后返回
	 * 
	 * @param sources
	 * @return
	 */
	private  HuffmanTree buildHuffman(byte[] sources) {
		Map<Byte, Integer> counts = new HashMap<>();
		for (int i = 0; i < sources.length; i++) {
			if (counts.containsKey(sources[i])) {
				int count = counts.get(sources[i]);
				counts.put(sources[i], count + 1);
			} else {
				counts.put(sources[i], 1);
			}
		}
		// 添加一个用来判断结束的标志
		counts.put(HuffmanTree.END, 1);
		PriorityQueue<TreeNode> queue = new PriorityQueue<>();
		for (Entry<Byte, Integer> entry : counts.entrySet()) {
			queue.add(new TreeNode(null, null, entry.getKey(), entry.getValue()));
		}
		HuffmanTree tree = new HuffmanTree(queue);
		return tree;
	}

	/**
	 * 编码
	 * 
	 * @param sources
	 * @param tree
	 * @return
	 */
	private static byte[] encode(byte[] sources, HuffmanTree tree) {
		BitList list = new BitList(sources.length * 8);
		Map<Byte, String> table = createEncodeTable(tree);
		for (int i = 0; i < sources.length; i++) {
			byte bt = sources[i];
			String s = table.get(bt);
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					list.add(true);
				} else {
					list.add(false);
				}
			}
		}
		String s = table.get(HuffmanTree.END);
		for (int j = 0; j < s.length(); j++) {
			if (s.charAt(j) == '1') {
				list.add(true);
			} else {
				list.add(false);
			}
		}
		return list.toBytes();
	}

	/**
	 * 创建编码表
	 * 
	 * @param tree
	 * @return
	 */
	private static Map<Byte, String> createEncodeTable(HuffmanTree tree) {
		Map<Byte, String> table = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		createEncodeTable(tree.root, table, sb);
		return table;
	}

	private static void createEncodeTable(TreeNode root, Map<Byte, String> table, StringBuilder sb) {
		if (root.val != HuffmanTree.EMPTY_NODE_VAL) {
			table.put(root.val, sb.toString());
			return;
		}
		sb.append('0');
		createEncodeTable(root.left, table, sb);
		sb.deleteCharAt(sb.length() - 1);
		sb.append('1');
		createEncodeTable(root.right, table, sb);
		sb.deleteCharAt(sb.length() - 1);
	}
	
	/**
	 * 解压
	 * @param sources
	 * @return
	 */
	public  byte[] decompress(byte[] sources) {
		if(sources==null||sources.length==0||sources[0]!=-66) {
			return sources;
		}
		//将字节数组分割成哈夫曼树部分与数据部分
		byte[][] bts=cut(sources);
		//构建哈夫曼树
		HuffmanTree tree=new HuffmanTree();
		tree.deserialize(bts[0]);
		//解码
		return decode(bts[1], tree);
	}
	
	/**
	 * 解码
	 * @param datas
	 * @return
	 */
	private static byte[] decode(byte[] datas,HuffmanTree tree) {
		List<Byte>list=new LinkedList<>();
		BitList bitlist=new BitList(datas);
		int index=0;
		while(index<bitlist.size()) {
			TreeNode node=tree.root;
			while(!tree.isLeaf(node)) {
				if(bitlist.get(index++)) {
					node=node.right;
				}else {
					node=node.left;
				}
			}
			byte b=node.val;
			if(b==HuffmanTree.END) {
				break;
			}
			list.add(node.val);
		}
		return listToArray(list);
	}
}
