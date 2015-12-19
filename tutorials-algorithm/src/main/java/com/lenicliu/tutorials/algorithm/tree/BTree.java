package com.lenicliu.tutorials.algorithm.tree;

public class BTree<T extends Comparable<T>> {

	public BTree(T value) {
		super();
		this.value = value;
	}

	private BTree<T>	left;
	private BTree<T>	right;
	private T			value;

	public BTree<T> find(T value) {
		if (this.value != null && value != null) {
			if (this.value.compareTo(value) == 0) {
				return this;
			} else if (this.value.compareTo(value) < 0) {
				if (this.left == null) {
					return null;
				}
				return this.left.find(value);
			} else if (this.value.compareTo(value) > 0) {
				if (this.right == null) {
					return null;
				}
				return this.right.find(value);
			}
		}
		return null;
	}

	public boolean add(T value) {
		if (this.value != null && value != null) {
			if (this.value.compareTo(value) == 0) {
				return false;
			} else if (this.value.compareTo(value) > 0) {
				this.addLeft(value);
				return true;
			} else if (this.value.compareTo(value) > 0) {
				this.addRight(value);
				return true;
			}
		}
		return false;
	}

	private void addRight(T value) {
		if (this.right == null) {
			this.right = new BTree<T>(value);
		} else {
			this.right.add(value);
		}
	}

	private void addLeft(T value) {
		if (this.left == null) {
			this.left = new BTree<T>(value);
		} else {
			this.left.add(value);
		}
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (this.left != null) {
			buf.append(this.left.toString());
		}
		buf.append("[" + this.value + "]");
		if (this.right != null) {
			buf.append(this.right.toString());
		}
		return buf.toString().replaceAll("]\\[", ", ");
	}
}