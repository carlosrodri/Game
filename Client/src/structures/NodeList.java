package structures;

public class NodeList <T>{

	private NodeList<T> next;
	private T information;
	
	public NodeList(T information) {
		this.information = information;
	}

	public T getInformation() {
		return information;
	}

	public NodeList<T> getNext() {
		return next;
	}
	
	public void setNext(NodeList<T> next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return information.toString();
	}
}