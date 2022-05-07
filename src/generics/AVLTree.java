package generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class AVLTree<T> {
	private Node<T> root;
	Comparator<T> comparator;

	public AVLTree() {
	}

	/**
	 * This is the constructor method of the AVLTree
	 * 
	 * @param comparator, (T) Comparator, this is the comparator of the object T
	 */
	public AVLTree(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * @return the root
	 */
	public Node<T> getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}

	/**
	 * @return the comparator
	 */
	public Comparator<T> getComparator() {
		return comparator;
	}

	/**
	 * @param comparator the comparator to set
	 */
	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	// A utility function to get height of the tree
	public int height(Node<T> N) {
		if (N == null)
			return 0;
		return N.getHeight();
	}

	/**
	 * This method rotates the given node to the right
	 * 
	 * @param y, (T) Node, this is the given node
	 * @return x, (T) Node, this is the resulting node rotated to the right
	 */
	private Node<T> rightRotate(Node<T> y) {
		Node<T> x = y.getLeft();
		Node<T> T2 = x.getRight();

		x.setRight(y);
		y.setLeft(T2);

		// Update heights
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

		return x;
	}

	/**
	 * This method rotates the given node to the left
	 * 
	 * @param x, (T) Node, this is the given node
	 * @return y, (T) Node, this is the resulting node rotated to the left
	 */
	private Node<T> leftRotate(Node<T> x) {
		Node<T> y = x.getRight();
		Node<T> T2 = y.getLeft();

		y.setLeft(x);
		x.setRight(T2);

		// Update heights
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

		return y;
	}

	/**
	 * This method gets the balance factor of a node
	 * 
	 * @param N, T (Node), this is the node which balance factor is being calculated
	 * @return
	 */
	private int getBalance(Node<T> N) {
		if (N == null)
			return 0;
		return height(N.getLeft()) - height(N.getRight());
	}

	/**
	 * In order to insert a new node, this method runs the <b>insert</b> method
	 * if already exists a root. It assigns a new node with the <b>value</b> to the
	 * root otherwise.
	 * 
	 * @param value, T, this is the value of the node to be added to the tree
	 */
	public void insert(T value) {
		Node<T> newNode = new Node<>(value);
		// In case this is the first insert in the tree
		if (root == null) {
			root = newNode;
		} else {
			root = insert(root, value);
		}
	}

	/**
	 * This method inserts a new node to the AVL tree. The logic of the insertion is
	 * the same as a binary tree. This method is recursive, and the variable of the
	 * recursion will be <b>node</b>
	 * 
	 * @param node   (T) Node, this is the iterated node. In the first time of the
	 *               recursion, this variable will be the root, then, the variable
	 *               will change according to the conditions given by the recursive
	 *               method.
	 * @param value, T, this is the value of the new node
	 * @return (T) Node, this may be the modified tree with the new node, or the
	 *         node without changes (in case the node with the initial condition is
	 *         already in the tree)
	 */
	private Node<T> insert(Node<T> node, T value) {
		// Normal BST rotation
		if (node == null)
			return (new Node<T>(value));

		if (compareValue(value, node.getValue()) < 0) // <0
			node.setLeft(insert(node.getLeft(), value));
		else if (compareValue(value, node.getValue()) > 0) // >0
			node.setRight(insert(node.getRight(), value));
		else // Equal values not allowed
			return node;

		/* Update height of this ancestor node */
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

		/*
		 * Get the balance factor of this ancestor node to check if this node became
		 * unbalanced
		 */
		int balance = getBalance(node);

		// If this node becomes unbalanced:
		// Left rotate
		if (balance > 1 && compareValue(value, node.getLeft().getValue()) < 0)// left <0
			return rightRotate(node);

		// Right rotate
		if (balance < -1 && compareValue(value, node.getRight().getValue()) > 0)// right >0
			return leftRotate(node);

		// Left right rotate
		if (balance > 1 && compareValue(value, node.getLeft().getValue()) > 0) { // left >0
			node.setLeft(leftRotate(node.getLeft()));
			return rightRotate(node);
		}

		// Right left rotate
		if (balance < -1 && compareValue(value, node.getRight().getValue()) < 0) { // right <0
			node.setRight(rightRotate(node.getRight()));
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	/**
	 * This method compares two values of T class, it returns a negative int if
	 * value1 is smaller than value2, a positive int in the opposite case, and 0 if
	 * they are equivalent
	 * 
	 * @param value1, this is the first value to compare
	 * @param value2, this is the second value to compare
	 * @return int, negative int if value1 is smaller than value2, a positive int in
	 *         the opposite case, and 0 if they are equivalent
	 */
	private int compareValue(T value1, T value2) {
		if (this.comparator == null) {
			return ((Comparable) value1).compareTo(value2);
		} else {
			return this.comparator.compare(value1, value2);
		}
	}

	/**
	 * This method returns the node with the minimum value of the specified subtree.
	 * 
	 * @param node, (T) Node, this will be the subtree which node with the minimum
	 *              value will be found
	 * @return current, (T) Node, this is the minimum node of the specified subtree
	 */
	private Node<T> minValueNode(Node<T> node) {
		Node<T> current = node;

		/* loop down to find the leftmost leaf */
		while (current.getLeft() != null)
			current = current.getLeft();

		return current;
	}

	/**
	 * This method executes the deletion method
	 * 
	 * @param value, T, this is the value of the node to be deleted
	 */
	public void delete(T value) {
		root = deleteNode(root, value);
	}

	/**
	 * This method deletes a node with the specified value, and rebalances the tree
	 * after that.
	 * 
	 * @param root,  T (Node), this is the node of the iteration. It will be the
	 *               root in the first case, but it will change according to the
	 *               given conditions of the method.
	 * @param value, T, this is the value of the node to be deleted
	 * @return
	 */
	private Node<T> deleteNode(Node<T> root, T value) {
		// STEP 1: PERFORM STANDARD BST DELETE
		if (root == null)
			return root;

		// If the value to be deleted is smaller than the root's value, the deletion
		// will be in the left of the iterated node
		if (compareValue(value, root.getValue()) < 0) // <0
			root.setLeft(deleteNode(root.getLeft(), value));

		// If the value to be deleted is greater than the root's value, the deletion
		// will be
		// in the right of the iterated node
		else if (compareValue(value, root.getValue()) > 0) // <0
			root.setRight(deleteNode(root.getRight(), value));

		// if value is same as root's value, then this is the node to be deleted
		else {
			if ((root.getLeft() == null) || (root.getRight() == null)) {
				Node<T> temp = null;
				if (temp == root.getLeft())
					temp = root.getRight();
				else
					temp = root.getLeft();

				// The node to delete has no children
				if (temp == null) {
					temp = root;
					root = null;
				} else // The node to delete only has one child
					root = temp;
			} else {

				// If the node to delete has two children, the successor must be found (the left
				// minimum node of the right subtree)
				Node<T> temp = minValueNode(root.getRight());

				// The successor becomes the root of the tree
				root.setValue(temp.getValue());

				// Delete the found successor of the tree
				root.setRight(deleteNode(root.getRight(), temp.getValue()));
			}
		}

		// If the tree had only one node then return
		if (root == null)
			return root;

		// Update the height of the iterated node
		root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);

		/*
		 * Get the balance factor of this ancestor node to check if this node became
		 * unbalanced
		 */
		int balance = getBalance(root);

		// If this node becomes unbalanced:
		// Left rotate
		if (balance > 1 && getBalance(root.getLeft()) >= 0)
			return rightRotate(root);

		// Left right rotate
		if (balance > 1 && getBalance(root.getLeft()) < 0) {
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}

		// Right rotate
		if (balance < -1 && getBalance(root.getRight()) <= 0)
			return leftRotate(root);

		// Right left rotate
		if (balance < -1 && getBalance(root.getRight()) > 0) {
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}

		return root;
	}

	/**
	 * This method travels through the tree in Pre-order way
	 * 
	 * @return list, (T) List, this is a list ordered in pre-order way
	 */
	public List<T> preOrden() {
		List<T> list = new ArrayList<>();
		Node<T> node = this.getRoot();
		Stack<Node<T>> heap = new Stack<>();
		while ((node != null && node.getValue() != null) || !heap.empty()) {
			if (node != null) {
				System.out.println(list.size()+1);
				list.add(node.getValue());
				heap.push(node);
				node = node.getLeft();
			} else {
				node = heap.pop();
				node = node.getRight();
			}
		}
		return list;
	}
	
	/**
	 * This method executes the search method
	 * @param value, T, this is the value of the node to find
	 * @return, T, value of the found node
	 */
	public T search(T value) {
		Node<T> output = search(root, value);
		return output == null ? null : output.getValue();
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param root, (T) Node this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) Node, this is the node that contains the searched value
	 */
	public Node<T> search(Node<T> root, T value) {
		// Values are equivalent. Return the iterated node
		if (root == null || compareValue(root.getValue(), value) == 0) // 0
			return root;

		// Value is greater than root's value. Search in the right child
		if (compareValue(root.getValue(), value) < 0) // <0
			return search(root.getRight(), value);

		// Value is smaller than root's value. Search in the left child
		return search(root.getLeft(), value);
	}

	/**
	 * This method shows the AVL tree in a preOrder way
	 * 
	 * @param node, this is the root of the AVL tree
	 */
	public void preOrder(Node<T> node) {
		if (node != null) {
			System.out.println(node.getValue() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	/**
	 * This method shows the AVL tree in a inOrder way
	 * 
	 * @param node, this is the root of the AVL tree
	 */
	public void inOrder(Node<T> node) {
		if (node != null) {
			inOrder(node.getLeft());
			System.out.println(node.getValue());
			inOrder(node.getRight());
		}
	}
}
