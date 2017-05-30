/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 2
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_2;

public class Order {

	private String order;
	private String specialInstruction;
	private double price;
	
	/**
	 * [This constructor creates a Order object with the
	 * given name and price and special instruction]
	 * 
	 * @param name
	 *     [Order object name]
	 * @param price
	 *     [Order object price]
	 * @param specialInstruction
	 *     [Order object special instructions]
	 * <dt><b>Postconditions:</b><dd>
	 *     [Object initialized to an Order object with specified name and price
	 *     and special instruction]
	 */
	
	public Order(String name, String specialInstruction, double price) {
		
		order = name;
		this.specialInstruction = specialInstruction;
		this.price = price;
		
	}
	
	/**
	 * [Gets the name of the Order object]
	 * 
	 * @return
	 *     [returns order name as a String]
	 */
	
	public String getOrder() {
		
		return order;
	
	}
	
	/**
	 * [Changes Order objects name]
	 * 
	 * @param order
	 *     [order name]
	 */
	
	public void setOrder(String order) {
		
		this.order = order;
	
	}
	
	/**
	 * [Gets the special instructions given by the user
	 *  of the Order object]
	 *  
	 * @return
	 *     [returns orders special instructions as a String]
	 */

	public String getSpecialInstruction() {
	
		return specialInstruction;
	
	}
	
	/**
	 * [Changes Order objects special instructions]
	 * 
	 * @param specialInstruction
	 *     [order object special instructions]
	 */
	
	public void setSpecialInstruction(String specialInstruction) {
		
		this.specialInstruction = specialInstruction;
	
	}
	
	/**
	 * [Gets the price of the order object]
	 * 
	 * @return
	 *     [returns orders price as a double]
	 */

	public double getPrice() {
		
		return price;

	}
	
	/**
	 * [Changes Order objects price]
	 * 
	 * @param price
	 *     [order object price]
	 */

	public void setPrice(double price) {
		
		this.price = price;
	
	}
	
	/**
	 * [Compares the equality of a given Order object
	 * with that of another object of the same type.]
	 * 
	 * @return
	 *     [A value of true if the obj and an Order object have the same
	 *     properties. Otherwise, false]
	 */
	@Override
	public boolean equals(Object obj) {
		
		//Order order = new Order(this.getOrder(), this.getSpecialInstruction(), this.getPrice());
		
		return (obj == this && obj != null);
		
	}
	
	/**
	 * @return
	 *     [Returns a String representing the Order with its name,
	 *     specialInstruction, and price.]
	 * 
	 */
	
	public String toString() {
		
		return String.format("%-25s %-30s %23.2f", order, specialInstruction, price);
		
	}
	
}