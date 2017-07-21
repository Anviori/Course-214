import java.io.*;

public class CoffeeOrderManager {
	
	/**
	 * [Employs a loop that prompts the user to make
	 * a choice based on the menu options given. The loop exits
	 * when the user chooses the Quit option.]
	 * 
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {

		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);
		
		OrderList barista1 = new OrderList();
		OrderList barista2 = new OrderList();
		
		OrderListNode orderStored = null;
		
		System.out.println("--------------------------------\n"
				+ "  Welcome to Star Duck Coffee!\n"
				+ "--------------------------------\n");

		while(true) {
			
			System.out.print("------------------------------\nMenu:\n\n  O: Order\n"
					+ "  P: Print Order Lists\n"
					+ "  C: Cursor\n  Q: Quit\n"
					+ "\n------------------------------\n");
			
			System.out.print("\nEnter option: ");
			String choice = input.readLine().toUpperCase();
					
			switch(choice) {
			
				case "O":
					
					System.out.print("\nEnter drink name: ");
					String name = input.readLine();
					
					System.out.print("Enter special request: ");
					String instructions = input.readLine();
					
					System.out.print("Enter price of drink: ");
					String price1 = input.readLine();
					Double price = Double.parseDouble(price1);
					
					Order order = new Order(name, instructions, price);
					
					System.out.print("\nSelect Barisita (1 or 2): ");
					String baristaa = input.readLine();
					int barista = Integer.parseInt(baristaa);
	
						System.out.println("\n----------------------------------\n "
								+ "Where should the order be added?\n"
								+ "----------------------------------\n"
								+ "\n----------------------------------------------\n"
								+ "F: Front of List\nB: Back of List\nA: After Cursor"
								+ "\nS: After Similar Order (default: end of list)"
								+ "\n----------------------------------------------");
						
						System.out.print("\nEnter option: ");
						String choice1 = input.readLine().toUpperCase();
						System.out.println();
						
						switch(choice1) {
							
							case "F" :
								
								try {
									
									if(barista == 1)
										barista1.insertNewHead(order);
									else if(barista == 2)	
										barista2.insertNewHead(order);
								
								} catch (IllegalArgumentException e) {
									
									System.out.println("\nOrder is null!\n");
						
								}
							
								break;
							case "B" :
								
								try {
									
									if(barista == 1)
										barista1.appendToTail(order);
									else if(barista == 2)	
										barista2.appendToTail(order);
								
								} catch (IllegalArgumentException e) {
									
									System.out.println("\nOrder is null!\n");
						
								}
								
								break;
							
							case "A" :
								
								try {
									
									if(barista == 1)
										barista1.insertAfterCursor(order);
									else if(barista == 2)	
										barista2.insertAfterCursor(order);
								
								} catch (IllegalArgumentException e) {
									
									System.out.println("\nOrder is null!\n");
						
								}
								
								break;
							
							case "S" :
							
								try {
									
									if(barista == 1)
										barista1.AfterSimilarOrder(order);
									else if(barista == 2)	
										barista2.AfterSimilarOrder(order);
								
								} catch (IllegalArgumentException e) {
									
									System.out.println("\nOrder is null!\n");
						
								}
								
								break;
					
						}
						
					break;
					
				case "P":
					
					System.out.print("\n------------------------------------------"
							+ "----------------------------------------");
					System.out.print("\n[Barista 1]");
					System.out.print("\n" + barista1);
					System.out.print("\n------------------------------------------"
							+ "----------------------------------------\n");
					
					System.out.print("\n------------------------------------------"
							+ "----------------------------------------");
					System.out.print("\n[Barista 2]");
					System.out.print("\n" + barista2);
					System.out.print("\n------------------------------------------"
							+ "----------------------------------------\n\n");
					
					break;
					
				case "C":
					
					System.out.print("\nSelect a cursor (1 or 2): ");
					String choicee = input.readLine();
					int cursor = Integer.parseInt(choicee);
					
					System.out.println("\n----------------------------------\n "
							+ "Cursor Options: \n"
							+ "----------------------------------\n"
							+ "\n----------------------------------------------\n"
							+ "F: Move Forward\nB: Move Backward\nH: Reset To Head"
							+ "\nT: Move to Tail\nR: Remove\nC: Cut\nP: Paste"
							+ "\n----------------------------------------------");
					
					System.out.print("\nEnter option: ");
					String choice2 = input.readLine().toUpperCase();
					System.out.println();
					
					switch(choice2) {
					
					case "F" :
						
						try {
							
							if(cursor == 1) 
								barista1.cursorForward();
							else if(cursor == 2) 
								barista2.cursorForward();
							
						} catch(EndOfListException e) {
							
							System.out.println("\nCursor is at the tail of the list!\n");
							
						}
						
						System.out.println("Cursor has moved forward.\n");
						
						break;
					case "B" :
						
						try {
							
							if(cursor == 1) 
								barista1.cursorBackward();
							else if(cursor == 2) 
								barista2.cursorBackward();
							
						} catch(EndOfListException e) {
							
							System.out.println("\nCursor is at the head of the list!\n");
							
						}
						
						System.out.println("Cursor has moved Backward.\n");
						
						break;
					
					case "H" :
						
						if(cursor == 1) 
							barista1.resetCursorToHead();
						else if(cursor == 2) 
							barista2.resetCursorToHead();
						
						System.out.println("Cursor reset to head.\n");
						
						break;
					
					case "T" :
					
						if(cursor == 1) 
							barista1.resetCursorToTail();
						else if(cursor == 2) 
							barista2.resetCursorToTail();
						
						System.out.println("Cursor moved to tail.\n");
						
						break;
					
					case "R" :
					
						try {
							
							if(cursor == 1) 
								barista1.removeCursor();
							else if(cursor == 2) 
								barista2.removeCursor();
							
						} catch(EndOfListException e) {
							
							System.out.println("\nCursor is null!\n");
							
						}
						
						System.out.println("Order removed.\n");
						
						break;
						
					case "C" :
					
						try {
							
							if(cursor == 1) 
								orderStored = barista1.cut();
							else if(cursor == 2) 
								orderStored = barista2.cut();
							
						} catch(EndOfListException e) {
							
							System.out.println("\nCursor is null!\n");
							
						}
						
						break;	
					
					case "P" :
						
						try {
							
							if(cursor == 1) 
								barista1.paste(orderStored);
							else if(cursor == 2) 
								barista2.paste(orderStored);
							
						} catch(EndOfListException e) {
							
							System.out.println("\nCursor is null!\n");
							
						}

						break;
						
					}
					
					break;
					
				case "Q":
					
					System.out.println("\nGoodbye! Come Again!");
					System.exit(0);
					
				default:
					
					System.out.println("\nInvalid Input. Try Again.\n");
					
					break;
					
			}
			
		}
		
	}
	
}