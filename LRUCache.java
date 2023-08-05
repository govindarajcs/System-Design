package com.learning.system.design.cache;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

	class Node {
		Integer data;
		Node left;
		Node right;
	}
	
	
	Integer size;
	Map<Integer, Node> lookupTable = new HashMap<Integer, Node>();
	Node head, tail;
	public void reference(int data) {
		Node referenceNode;
		if(lookupTable.containsKey(data)) {
			referenceNode = lookupTable.get(data);
			if(referenceNode.left!=null && referenceNode.right!=null) {
				// it is not a head node and tail node
				Node temp = referenceNode.left;
				temp.right = referenceNode.right;
				referenceNode.right.left=temp;
				referenceNode.left = null;
				referenceNode.right = head;
				head = referenceNode;
			} else if(referenceNode.right==null) {
				// if it is a tail node
				tail = referenceNode.left;
				tail.right = null;
				referenceNode.right = head;
				referenceNode.left = null;
				head.left = referenceNode;
				head = referenceNode;
			}
		} else {
			referenceNode = new Node();
			referenceNode.data = data;
			if(lookupTable.size()==0) {
				head = referenceNode;
				tail = head;
				lookupTable.put(data, referenceNode);
			} else if(lookupTable.size()==size) {
				/*
				 * add head node
				 */
				referenceNode.right = head;
				head.left = referenceNode;
				head = referenceNode;
				/*
				 * remove tail node
				 */
				Node temp = tail.left;
				temp.right=null;
				tail.left = null;
				lookupTable.remove(tail.data);
				tail = temp;
				lookupTable.put(data, referenceNode);
			} else {
				// update head node
				referenceNode.right = head;
				head.left = referenceNode;
				head = referenceNode;
				lookupTable.put(data, referenceNode);
			}
		}
	}
	
	public void displayCache() {
		Node node = head;
		while(node!=null) {
			System.out.print(node.data+" ");
			node = node.right;
		}
	}
	
	public static void main(String[] args) {
		 	LRUCache cache = new LRUCache();
		 	cache.size = 3;
		 	// 1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5
	        cache.reference(1);
	        cache.reference(2);
	        cache.reference(3);
	        cache.reference(4);
	        cache.reference(1);
	        cache.reference(2);
	        cache.reference(5);
	        cache.reference(1);
	        cache.reference(2);
	        cache.reference(3);
	        cache.reference(4);
	        cache.reference(5);
	        cache.displayCache();
	}
}
