package structures;

import java.util.ArrayList;

public class Tree<T> {

	private NodeTree<T> root;

	public Tree() {
		root = new NodeTree<T>(null, null);
	}

	public void add(NodeTree<T> node, T info) {
		NodeTree<T> search = search(info);
		if(!validate(node.getInfo())) {
			search.addToList(node);
		}else {
			root.addToList(node);
		}
	}

	private boolean validate(T info) {
		if(root.getNodeList().size() != 0) {
			for (NodeTree<T> node : root.getNodeList()) {
				if(node.getInfo().equals(info)) {
					return true;
				}
			}
		}
		return false;
	}

	public void print(NodeTree<T> node) {
		for (NodeTree<T> iterable_element : node.getNodeList()) {
			System.out.println(iterable_element.getNodeList().size() + "list interna");
		}
		if (node.getNodeList() != null) {
			System.out.println(node.getInfo() + "    padre");
			printList(node.getNodeList());
		}else {
			System.out.println(node.getInfo().toString());
		}
	}

	private void printList(ArrayList<NodeTree<T>> list) {
		int i = 0;
		for (NodeTree<T> node : list) {
			System.out.println(node.getInfo() + "    hijo");
		}
		if(i < list.size()) {
		print(list.get(i));
		i++;
		}
	}

	public NodeTree<T> search(T info){
		for (NodeTree<T> iterable_element : root.getNodeList()) {
			if(iterable_element.equals(info)) {
				return iterable_element;
			}
		}
		return root;
	}

	//	private NodeTree<T> search(T info, NodeTree<T> nodeTree) {
	//		if(nodeTree.getNodeList() != null) {
	//			for (NodeTree<T> node : nodeTree.getNodeList()) {
	//				if(node.getInfo().equals(info)) {
	//					return node;
	//				}else {
	//					return search(info, node);
	//				}
	//			}
	//		}
	//		System.out.println("retorna nuloooooo en profundidad");
	//		return nodeTree;
	//	}

	public NodeTree<T> getRoot(){
		return root;
	}

}