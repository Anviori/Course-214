/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 2
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_2;

public class OrderListNode {

	private Order data;
	private OrderListNode next;
	private OrderListNode prev;
	
	/**
	 * [Initializes OrderListNode to wrap given Order]
	 * 
	 * @param initData
	 *     [Data to be wrapped by OrderListNode.]
	 * <dt><b>Preconditions:</b><dd>
	 *     [initData is not null.]
	 * <dt><b>Postconditions:</b><dd>
	 *     [This OrderListNode has been initialized to wrap the 
	 *     indicated Order, and prev and next have been set to null.]
	 * @throws IllegalArgumentException
	 *     [if initData is null.]
	 */
	
	public OrderListNode(Order initData) throws IllegalArgumentException {
		
		if(initData == null)
			throw new IllegalArgumentException();
	
		data = initData;	
		next = null;
		prev = null;
			
	}
	
	/**
	 * [Gets the Order data in the current Node.]
	 * 
	 * @return
	 *     [data]
	 */
	
	public Order getData() {
		
		return data;
	
	}
	
	/**
	 * [Changes previous Order data with new data.]
	 * 
	 * @param data
	 *     [data field]
	 */
	
	public void setData(Order data) {
		
		this.data = data;
	
	}
	
	/**
	 * [Gets reference 'link' to next node in the list.]
	 * 
	 * @return
	 *     [next link reference in list.]
	 */

	public OrderListNode getNext() {
		
		return next;
	
	}
	
	/**
	 * [Changes old next 'link' reference to a new one.]
	 * 
	 * @param next
	 *    [new reference]
	 */

	public void setNext(OrderListNode next) {
		
		this.next = next;
	
	}

	/**
	 * [Gets reference 'link' to previous node in the list.]
	 * 
	 * @return
	 *     [previous link reference in list.]
	 */
	
	public OrderListNode getPrev() {
		
		return prev;
	
	}
	
	/**
	 * [Changes previous 'link' reference to a new one.]
	 * 
	 * @param prev
	 *     [new reference]
	 */
	
	public void setPrev(OrderListNode prev) {
		
		this.prev = prev;
	
	}

}
