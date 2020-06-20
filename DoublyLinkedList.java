/**
*@author Eiman Rana,
*@author Yeabkal Biru, 
*@author John MWai
*@since 5/13/2020
* The node class for a DoublyLinkedList class
* The DoublyLinkedList class, holds the list of DNodes
* The DNode stores a name of the user 
*/

public class DoublyLinkedList{
	//Instance Variables
	public DNode head;		//The head sentinel DNode
	public DNode tail;		//The trail sentinel DNode
	public int size;		//The number of nodes in the list

	//constructor method
	
	public DoublyLinkedList(){

	}

	//ACCESS METHODS

	/**
	*@return returns true if the list is empty
	*/

	public boolean isEmpty(){
		return (head==null && tail==null);
	}	

	/**
	*@return returns the number of nodew of the list
	*/

	public int sizeIs(){
		return size;
	}

	/**
	*Adds the name to the front of the list
	*@param newData this is the data to be stored at a node
	*/

	public void insertFirst(String newData){ 
		DNode current=new DNode(newData);      //creates a new node with the data newDat
		if (isEmpty()){							//checks if the list is empty, 
			head=current;
			tail=current;
			current.next=null;					//sets previous and next nodes to be null
			current.previous=null;
			size++;								//we increase the size

		}else{								//elses it sets the head to be second data in the list 
			current.next=head;
			head.previous=current;			//and current data to be head 
			head=current;
			size++;							//we icrease the size
		}
	}

	/**
	*Adds the name to the end of the list
	*@param newData , data to be stored at the node
	*/

	public void insertLast(String newData){
		DNode current=new DNode(newData);				//creates a node that stores the data newData
		if(isEmpty()){									//checks if the list is empty, 
			head=current;								//sets previous and next nodes to be null
			tail=current;
			current.next=null;
			current.previous=null;
			size++;										//we increase the size
		}else{											//elses it sets the tail to be second last data in the list
			current.next=null;
			current.previous=tail;
			tail.next=current;
			tail=current;								//and current data to be tail
			size++;										//increase the size
		}
	}

	/**
	*@return returns true if the list is empty
	*@return returns true if the name in the node matches the key 
	*otherwise, it returns false
	*@param key this is the parameter passed to be checked if it matches the name stored at a node
	*it the deletes the node if the key if found
	*/	

	public boolean deleteNode(String key){
		DNode current=head; 				//assign current to head node      
		if(isEmpty()){						//checks if it is empty	
			return(isEmpty());

		}else{
			while(current!=null){			//continues if  only the next pointer does not point to null
				if(sizeIs()==1){			//continues if only size is 1
					if(current.getElement().equals(key)){		//checks if tha data stored stored at this node matches the key
						head=null;								//sets head and tail to null and reduces size
						tail=null;
						size--;
						return true;
					}else{					
						return false;						//key does not match with data 
					}
				}else{										//continues if size is >1
					if(current.getElement().equals(key)){  //checks of the key matches the data, 
						if (current.next==null){
							tail = current.previous;   //sets the previous node to null
							tail.next=null;         //and tail pointer to null
						}else{
							current.previous.next=current.next;					//removes the middle node by connecting the the previous node to the next node
							current.next.previous=current.previous;

						}
						
						size--;   //reduces size
						return true;
					}else{
						current=current.next;    //goes to the next node
					}


				}

			}
			return false;   //means the key was not found
		}

	}

	/**
	*@return returns the names stored at the nodes 
	*/

	public String returnName(){
		DNode current=head;      //assigns head to current head
		String nameList="";		//creates an empty list
		while(current!=null){
			nameList=nameList+ "\n" + current.getElement();    //adds the names to the nameList
			current=current.next;   //accesses the next node 
			
		}
		return nameList;		//returns the list containing the data 
	}

	/**
	*@return returns the data stored at the node ,
	*this will eventually be used to return the 3 most resent timeline posts, thats why we start from the tail 
	*/	

	public String returnPost(){
		DNode current=tail;			//sets current node to tail
		String timeline="";      	//creates an empty list
		int num = 1;					//creates an integer num that numbers the data
		while(true){
			if(current.previous == null){   //checks if the previous node is null
				break;    //breaks the while loop
			}
			timeline = timeline+ "\n" + num +"."+current.getElement();   //add timeline data to the list
			current = current.previous;			//sets current to previous
			num++;    //increase nums
		}
		timeline = timeline+ "\n" + num +"."+current.getElement();    //adds data stored at the head to the timeline 
		return timeline;
	}

	/**
	*@return timeline, returns the total timeline posts
	*We start from the head and loop through the entire nodes collecting data from the nodes
	*/

	public String returnTotalPost(){
		DNode current=head;						//sets the head to the current node
		String timeline="";						//creates an empty list
		int num = 1;
		while(current.next!=null){				//loops as long the list is not empty
			timeline=timeline+current.getElement()+",";    //adds the posts to the list
			current=current.next;    //sets current to next node in the list
			num++;
		}
		num = num+1;
		timeline = timeline+current.getElement();   //adds the last item in tail
		return timeline;      //returns the list storing the data
	}

}