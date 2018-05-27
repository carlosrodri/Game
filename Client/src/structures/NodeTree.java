package structures;

import java.util.ArrayList;

public class NodeTree<T> {
	private ArrayList<NodeTree<T>> nodeList;
	private NodeTree<T> fatherNode;
	private T info;
	
	public NodeTree(T info, NodeTree<T> fatherNode) {
		nodeList = new ArrayList<>();
		this.info = info;
		this.fatherNode = fatherNode;
	}
	
	public T getInfo() {
		return info;
	}
	
	public ArrayList<NodeTree<T>> getNodeList() {
		return nodeList;
	}
	
	public void setNodeList(ArrayList<NodeTree<T>> nodeList) {
		this.nodeList = nodeList;
	}

	public NodeTree<T> getFatherNode(){
		return fatherNode;
	}

	public void addToList(NodeTree<T> node) {
		nodeList.add(node);
	}
}