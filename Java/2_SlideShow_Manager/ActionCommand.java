/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 3
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_3;

import java.util.*;

public class ActionCommand {
	
	private int positionOne;
	private int positionTwo; 
	private String photo; 
	final ActionType type;
	
	/**
	 * [Initializes ActionCommand Object to the given position, 
	 * photo and ActionType. Called when ActionType is ADD.]
	 * 
	 * @param posOne
	 *     [Given position of the object.]
	 * @param photo
	 *     [Given photo name of the object.]
	 * @param type
	 *     [Action given by the user, used to 
	 *     perform that action on the object.]
	 */
	
	public ActionCommand(int posOne, String photo, ActionType type) {
		
		positionOne = posOne;
		this.photo = photo;
		this.type = type;
		
	}
	
	/**
	 * [Initializes ActionCommand Object to the given position, 
	 * and ActionType. Called when ActionType is REMOVE.]
	 * 
	 * @param posOne
	 *     [Given position of the object.]
	 * @param type
	 *     [Action given by the user, used to 
	 *     perform that action on the object.]
	 */
	
	public ActionCommand(int posOne, ActionType type) {
		
		positionOne = posOne;
		this.type = type;
		
	}
	
	/**
	 * [Initializes ActionCommand Object to the given positions, 
	 * photo and ActionType. Called when ActionType is SWAP or MOVE.]
	 * 
	 * @param posOne
	 *     [The position of the source photo.]
	 * @param posTwo
	 *     [Position of the destination of the photo.]
	 * @param type
	 *     [Action given by the user, used to 
	 *     perform that action on the object.]
	 */
	
	public ActionCommand(int posOne, int posTwo, ActionType type) {
	
		setPositionOne(posOne);
		setPositionTwo(posTwo);
		this.type = type;
		
	}
	
	/**
	 * [Gets the ActionType of the ActionCommand
	 * object (ADD, REMOVE, SWAP, or MOVE).]
	 * 
	 * @return
	 *     [ActionType: The objects action.]
	 */
	
	public ActionType getType() {
		
		return type;
		
	}
	
	/**
	 * [Gets the first position of the ActionCommand object.]
	 * 
	 * @return
	 *     [int: position of the object.]
	 */
	
	public int getPositionOne() {
		
		return positionOne;

	}
	
	/**
	 * [Sets the first position of the object.]
	 * 
	 * @param positionOne
	 *     [Position of the object.]
	 */

	public void setPositionOne(int positionOne) {
	
		this.positionOne = positionOne;
	
	}
	
	/**
	 * [Gets the second position of the object
	 * when the user chooses to SWAP a picture.]
	 * 
	 * @return
	 *     [int: the position of the destination of the
	 *     object.]
	 */
	
	public int getPositionTwo() {
	
		return positionTwo;
	
	}
	
	/**
	 * [Sets the second position of the ActionCommand 
	 * object.]
	 * 
	 * @param positionTwo
	 *     [Second position of the object (destination).]
	 */

	public void setPositionTwo(int positionTwo) {
	
		this.positionTwo = positionTwo;
	
	}
	
	/**
	 * [Gets the photo of the ActionCommand Object.]
	 * 
	 * @return
	 *     [String: photo name.]
	 */

	public String getPhoto() {
	
		return photo;
	
	}

	/**
	 * [Changes the name of the photo of the
	 * ActionCommand Object.]
	 * 
	 * @param photo
	 *     [Photo name of ActionCommand Object.]
	 */
	
	public void setPhoto(String photo) {
		
		this.photo = photo;
		
	}

	/**
	 * [Performs the action on the given slide-show object.]
	 * 
	 * @param slideshow
	 *    [Slide-show object received to perform an action on it
	 *    (add it, remove it, move it, or swap it).]
	 */
	
	public void perform(ArrayList<String> slideshow) {
		
		switch (type) {
		
			case ADD:

				slideshow.add(positionOne, getPhoto());
				
				break;
			
			case REMOVE:
				
				String r = slideshow.remove(getPositionOne());
				
				this.photo = r;
				
				break;
			
			case MOVE:
				
				String a = slideshow.remove(positionOne);
				slideshow.add(positionTwo, a);
				
				this.photo = a;

				break;
		
			case SWAP:
				
				Collections.swap(slideshow, positionOne, positionTwo);
				
				break;
		
		}
		
	}
	
	/**
	 * [Generate a new action command that would undo the 
	 * this action command.]
	 * 
	 * @return
	 *     [ActionCommand: The inverse of the action 
	 *     (Ex: ADD would generate a REMOVE).]
	 */
	
	public ActionCommand getInverse() {
	
		ActionCommand actionTemp = null;
		
		switch (type) {
		
			case ADD:
		
				actionTemp = new ActionCommand(positionOne, photo, ActionType.REMOVE);
				
				break;
				
			case REMOVE:
			
				actionTemp = new ActionCommand(positionOne, photo, ActionType.ADD);
				
				break;
		
			case MOVE:
			
				actionTemp = new ActionCommand(positionOne, positionTwo, ActionType.MOVE);
				
				break;
	
			case SWAP:
				
				actionTemp = new ActionCommand(positionOne, positionTwo, ActionType.SWAP);
				
				break;

		}
		
		return actionTemp;

	}
	
}