/**
*@author Yeabkal Biru, 
*@author Eiman Rana,
*@author John MWai
*@since 5/13/2020
*This program will process a date and arramge and order the date based an chronologcal order,
*The first one would be the one that is in th  future then the ones next to it will be arranged on time based manner
*/

import java.util.Scanner;  // Import the Scanner class
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // 
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.text.ParseException;
import java.io.FileWriter;
import java.io.IOException;



public class Event {
	String [] A;		//initiate the array to hold the elements in the priority queue
	int n;				//initiate the size of the priority queue
	//boolean x;

	public Event(int capacity) {
		A = new String[capacity];			//creat the array with a capacity of the number of elements and in this case the number of elements
		n = 0;							//initiate the size with the value 0
	}

	/**
	*Return the size of the priority queue.
	*@return the size of the priority queue
	*/
	public int size() {
		return n;				//return the size of the priority queue
	}

	/**
	*Return true if the the priority queueu is empty and return false if it's not empty
	*@return true if the prioriry queue is true and false if it's not empty
	*/

	public boolean isEmpty() {
		return n == 0;				//return true if the elements is empty and false if it's non empty
	}

	/**
	*Return the minimum value in the priority queue
	*@return the value that is the minimum in the priority queue
	*/
	public String getMin() {		
		return A[1];				//return the first elememt in the priority queue
	}

	
	/**
	*insert a string value to the priority queue then change the format of the value to
	*date data type and compare it to the values that are already in the priority queue and then find it's place and place it there
	*/

	public void insert(String k) {
		try{							//put every thing in try and catch to be able to catch if there is an error specifically since we are going to use parse
			A[n+1] = k;	
			n++;					
			int cIndex = n;			//initiate the child index
			int pIndex = parentIndex(n);//initiate the parent index
			while(true){
				if(pIndex<1){		//if the parent index is less than 1 then break
					break;
				}
				String sub1=A[cIndex].substring(0,19);			//get the first 18 characters of the event string whcih are the dates that are found on the child index
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				Date fileDate1=sdf1.parse(sub1);

				Date fileDate2;
				String sub2=A[pIndex].substring(0,19);		//get the first 18 characters of the event string whcih are the dates that are found on the parent index
				SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				fileDate2=sdf2.parse(sub2);


				if(fileDate1.compareTo(fileDate2)>0){	//if the child date is less thn the parent date then break
					break;
				}
				swap(cIndex,pIndex);		//swap the child index with the parent index
				cIndex = pIndex;		//change the child index to the parent index
				pIndex = parentIndex(cIndex);		//get the new parent index



			}
			
			

		} catch(Exception e){
    		System.out.println(e.getMessage());
    	}
	}



	
	/**
	*get the minimum value in the priority queue and then return the value .
	*@return the minimum value then remove it from the priority queue
	*/

	public String extractMin() {
		swap(1,n);		//swap the first index with last index
		n--;
		int pIndex = 1;		//set the parent index to be 1
		int rCIndex = rightChildIndex(pIndex);			//get the right child index
		int lCIndex = leftChildIndex(pIndex);			//get the left child index 


		try{
			while(true){
				if(hasSmallerChild(pIndex)==false){			//if it doesnt have a smaller child then break
					break;
				}
				

				String sub1=A[lCIndex].substring(0,19);			//get the first 18 characters of the event string whcih are the dates that are found on the left child index
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				Date fileDate1=sdf1.parse(sub1);

				String sub2=A[rCIndex].substring(0,19);				//get the first 18 characters of the event string whcih are the dates that are found on the right child index
				SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				Date fileDate2=sdf2.parse(sub2);

				String sub3=A[pIndex].substring(0,19);				//get the first 18 characters of the event string whcih are the dates that are found on the parent index
				SimpleDateFormat sdf3=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				Date fileDate3=sdf3.parse(sub3);
				if(rCIndex>n){				//if right child index is greater than n 
					swap(pIndex,lCIndex);		//swap the parent index with the left child index
					pIndex=lCIndex;				//set the parent index to be left child index value
				}else{
					if(fileDate1.compareTo(fileDate2)<0){		//if the left child index is less than rigt child index 
						swap(pIndex,lCIndex);					//swap the parent index with the left child index 
						pIndex=lCIndex;						//set the parent index to be the left child index
					}else{
						swap(pIndex,rCIndex);					//swap the parent index to the right child and index
						pIndex=rCIndex;							//set parent index to right child index 
					}
				}
				rCIndex = rightChildIndex(pIndex);				//get the rihgt child index 
				lCIndex = leftChildIndex(pIndex);				//get the left child index
			

			}
		} catch(Exception e){
    		System.out.println(e.getMessage());
    	}

		return A[n+1];							//return the value that is being removed from the priority queue which is the min value 
		
	}

/**
	*Return the parent index after getting the child index as a parameter.
	*@return the parent index
	*/
	private int parentIndex(int cIndex) {
		return cIndex/2;					//divide the child index by 2
	}
	

	/**
	*Return the left child index after getting the parent index as a parameter.
	*@return the left child index
	*/
	private int leftChildIndex(int pIndex) {
		return pIndex * 2;						//multiply the parent index by 2
	}


	/**
	*Return the right child index after getting the parent index as a parameter.
	*@return the right child index
	*/
	private int rightChildIndex(int pIndex) {
		return (pIndex * 2) + 1;					//multiply the parent index by 2 then add 1 to it
	}


	/**
	*this methos will swap the value at the parent index with the value at the child index.
	*/
	private void swap(int pIndex, int cIndex) {
		String temp = A[cIndex];					//store the value at the child index 
		A[cIndex] = A[pIndex];						//set the value at thweb chld index to be equal to the parent index
		A[pIndex] = temp;							//set the value at the parent index to be equal to the child index
	}

	

	/**
	*Return true if the psrent index that was passed as paramter have a child.
	*@return true if the parent index that was passes as parameter posses a child and return false if not
	*/
	private boolean hasSmallerChild(int pIndex) {
		int rCIndex = rightChildIndex(pIndex);
		int lCIndex = leftChildIndex(pIndex);
		
		if(rCIndex > n && lCIndex>n){			//if the right child index is greater than n and left child index greater than n then return false
			return false;
		
		
		
		}else if(rCIndex>n && lCIndex<n){					//if the right child index greater than n and left child index is greater than n 
			String sub1=A[lCIndex].substring(0,19);			//get the first 18 characters of the event string whcih are the dates that are found on the left child index
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			Date fileDate1=new Date();

			String sub3=A[pIndex].substring(0,19);		//get the first 18 characters of the event string whcih are the dates that are found on the parent index
			SimpleDateFormat sdf3=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			Date fileDate3=new Date();

			try{ 
				fileDate1=sdf1.parse(sub1);		//parse the characters and change it to a date format
				fileDate3=sdf3.parse(sub3);		//parse the characters and change it to the date format
			} catch(Exception e){
    			System.out.println(e.getMessage());
    		}

    		if (fileDate1.compareTo(fileDate3)<0){		//if the left child date is less than the parent date return true other wise return false
				return true;
			}else{
				return false;
			}
		
			
		}else if(rCIndex<=n && lCIndex<=n){				
			String sub1=A[lCIndex].substring(0,19);						//get the first 18 characters of the event string whcih are the dates that are found on the lesft child index
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			Date fileDate1=new Date();

			String sub2=A[rCIndex].substring(0,19);						//get the first 18 characters of the event string whcih are the dates that are found on the right child index
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			Date fileDate2=new Date();

			String sub3=A[pIndex].substring(0,19);						//get the first 18 characters of the event string whcih are the dates that are found on the parent index
			SimpleDateFormat sdf3=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
			Date fileDate3=new Date();

			try{ 
				fileDate1=sdf1.parse(sub1);						//parse the characters and change it to a date format
				fileDate2=sdf2.parse(sub2);						//parse the characters and change it to the date format
				fileDate3=sdf3.parse(sub3);						//parse the characters and change it to the date format
			} catch(Exception e){
    			System.out.println(e.getMessage());
    		}


    		if(fileDate1.compareTo(fileDate3)<0){		//if the left child date is less than the parent date return true other wise return false
				return true;
			}else if (fileDate2.compareTo(fileDate3)<0){	//if the right child date is less than the parent date return true other wise return false
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		

	}

	/**
	*this method will extend the array size byb 1 when ever we call it in the main class.
	*the increment of the array will basically increase the size of the priority queue 
	*since the priority queue is an array based priority queue
	*/

	public void extendArraySize(){
		String [] temp=A;				//store the array in a temp variable
		A=new String[temp.length+1];	//creat a new array with a size that is one more than the previous array
		for(int i=1;i<temp.length;i++){
			A[i]=temp[i];				//go through the array and put the value at each index to the new array
		}
		temp=null;						//set the temp array to null to prevent it from taking our memory space
	}

	

	
}
	




