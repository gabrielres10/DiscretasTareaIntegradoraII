package model;

public class RedBlackTree<T extends Comparable<T>> {

	private RedBlackNode<T> root;

	public RedBlackTree(T value) {
		root = new RedBlackNode<T>(value);
	}
	
	public RedBlackTree() {
		
	}

	public RedBlackNode<T> getRoot() {
		return root;
	}

	public RedBlackNode<T> search(T value) {
		RedBlackNode<T> found = search(value, root);
		return found;

	}


	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) RedBlackNode this is the node of the iteration, in the
	 *                 first time it will always be the root of the tree
	 * @return (T) RedBlackNode, this is the node that contains the searched value
	 */
	private RedBlackNode<T> search(T value, RedBlackNode<T> current) {
		if (this.root == null) {
			return null;
		} else if (current.getValue().equals(value)) {
			return current;
			// Iteration of the search method
		} else if ((current.getValue()).compareTo(value) == -1) {
			// if the value of the current node is smaller than the value that is being
			// searched
			return search(value, current.getRightChild());
		} else {
			// the value of the current node is bigger
			return search(value, current.getLeftChild());
		}

	}

	public void insert(T value) {
		RedBlackNode<T> node = new RedBlackNode<>(value);
		root = insert(root, node);
		recolorAndRotate(node);
	}

	private void recolorAndRotate(RedBlackNode<T> node) {
		// TODO Auto-generated method stub
		RedBlackNode<T> parent = node.getParent();
		if (node != root && parent.getColor() == Color.RED) {
			RedBlackNode<T> grandParent = node.getParent().getParent();
			RedBlackNode<T> uncle = parent.isLeftChild() ? grandParent.getRightChild() : grandParent.getLeftChild();
			if (uncle != null && uncle.getColor() == Color.RED) {
				// recoloring the nodes
				uncle.flipColor();
				parent.flipColor();
				grandParent.flipColor();
				recolorAndRotate(grandParent);
			} else if (parent.isLeftChild()) {
				// left-heavy or left-right
				handleLeftSituations(node, parent, grandParent);
			} else if (!parent.isLeftChild()) {
				// right-heavy or right-left
				handleRightSituations(node, parent, grandParent);
			}
		}
		root.setColor(Color.BLACK);
	}

	private void handleRightSituations(RedBlackNode<T> node, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
		// TODO Auto-generated method stub
		parent.flipColor();
		grandParent.flipColor();
		rotateLeft(grandParent);
		recolorAndRotate(node.isLeftChild() ? parent : grandParent);

	}

	private void rotateRight(RedBlackNode<T> node) {
		// TODO Auto-generated method stub
		RedBlackNode<T> aux = node.getLeftChild();
		node.setLeftChild(aux.getRightChild());
		if (node.getLeftChild() != null) {
			node.getLeftChild().setParent(node);
		}
		aux.setRightChild(node);
		aux.setParent(node.getParent());
		updateChildOfParentNode(node, aux);
		node.setParent(aux);

	}

	private void handleLeftSituations(RedBlackNode<T> node, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
		// TODO Auto-generated method stub
		if (!node.isLeftChild()) {
			rotateLeft(parent);
		}

		parent.flipColor();
		grandParent.flipColor();
		rotateRight(grandParent);
		recolorAndRotate(node.isLeftChild() ? parent : grandParent);
	}
	
	
	private void rotateLeft(RedBlackNode<T> node) {
		// TODO Auto-generated method stub
		RedBlackNode<T> aux = node.getRightChild();
		node.setRightChild(aux.getLeftChild());
		if(node.getRightChild() != null) {
			node.getRightChild().setParent(node);
		}
		aux.setLeftChild(node);
		aux.setParent(node.getParent());
		updateChildOfParentNode(node, aux);
		node.setParent(aux);
		

	}

	/**
	 * This method sets the children of a node that is supposed to be a grandparent
	 * 
	 * @param node, (T) RedBlackNode, this is the node that can be a grandparent
	 * @param aux,  (T) RedBlackNode, this is the node that must be a child
	 */
	private void updateChildOfParentNode(RedBlackNode<T> node, RedBlackNode<T> aux) {
		if (node.getParent() == null) {
			root = aux;
		} else if (node.isLeftChild()) {
			node.getParent().setLeftChild(aux);
		} else {
			node.getParent().setRightChild(aux);
		}
	}

	private RedBlackNode<T> insert(RedBlackNode<T> subtree, RedBlackNode<T> newNode) {
		// TODO Auto-generated method stub
		if (subtree == null) {
			return newNode;
		}
		if (newNode.getValue().compareTo(subtree.getValue()) == -1) {
			subtree.setLeftChild(insert(subtree.getLeftChild(), newNode));
			subtree.getLeftChild().setParent(subtree);
		} else if (newNode.getValue().compareTo(subtree.getValue()) == 1) {
			subtree.setRightChild(insert(subtree.getRightChild(), newNode));
			subtree.getRightChild().setParent(subtree);
		}

		return subtree;

	}
	
	/**
	 * This method shows the AVL tree in a preOrden way
	 * 
	 * @param root, this is the root of the AVL tree
	 */
	public void preOrden(RedBlackNode<T> root) {
		if (root != null) {
			System.out.print(root.getValue() + " ");
			preOrden(root.getLeftChild());
			preOrden(root.getRightChild());
		}
	}

}
