package structures;

public class Queue <T>{

	private NodeList<T> head;
	private NodeList<T> last;

	public Queue() {
	}

	public void enqueue(NodeList<T> node){
		if (head != null) {
			NodeList<T> actual = head;
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(node);
		}else{
			head = node;
		}
	}

	public NodeList<T> getLast() {
		return last;
	}
	
	public void strainBefore(T info, NodeList<T> node) {
		NodeList<T> reference ;
		NodeList<T> current = head;
		if(head != null) {
			if(info.equals(head.getInformation())) {
				node.setNext(head);
				head = node;
			}else {
				while (current.getNext().getInformation() != info) {
					current = current.getNext();
				}
				reference = current.getNext();
				current.setNext(node);
				node.setNext(reference);

			}
		}else {
			head = node;
		}
	}

	public boolean isEmpty() {
		if(head != null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void strainAfter(T info, NodeList<T> node) {
		NodeList<T> reference ;
		NodeList<T> current = head;
		if(head != null) {
			if(info.equals(head.getInformation())) {
				reference = head;
				node.setNext(head.getNext());
				reference.setNext(node);
				head = reference;
			}else {
				while (current.getInformation() != info) {
					current = current.getNext();
				}
				reference = current.getNext();
				current.setNext(node);
				node.setNext(reference);
			}
		}else {
			head = node;
		}
	}
	
	public NodeList<T> getHead() {
		return head;
	}

	public NodeList<T> dequeue(){
		NodeList<T> reference = head;
		head = reference.getNext();
		reference.setNext(null);
		return reference;
	}
	
	public void print() {
		NodeList<T> current = head;
		while (current != null) {
			System.out.println(current.getInformation());
			current = current.getNext();
		}
	}
	
	public void clear() {
		head = null;
	}
}