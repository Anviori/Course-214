/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 2
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 * 
 */

package assignment_2;

public class OrderList {

	private OrderListNode head;
	private OrderListNode tail;
	private OrderListNode cursor;
	private int listSize;
	
	/**
	 * [Initializes object to an empty list of Orders]
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 *     [Order initialized with head, tail, and cursor 
	 *     set to null.]
	 */
	
	public OrderList() {
		
		head = null;
		tail = null;
		cursor = null;
		listSize = 0;

	}
	
	/**
	 * [Gets head]
	 * 
	 * @return
	 *     [head]
	 */
	
	public OrderListNode getHead() {
		
		return head;
	
	}
	
	/**
	 * [Changes previous head with new head]
	 * 
	 * @param head
	 *     [head]
	 */

	public void setHead(OrderListNode head) {
		
		this.head = head;
	
	}
	
	/**
	 * [Gets tail]
	 * 
	 * @return
	 *     [tail]
	 */

	public OrderListNode getTail() {
		
		return tail;
	
	}
	
	/**
	 * [Changes previous tail with new tail]
	 * 
	 * @param tail
	 *     [tail]
	 */

	public void setTail(OrderListNode tail) {
		
		this.tail = tail;
	
	}
	
	/**
	 * [Gets cursor]
	 * 
	 * @return
	 *     [cursor]
	 */

	public OrderListNode getCursor() {
		
		return cursor;

	}
	
	/**
	 * [Changes previous cursor to new cursor]
	 * 
	 * @param cursor
	 *     [cursor]
	 */
	
	public void setCursor(OrderListNode cursor) {
		
		this.cursor = cursor;
	
	}
	
	/**
	 * [Number of orders in the list]
	 * 
	 * @return
	 *     [Returns the total number 
	 *     of Orders in the list. O(1)]
	 */
	
	public int numOrders() {
		
		return listSize;
		
	}
	
	/**
	 * [Gets reference to the Order wrapped by the OrderListNode that 
	 * is referenced by the cursor.]
	 * 
	 * @return
	 *     [The reference by the Order wrapped by the
	 *     OrderListNode currently referenced by cursor. 
	 *     If cursor = null, return null]
	 */
	
	public Order getCursorOrder() {
		
		if(cursor == null)		
			return null;
		else
			return cursor.getData();
		
	}
	
	/**
	 * [Returns cursor to the end of the list.]
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 *     [If tail is not null, cursor references the
	 *     last OrderListNode in the list. If tail is null,
	 *     cursor is set to null also (no orders in list).]
	 */
	
	public void resetCursorToTail() {
		
		if(tail != null)
			cursor = tail;
		else if(tail == null)
			cursor = null;
		
	}
	
	/**
	 * [Returns cursor to the start of the list]
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 *     [If head is not null, cursor references the
	 *     first OrderListNode in the list. If head is null,
	 *     cursor is set to null also (no orders in list).]
	 */
	
	public void resetCursorToHead() {
		
		if(head != null)
			cursor = head;
		else if(head == null)
			cursor = null;
		
	}
	
	/**
	 * [Moves cursor to the next OrderListNode in the list.
	 * If cursor is at tail of list, exception is thrown.]
	 * 
	 * @throws EndOfListException
	 *     [Cursor is at the tail of the list.]
	 */
	
	public void cursorForward() throws EndOfListException {
		
		if(cursor == tail || (cursor == null && tail == null))
			throw new EndOfListException(null);

		cursor = cursor.getNext();
		
	}
	
	/**
	 * [Moves cursor to the previous OrderListNode in the list. 
	 * If cursor is at head of list, exception is thrown.]
	 * 
	 * @throws EndOfListException
	 *     [Cursor is at the head of the list.]
	 */
	
	public void cursorBackward() throws EndOfListException {
		
		if(cursor == head || (cursor == null && head == null))
			throw new EndOfListException(null);
				
		cursor = cursor.getPrev();
		
		System.out.println("Cursor has moved Backward.\n");
		
	}
	
	/**
	 * [Inserts the given Order after the cursor.]
	 * 
	 * @param newOrder
	 *     [Given Order to be placed.]
	 * <dt><b>Preconditions:</b><dd>
	 *     [newOrder is not null]
	 * <dt><b>Postconditions:</b><dd>
	 *     [newOrder is wrapped in a new OrderListNode object.
	 *     If cursor was not null, the created OrderListNode is inserted
	 *     into the list after the cursor.
	 *     If cursor was null, the created OrderListNode was set as the new
	 *     head of the list (as well as tail).
	 *     The cursor now references the newly created OrderListNode.]
	 * @throws IllegalArgumentException
	 *     [If newOrder is null.]
	 */
	
	public void insertAfterCursor(Order newOrder) throws IllegalArgumentException {
		
		if(newOrder == null)
			throw new IllegalArgumentException();
		
		listSize++;
		
		OrderListNode newNode = new OrderListNode(newOrder);
		
		if(cursor != null) {
		
			newNode.setNext(cursor.getNext());
			newNode.setPrev(cursor);
		
			cursor.setNext(newNode);
			if(newNode.getNext() == null)
				return;
			newNode.getNext().setPrev(newNode);
			newNode.getPrev().setNext(newNode);
			
		} else if(cursor == null) {
			
			tail = newNode;
			head = newNode;
			cursor = newNode;
			
		}
		
	}
	
	/**
	 * [Insets newOrder at the head of the list.]
	 * 
	 * @param newOrder
	 *     [Given order to be inserted.]
	 * <dt><b>Preconditions:</b><dd>
	 *     [newOrder is not null.]
	 * <dt><b>Postconditions:</b><dd>
	 *     [newOrder is wrapped in a new OrderListNode object.
	 *     If head was not null, the created OrderListNode is now inserted
	 *     into the list before the head. 
	 *     If head was null, the created OrderListNode is now set as the new
	 *     head of the list (as well as the tail and the cursor).
	 *     The cursor now references the newly created OrderListNode.]
	 * @throws EndOfListException
	 *     [If newOrder is null.]
	 */
	
	public void insertNewHead(Order newOrder) throws IllegalArgumentException {
		
		if(newOrder == null)
			throw new IllegalArgumentException();
		
		OrderListNode newNode = new OrderListNode(newOrder);
		
		if(head != null) {
			
			newNode.setPrev(null);
			newNode.setNext(head);
			newNode.getNext().setPrev(newNode);
			head = newNode;
			cursor = newNode;
			
			listSize++;
			
 		} else if(head == null) {
 			
 			head = newNode;
 			tail = head;
 			cursor = head;
 			
 			listSize++;
 			
 		}
		
	}
	
	/**
	 * [Inserts a newOrder after an order that is similar.
	 * if no similar order, newOrder is inserted at the end
	 * of the list.]
	 * 
	 * @param newOrder
	 *     [Given order to be inserted into list.]
	 * @throws
	 *     [If newOrder is null]
	 */
	
	public void AfterSimilarOrder(Order newOrder) throws IllegalArgumentException {
		
		if(newOrder == null)
			throw new IllegalArgumentException();
		
		OrderListNode newNode = new OrderListNode(newOrder);
		OrderListNode tempNode = head;
		
		appendToTail(newOrder);
		
		while(tempNode != null) {
			
			if(tempNode.getData().getOrder().equals(newNode.getData().getOrder())) {	
				
				if(tempNode == tail) {
					
					tempNode.setNext(newNode);
					newNode.setPrev(tempNode);
					newNode.setNext(null);
					tail = newNode;
			
					cursor = newNode;
					
					listSize++;

					return;
					
				} else if(tempNode == head) {
					
					tempNode.getNext().setPrev(newNode);
					newNode.setNext(tempNode.getNext());
					tempNode.setNext(newNode);
					newNode.setPrev(tempNode);
					
					cursor = newNode;
					
					listSize++;
					
					return;

				} else if(tempNode != head && tempNode != tail){
					
					cursor.setNext(newNode);
					tempNode.getNext().setPrev(newNode);
					newNode.setNext(tempNode.getNext());
					tempNode.setNext(newNode);
					newNode.setPrev(tempNode);
					
					cursor = newNode;
					
					listSize++;
					
					return;
					
				}
	
			}
			
			tempNode = tempNode.getNext();
			
		}
		
	}
	
	/**
	 * [Copies and removes the Node the cursor is 
	 * currently pointing to.]
	 * 
	 * @return 
	 *     [Returns stored OrderListNode after it is cut.]
	 * @throws EndOfListException
	 *     [if cursor is null]
	 */
	
	public OrderListNode cut() throws EndOfListException {
		
		if(cursor == null)
			throw new EndOfListException(null);
		
		OrderListNode tempNode = cursor;

			String order = cursor.getData().getOrder();
			
			removeCursor();
			
			System.out.println(order + " is in clipboard.\n");
			
		return tempNode;
		
	}
	
	/**
	 * [Pastes the stored node after the cursor.]
	 * 
	 * @param orderStored
	 *     [Stored order to be inserted after cursor (pasted)]
	 * @throws EndOfListException
	 *     [if cursor is null.]
	 */
	
	public void paste(OrderListNode orderStored) throws EndOfListException {
		
		if(cursor == null)
			throw new EndOfListException(null);
		
		insertAfterCursor(orderStored.getData());
		
		System.out.println(orderStored.getData().getOrder() + " pasted after cursor.\n");
		
	}
	
	/**
	 * [Insets newOrder after the tail of the list.]
	 * 
	 * @param newOrder
	 *     [Given order to be inserted.]
	 * <dt><b>Preconditions:</b><dd>
	 *     [newOrder is not null.]
	 * <dt><b>Postconditions:</b><dd>
	 *     [newOrder is wrapped in a new OrderListNode object.
	 *     If tail was not null, the created OrderListNode is now inserted
	 *     into the list after the tail. 
	 *     If tail was null, the created OrderListNode is now set as the new
	 *     head of the list (as well as the tail and the cursor).
	 *     The tail now references the newly created OrderListNode.]
	 * @throws EndOfListException
	 *     [If newOrder is null.]
	 */
	
	public void appendToTail(Order newOrder) throws IllegalArgumentException {
		
		if(newOrder == null)
			throw new IllegalArgumentException();
		
		OrderListNode newNode = new OrderListNode(newOrder);
		
		if(tail != null) {
			
			newNode.setNext(null);
			newNode.setPrev(tail);
			newNode.getPrev().setNext(newNode);
			tail = newNode;
			cursor = newNode;
			
			listSize++;
			
 		} else if(tail == null) {
 			
 			tail = newNode;
 			head = tail;
 			cursor = tail;
 			
 			listSize++;
 			
 		}
		
	}
	
	/**
	 * [Removes the OrderListNode referenced by the cursor, and returns
	 * the Order inside.]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [Cursor is not null]
	 * <dt><b>Postconditions:</b><dd>
	 *     [The OrderListNode referenced by the cursor has been removed
	 *     from the list.
	 *     All other OrderListNodes in the list exist in the same order
	 *     as before.
	 *     The cursor now references the previous OrderListNode (or the head,
	 *     if the cursor previously referenced the head of the list.]
	 * @return
	 *     [the Order inside the removed OrderListNode referenced by the cursor]
	 * @throws EndOfListException
	 *     [if cursor is null]
	 */
	
	public Order removeCursor() throws EndOfListException {
		
		if(cursor == null) 
			throw new EndOfListException(null);
		
		Order order = cursor.getData();

		if(cursor == head) {	
			
			if(head != null) {
				
				head = head.getNext();
				cursor = head;
				
			} else if(head == null) {

				tail = null;
				cursor = head;
			
			}
			
		} else if(cursor == tail) {
			
			if(tail != null) {

				tail = tail.getPrev();
				cursor = tail;
				
			} else if(tail == null) {

				head = null;
				cursor = tail;
				
			}
			
		} else if(cursor != head && cursor != tail){

			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			
			cursor = cursor.getPrev();
		
		}
		
		listSize--;
		
		return order;
					
	}
	
	/**
	 * @return
	 *     [Returns a String representation of all the data inside 
	 *     all the nodes of the list.]
	 * 
	 */
	
	public String toString() {
		
		String list = String.format("%-25s %-30s %25s", "Order Name: ", "Special Instruction: ", "Price: ");
		
		list += "\n------------------------------------------"
				+ "----------------------------------------\n";
		
		OrderListNode tempNode = head;
		
		while(true) {
			
			if(head == null) {
				
				list+= "[No Orders]";
				
				break;
				
			}
			
			if (tempNode == cursor) {
				
				list += "=>" + tempNode.getData() + "\n";
			
			} else if (tempNode != cursor) {
			
				list += "  " + tempNode.getData() + "\n";
			
			}
			
			if (tempNode.getNext() == null)
				break;
				
			tempNode = tempNode.getNext();
			
		}
		
		return list;	
		
	}
	
}
