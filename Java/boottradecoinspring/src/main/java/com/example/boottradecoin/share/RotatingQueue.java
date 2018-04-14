package com.example.boottradecoin.share;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Mathias Bak Bertelsen Email: bufas@cs.au.dk Date: 03-08-2014
 * 
 * A very simple implementation of a generic FIFO queue.
 */
public class RotatingQueue<T> {

	public RotatingQueue(int capacity) {
		size = capacity;
		queue = new ArrayList<T>(capacity);
		mostRecentItem = capacity - 1;
	}

	/**
	 * Inserts an element to the head of the queue, pushing all other elements
	 * one position forward.
	 *
	 * @param element
	 */
	public void insertElement(T element) {
		// Get index
		mostRecentItem = advancePointer(mostRecentItem);

		// Check if list already has an element
		if (queue.size() == mostRecentItem) {
			queue.add(element);
		} else {
			queue.set(mostRecentItem, element);
		}
	}

	public T getElement(int index) {
		// Normalize index to size of queue
		index = index % size;

		// Translate wanted index to queue index
		int queueIndex = mostRecentItem - index;
		// If negative, add size
		if (queueIndex < 0) {
			queueIndex += size;
		}

		// Check if element already exists in queue
		if (queueIndex < queue.size()) {
			return queue.get(queueIndex);
		} else {
			return null;
		}
	}

	public int size() {
		return size;
	}

	private int advancePointer(int oldPointer) {
		int pointer = oldPointer + 1;
		if (pointer < size) {
			return pointer;
		} else {
			return 0;
		}
	}

	///
	// INSTANCE VARIABLES
	///
	private List<T> queue;
	private int mostRecentItem;
	private int size;
	
	public static void main(String[] args) {
		RotatingQueue<Integer> queue = new RotatingQueue(4);
		queue.insertElement(1);
		queue.insertElement(2);
		queue.insertElement(3);
		queue.insertElement(4);
		queue.insertElement(5);
		queue.insertElement(6);
	}
}
