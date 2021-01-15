/**
*@author Yeabkal Biru
*@since 5/13/2020
* The node class for a DoublyLinkedList class
* The DNode stores a name of the user 
*/
public class DNode{
	public String name;    		//Reference to the name stored at this node
	public DNode next;			//Reference to the successor node in the list
	public DNode previous;		//Reference to the predecessor node in the list
	
	//The Constructor method

	public DNode(String data){
		name=data;
	}

	//ACCESS METHODS

	/**
	*@return returns the name of the user
	*/

	public String getElement(){
		return name;

	}

	/**
	*This diaplys the name of the user
	*/

	public void displayLink(){
		System.out.println(name + "");
	}
	
}
