/**
*@author Yeabkal Biru,
*@author Eiman Rana, 
*@author John MWai
*@since 5/13/2020
*This class will hold the user's account details, events, friends, and timeline posts
*the program will be able to add and remove friends, add and return a list posts 
*/

public class MySocialProfile{
	DoublyLinkedList freindList = new DoublyLinkedList();
	DoublyLinkedList timeLineList = new DoublyLinkedList();


	//constructor method

	public MySocialProfile(){

	}

	//accessor methods

	/**
	*this method adds a friend into the friends list
	*@param name , adds the name of friend to the list of friends 
	*/

	public void addFriend(String name){
		freindList.insertLast(name);        //adds using the DoublyLinkedList class

	}

	/**
	*@return this method returns true if a friend is removed from the list or false otherwise
	*@param name, removes the name of the friend to the list of friends*/

	public boolean removeFriend(String name){
		boolean existance = freindList.deleteNode(name);       //removes the friend by calling the DoublyLinkedList class
		if(existance == true){				//checks if true of false
			return true;  //friend removed successfully
		}
		else{
			return false;   //friend not yet removed
		}
	}

	/**
	*@return , returns the list of friends stored at the nodes 
	*/

	public String listOfnames(){
		String list=freindList.returnName();   //uses the DoublyLinkedList class by accessing the returnName mathod
		return list;
	}

	/**
	*This methods adds post to the timeline list
	*/

	public void addPost(String post){
		timeLineList.insertLast(post);
	}

	/**
	*@return , this method returns the recent  list of timeline posts posted 
	*/

	public String returnRecentPost(){
		String list=timeLineList.returnPost();
		return list;

		
	}

	/**
	*@return this returns all the timeline posts added to the timeline list
	*/
	
	public String returnAllPosts(){
		String allList=timeLineList.returnTotalPost();
		return allList;
	}
}
