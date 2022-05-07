package generics;

public class Node<T> {
	private int height;
	private T value;
	private Node<T> left, right;

	public Node(T value) {
		this.value = value;
		height = 1;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @return the left
	 */
	public Node<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Node<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	
}
