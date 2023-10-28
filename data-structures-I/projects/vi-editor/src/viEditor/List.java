package viEditor;

public class List {
	private Node head;
	private Node tail;
	private int count;
	
	public List() {
		head = null;
		tail = null;
		count = 0;
	}

	public boolean isEmpty() { return count == 0; }

	public boolean isFull() {
		Node temp = new Node();
		return temp == null;
	}

	// Insert in the end of the list
	public boolean append(String data) {
		if (isFull()) return false;

		// Create new node to be inserted in the end of the list
		Node node = new Node(data);
		
		// List is empty
		if (head == null) { 
			head = node;
		} else {
			// Insert new node in the end of the list
			tail.setNext(node);
			node.setPrevious(tail);
		}

		// The new node is the last of the list
		tail = node;

		// Circular list
		tail.setNext(head);
		head.setPrevious(tail);
		
		count++;
		return true;
	}

	// Insert in the beginning of the list
	public boolean insert(String data) {
		if (isFull()) return false;

		Node node = new Node(data);

		if (head == null) {
			tail = node;
		} else {
			node.setNext(head);
			head.setPrevious(node);
		}

		head = node;
		tail.setNext(head);
		head.setPrevious(tail);

		++count;
		return true;
	}

	// Insert in the middle of the list
	public boolean insertMid(String data, int line) {
		if (isFull()) return false;
		if (line <= 0 || line > count) return false;

		Node node = new Node(data);

		// New node's next points to previous node's next
		node.setNext(node.getPrevious());

		// Previous node's next points to new node
		Node currPointer = head;
		Node prevPointer;

		int index = 1;

		if (line == 1) {
			insert(data);
		} else {
			while (currPointer.getNext() != head) {
				prevPointer = currPointer;
				currPointer = currPointer.getNext();

				if (index == line) {
					// When currPointer reaches the given line
					prevPointer.setNext(node);
					node.setPrevious(prevPointer);

					node.setNext(currPointer);
					currPointer.setPrevious(node);
					break;
				}

				++index;
			}
		}

		++count;
		return true;
	}

	// remove
	public boolean removeLine(int line) {
		if (isEmpty()) return false;

		// Initializing a current and previous pointers
		Node currPointer = head;
		Node prevPointer;

		int index = 0;

		// Iterates through the list until the given line is reached
		do {
			prevPointer = currPointer;
			currPointer = currPointer.getNext();
			++index;
		} while (index != line);

		// If the given line is the first
		if (index == 1) {
			// There will be a new head
			head = currPointer;

			// prevPointer is the previous head, so it will be pointing to null
			// since it's going to be removed
			prevPointer.setNext(null);
			prevPointer.setPrevious(null);

			// New head and the actual tail will be pointing to each other
			head.setPrevious(tail);
			tail.setNext(head);
		} else if (index == count) {
			// tail is set to a past node and connects again with the head
			tail = prevPointer.getPrevious();
			tail.setNext(head);

			head.setPrevious(tail);

			// terminating connections of the deleted node
			prevPointer.setPrevious(null);
			prevPointer.setNext(null);
		} else {
			// new node to store the previous node from prevNode
			Node temp = prevPointer.getPrevious();

			// the currPointer is pointing to the temp as its previous node
			// the next temp's node is now the currPointer
			currPointer.setPrevious(temp);
			temp.setNext(currPointer);

			// cutting connections of the deleted node
			prevPointer.setNext(null);
			prevPointer.setPrevious(null);
		}

		--count;
		return true;
	}

	// search

	public Node getHead() { return head; }

	public Node getTail() { return tail; }
	
	@Override
	public String toString() {
		if (head == null) {
			return null;
		}
		
		Node aux = head;
		
		StringBuilder sb = new StringBuilder();
		sb.append("count: " + count + '\n');
		
		do {
			sb.append(aux).append(" ->\n");
			aux = aux.getNext();
		} while (aux != head);
		
		return sb.toString();
	}
}
