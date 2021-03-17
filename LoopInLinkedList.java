package com.code.more;
class Node{
	Node next;
	int data;
	public Node(int data){
		this.data = data;
		this.next = null;
	}
}
public class LoopInLinkedList {	
	Node head;	
	public void addElement(int data) {
		Node temp =  head;
		if(temp == null) {
			Node n = new Node(data);
			head = n;
		}else {
			while(temp.next != null) {
				temp =  temp.next;
			}
			Node n =  new Node(data);
			temp.next = n;
		}
		
	}
	public void AddElements() {
		for(int i=0; i<11; i++) {
			addElement(i+1);
		}
	}
	
	public void printLinkedList() {
		Node temp = head;
		while(temp.next != null) {
			System.out.print("->"+temp.data);
			if(temp.data == temp.next.data)
				break;
			temp =  temp.next;
		}
		System.out.print("->"+temp.data);
	}
	
	private void findLoop() {
		Node slow = head, fast = head;
		
		while(fast.next != null /*&& fast != null*/) {
			slow = slow.next;
			fast = fast.next;
			if(fast.next != null)
				fast = fast.next;
			//4877
			if(slow == fast) {
				System.out.println("Loop found!!");
				break;
			}
		}
		
		slow = head;
		int loopCount=1;
		while(slow != fast) {
			slow = slow.next;
			fast =  fast.next;
		}
		System.out.println("Loop found at "+slow.data);
		Node prev = null;
		fast = slow.next;
		while(slow != fast) {
			prev = fast;
			fast =  fast.next;
			loopCount++;
		}
		System.out.println("Loop length "+loopCount);
		
		System.out.println("Removing loop by inserting null at the next of "+prev.data);
		prev.next = null;
		
	}
	private void addLoop(int i) {
		Node temp = head, loc = null;
		while(temp.next != null) {
			if(temp.data == i) {
				loc = temp;
				//break;
			}
			temp =  temp.next;
		}
		
		temp.next = loc;
		System.out.println("\nInserted "+temp.data+" to "+loc.data);
		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello lined list");
		LinkedList ll = new LinkedList();
		ll.AddElements();
		ll.printLinkedList();
		ll.addLoop(4);				
		ll.findLoop();
		System.out.println("Linked listed after removing loop:\n");
		ll.printLinkedList();
		//Node node = new Node(1);
		
	}
	
	

}
