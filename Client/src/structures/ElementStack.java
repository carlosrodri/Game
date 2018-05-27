package structures;

public class ElementStack <T>{

	private NodeList<T> head;
	private NodeList<T> last;

	public ElementStack() {
		
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
	
	public void push(NodeList<T> node) {
		if(head != null) {
			node.setNext(head);
			head = node;
		}else {
			head = node;
		}
	}

	public NodeList<T> pop() throws Exception {
	if(head != null) {
		NodeList<T> popNode = head;
		head = head.getNext();
		popNode.setNext(null);
		return popNode;
		}else {
			throw new Exception("no hay mas elementos en la lista");
		}
	}
	
	public void enqueueCircle(NodeList<T> node) {
		if(head != null) {
			System.out.println(node.getInformation());
			last.setNext(node);
			node.setNext(head);
			last = node;
		}else {
			head = node;
			last = head;
		}
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