

/**
*@author Yeabkal Biru, 
*@author Eiman Rana,
*@author John MWai
*@since 5/13/2020
**Date 5/02/2020
*This program will serve as a user interface using the terminal. it will display the whole program on the terminal
*and also helps us get information from the user on the terminal. 
*it also helps us open, read and write on files and get the current date
*this class is the main class in which we do most of the operations and data manipulation while also using the other classes
*/




import java.util.Scanner;  // Import the Scanner class
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // 
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.text.ParseException;
import java.io.FileWriter;
import java.io.IOException;


public class Main{
	public static void main(String[] args){
		int m=0;							//this will help the user to keep trying to log in or redirect the user to creat an account in case of not having an account 
		int num=0;
		while(true){
			
			if(m==0){						//check to see if m is 0 them proceed to the main menu
				
				//prompt the user to enter one of the 3 numbers on the main menu here
				Scanner mainMenuNumber=new Scanner(System.in);	
				System.out.print("......................................................................\nWelcome! if you want to proceed, choose one of the following. Thank you! \n"+" "+"1.Login \n 2.signup \n 3.exit\n");
				num=mainMenuNumber.nextInt(); // Read user input
			}
			
			if(num==3){			//if the user chooses 3 or exit then quit the program
				break;
			}


			if(num==1){				//if the user chooses 1 or login then proceed to the login section
				String userName;	//initiate the user name holder that we will store prompting the user
				String [] arr;		//initiate the array we will use to store evey line in the user profile file as indivisual elements
				int signUpChoice;	//initiate this variable which we will store the decision of the user to try different user names or proceed into cresting an account 
				
				while(true){
					Scanner userID=new Scanner(System.in);		//prompt the user for a user name
					System.out.print("............................\nplease enter your User Name: ");
					userName=userID.nextLine();
					//read the file and get the name and password
					arr=new String[7];			//make the size of the array to be 7 because thee are only 7 lines in the profile file of the user
					

					try {		//put the lines in a try catch to help catch an error if there is any
						
						//read the file of the user using the user name as a file name
						File myObj = new File(userName+".txt");
	      				Scanner myReader = new Scanner(myObj);
	      				int i=0;
	      				while (myReader.hasNextLine()) {
	      					String data = myReader.nextLine();
	        				arr[i]=data;			//assign each line of the file to the array we created
	        				i++;
	        			}
	        			myReader.close();
	        			break;
	        		} catch (FileNotFoundException e) {					//catch the error if there is no file to be found with the provided information
	      				Scanner loopQuiter=new Scanner(System.in);
						System.out.println("..................................\nThe User Name was not found!\n1. try again with a different userName\n2. Sign Up to create a new account.");
						signUpChoice=loopQuiter.nextInt();
						if(signUpChoice==2){						//if the user chooese to proceed and creat an account then break from the loop and assign m to be 1
							m=1;
							break;

						}
	      				
	      			}
      			}

      			if(m==1){			//if m equals to 1 then proceed directly to the signup page
      				num=2;
      			}else{




	      			String password=arr[2];			//get the password from the array with the index number 2
	      			
	      			String name=arr[0];				//get the full name from the array with the index number 0

	      			
	     
	      			String promtPassword;			
	      			while(true){
					
						
						Scanner passwordReading=new Scanner(System.in);			//prompt for the Password from the user
						System.out.print("please enter your password: ");
						promtPassword=passwordReading.nextLine();
						
						if(promtPassword.equals(password)){			/*if the pasword entered by the user matches the passsword from 
																	he file then break out of the loop and approve logging in*/
							break;
						}
						System.out.print("\nthe password your entered is not correct please try again \n");

					

					}
					System.out.println("\n\n\n\n\n\n\n\n................................\nHello! and Welcome!,"+" "+name+"\nyou have succesfully logged in to your account, we have missed you!");

					
					MySocialProfile socialProfile=new MySocialProfile(); //creat a mySocialProfile object
					
					
					String friends=arr[6];			//assign the list of the email adress of the friends to a string object
					
					String[] friendsArr=friends.split(",");				/*split the string containing all of the email adress of the freind into 
																		differnet parts depending on the presence of ","*/
					for(int i=0;i<friendsArr.length;i++){
						socialProfile.addFriend(friendsArr[i]);				//add all of the friends from the file into the MySocialProfile object
					}

					
					String timeline=arr[5];								//assign the previos posts into a string object
					
					String[] timelineArr=timeline.split(",");			//split the timline string where there is a ","
					for(int i=0;i<timelineArr.length;i++)
						socialProfile.addPost(timelineArr[i]);			//add all of the previous posts from the file into the MySocialProfile object
					
					

					String events=arr[4];							//assign the events to a strig object
					
					String[] parts=events.split(",");				//split the string where there is a ","


					String totalPosts=arr[5];						//assign the total number of posts to a string
					String totalFriends=arr[6];						//asssign the total number of email adress of the friends to a string

					int updateTrackerEvent=0;

		
					try {
						Date currentDate=new Date();				//get the current date from the computer
						SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
						timeFormat.format(currentDate);

						String sub=parts[parts.length-1].substring(0,19);							//get only the first 18 characters from each event which is equals to the date of each event
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
						Date lastDate=sdf.parse(sub);
						int nullPositionTracer=0;
						
						if ((currentDate.compareTo(lastDate))>0){					//if the date has already passsed then remove the events from the array
							System.out.println("....................\nall of the events on your event list have already passed so they have been automatically removed from your event list....................\n");
							for(int i=0;i<parts.length;i++){
								parts[i]=null;						//assign the specific indexes of the array that we removed the events from to null
							}
							nullPositionTracer=parts.length;				//update where the lowest index with null value is
							updateTrackerEvent=1;
						
						}else{
							int i=parts.length-2;					//if the date is not in the past then decrement i by 1




	    					while(true){
	    						if(i<0 || currentDate.compareTo(sdf.parse(parts[i].substring(0,19)))>0){		//if the date is in the past or the index is less than zero the break out of the loop
	    							break;
	    						}
	    		
	    						i--;
	    					}




							//if i is greater than zero and then that means all the events in the array starting from zero to i are in the past.

							if(i>0){
								System.out.println("........................................\nall of the events up to and including"+ " " + "\""+parts[i]+"\""+" " +"on your event list have already passed so they have been automatically removed from your event list");
								int f=0;
								int l=i;
								for(int j=l;j<parts.length-1;j++){
									parts[f]=parts[i+1];				//then shift the values that from i+1 to the last index then assign them to the indexes startig from 0 to i
									i++;
									f++;
								}
								l++;
								while(l<=parts.length-1){
									parts[l++]=null;				//then assign the indexes starting from i to the last index
								}
								nullPositionTracer=f;
								updateTrackerEvent=1;

							}else if(i==0 && currentDate.compareTo(sdf.parse(parts[i].substring(0,19)))>0){		//if the event found at the first index value of the array is is in the past then remove it
								System.out.println("........................................\nthe event found first on your event list, that is"+" "+"\""+parts[0]+"\""+" "+"have already passed so it has been automatically removed from your event list");
								int k=parts.length-1;
								int n=0;
								for(int j=0;j<parts.length-1;j++){
									parts[j]=parts[j+1];				//shift the values starting from the second index value by one 
									n++;
								}
								parts[parts.length-1]=null;			//assign the last index to null
								nullPositionTracer=parts.length-1;
								updateTrackerEvent=1;
							}else if(i<0 && currentDate.compareTo(sdf.parse(parts[0].substring(0,19)))<0){		//f the index value i is less than zero that means all of the events are in the future
								System.out.println("........................................\nall of your events in your event list are in the future. No missed event so far.");
								nullPositionTracer=parts.length;

							}
						}
					




						int eventSize= nullPositionTracer;			//assign the null postion tracer value to eventsize

						Event event=new Event(eventSize+1); //creat a PriorityQueue object
						for(int i=0;i<eventSize;i++){
							event.insert(parts[i]);					//insert the elements up to null(excluding null) from "parts" to the priority queue 
						}

						System.out.println("\nPress Enter to continue");
						try{
							System.in.read();
						}catch(Exception e){};


		

						String firstEvent=parts[0];  //get the nextEvent
						System.out.println("..............................................\n\n\n\n\n\n\n\n\nyour next event is:"+" "+firstEvent);	 //print the next event

						String firstThreePosts=socialProfile.returnRecentPost();	//get 3 recentTimelines
						System.out.println("...................................\nyour recent 3 posts are:"+" "+firstThreePosts);	//print the 3 recentTimelines

						
						System.out.println(".....................................\nyour upcoming events are:");
						for(int i=0;i<eventSize;i++){
							System.out.println(i+1+"."+" "+parts[i]);	//print all of the events in chrnological order		 
						}

						System.out.println("\n");

						
					

		
						int operationNumber;			//initiate operational number which is the input from the user
						int updateTrackerTimeline=0;	
						int updateTrackerFriends=0;
						while(true){
							//prompt the user for the number on the Homescreen to proceed to the different operations
							Scanner proceedingNumber=new Scanner(System.in);
							System.out.print(".................................................\nchoose one of the following and enter the numbers \n");
							System.out.println("\n\n 1.Post to Timeline \n 2.Add an event to event list \n 3.View list of friends \n 4.Add a new friend \n 5.Remove a friend \n 6. Log out");
							operationNumber=proceedingNumber.nextInt();
							if(operationNumber==6){		//if the user inputs 6 then log out 
								break;
							}

							if(operationNumber==1){		//if the user inputs 1 then proceed to the log in page
								
								Scanner newText=new Scanner(System.in);	//prompt the user to enter the post to be added to the timeline
								System.out.print("..........................................\n\nplease enter the text you want to post to your Timeline: ");
								String newPost=newText.nextLine();
								
								socialProfile.addPost(newPost);			//prompt the user to a new text to post into the timeline
								System.out.print("........................................\n\""+newPost+"\""+" "+"was succesfully added to your Timeline\n\n");
								updateTrackerTimeline=1;

							}else if(operationNumber==2){		//if the user inputs 2 then proceed into the signup page
								//prompt the user to enter only the activity with out the date
								Scanner newPlan=new Scanner(System.in);
								System.out.println("...................................\n\nenter the the activity(event)");
								String eventPlanOnly=newPlan.nextLine();

								String newEventDate;
	    						while(true){
	    							
									Scanner newPlanDate=new Scanner(System.in);		//prompt the user to enter the date of the new event to be added
									System.out.println(".............................................\nplaese enter the date of the new event you are trying to add in"+" "+"yyyy/MM/dd/hh/mm/ss form");
									newEventDate=newPlanDate.nextLine();
									SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
									Date newDate=sdf2.parse(newEventDate);
									Date currentDate2=new Date();					//get the current date
	    							SimpleDateFormat timeFormat2=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
	    							timeFormat2.format(currentDate2);

	    							if(currentDate2.compareTo(newDate)<0){			/*if the eevent we are putting is in the future contnue to the next part of the program 
	    																			but if the event date entered is in the past then prompt the user to enter another date again*/
	    								break;
	    							}
	    							
									System.out.println("..........................................................\n\nthe date you are trying to add is already in the past please try choosing another date \n");
									
									
	    						}
	    						String newEvent=newEventDate+" "+eventPlanOnly;				//put the event date and the event activity together and form the event as a whole
	    						
	    						event.extendArraySize();				//extend the array that the priority queue of the events is based on in order to fit the newely added event into the priorityb queue
								
								event.insert(newEvent);					//after extending the size of the priority queue then add the new event into it
								System.out.print("...............................................\n\n\n\n\n..............................................\n\n\""+newEvent+"\""+" "+"---has been succesfully added to your event list \n\n");
								updateTrackerEvent=1;

							}else if(operationNumber==3){			//if the user inputs 3 then view the list of the email adress of the friend 
								System.out.print(".............................................\nyour freinds are:" + socialProfile.listOfnames()+"\n");
							
							}else if(operationNumber==4){			//if the user inputs 4 then proceed to the new friend adding page
								
								Scanner newFreindPrompt=new Scanner(System.in);		//prompt the user for the name of the friend to be added
								System.out.print("..............................................\nenter the email adress of the new friend you want to add into your friends list");
								String newFreind=newFreindPrompt.nextLine();
								
								socialProfile.addFriend(newFreind);				//add the email adress of the new friend to the friend list
								System.out.println("...........................................................\n"+"\""+newFreind+"\""+" "+"has been succesfully added to your friends list\n");
								updateTrackerFriends=1;

							}else if(operationNumber==5){				//if the user inputs 5 then proceed to the friend removing page
								
								Scanner freindToBeRemoved=new Scanner(System.in);			//prompt the user to enter the name of the friend to be removed and let's call it noMoreFriend
								System.out.print("...........................................................\nenter the email adress of the friend to be removed\n\n");
								String noMoreFriend=freindToBeRemoved.nextLine();
								
								boolean b=socialProfile.removeFriend(noMoreFriend);				/*try to remove the friend from the list and get the boolean value to 
																								check if the email adress was actually found in the list*/
								
								if(b==false){			//if the email adress is not found in the list or the return value was false then notify the user that the email adress was not found
									System.out.println("..................................\n"+noMoreFriend+" "+"was not found in your friends list\n");
								}else{
									System.out.println("....................................\n"+noMoreFriend+" "+"was succesfully removed from your friends list\n");
								}
								updateTrackerFriends=1;

							}

						}
						Scanner sc = new Scanner(new File(userName+".txt"));				//depending on the usernme open the file of the user profile
						StringBuffer buffer = new StringBuffer();
						while (sc.hasNextLine()) {
		     				buffer.append(sc.nextLine()+System.lineSeparator());
		     			}
		     			String fileContents = buffer.toString();
		     			sc.close();
		     			String oldLine;													//initiate the old lines that going to be replaced
		  				
		  				String newLine;													//initiate a new line variable that will replace the old line

						if(updateTrackerTimeline==1){							//if there was actually changes been made to the timeline 					
							
							oldLine =totalPosts;								//get the old timeline
		  					
		  					newLine =socialProfile.returnAllPosts();			//get the new altered timelines
		  					
		  					fileContents = fileContents.replaceAll(oldLine, newLine);		//replace the old line of the old timline with the new timeline information 
		  					FileWriter writer = new FileWriter(userName+".txt");
		  					writer.append(fileContents);									//write the new information to the file
		  					writer.flush();

						}else if(updateTrackerEvent==1){					/*if there was an alteration that weas made to the event list by 
																			adding or removing events from the event list*/

							String newEventRep="";
							String eventLineReplacement;					//initiate the variable that is going to hold the new event
							if(event.size()==0){
								eventLineReplacement=newEventRep;
							}else{
								for(int i=1;i<=event.size();i++){
									newEventRep=newEventRep+event.extractMin()+",";				//assign the new altered event list to the already initiated object
			
								}
								eventLineReplacement=newEventRep+event.extractMin();
							}
							
						
		     				oldLine = events;							//get the old line
		  					newLine = eventLineReplacement;				//get the new  event
		  					
		  					fileContents = fileContents.replaceAll(oldLine, newLine);		//replace the old line with the new
		  					FileWriter writer = new FileWriter(userName+".txt");
		  					writer.append(fileContents);							//write the new altered information to the file of the user profile
		  					writer.flush();
		  				}else if(updateTrackerFriends==4 || updateTrackerFriends==5){		/*if there was some alteration made to the friends list then write 
		  																					the new altered information in place of the previous one*/

		  					oldLine =totalFriends;							//get the old line 
		  					newLine =socialProfile.listOfnames();				//get the new line
		  					fileContents = fileContents.replaceAll(oldLine, newLine);			//replace the old line with the new one
		  					FileWriter writer = new FileWriter(userName+".txt");
		  					writer.append(fileContents);										//write the new altered info into the file of the user profile
		  					writer.flush();

		  				}
						
					} catch(Exception e){
	    				System.out.println(e.getMessage());
	    			}
	    		}
	    	}
				
    					
    						
    					
    					
    				
    				
    				
         			
      				


					
	    	//below this starts the sign up page
	
				
			if(num==2){									//if the user input 2 on the main menu page
				Scanner addName=new Scanner(System.in);	//get the name up the user
				System.out.println("\n\n\n..............................................\nwe are extremely delighted on your decision to join our communinty, follow the next steps to create your account.\n\nplease enter your full name:");
				String signUpName=addName.nextLine();

				Scanner email=new Scanner(System.in);					//get the email adress of the user
				System.out.println("\nenter your email adress: ");
				String emailAdress=email.nextLine();





				String signUpPassword;					//initiate the object that we are going to store the password the user is going to choose
				while(true){
					Scanner addPassword=new Scanner(System.in);   		//get the password the user chooses
					System.out.println("\nplease enter a password of your choice");
					signUpPassword=addPassword.nextLine();
					int len=signUpPassword.length();					//get the length of the password the user chooses
					
					if(len>6){				//if the password is more than break
						break;
					}else if(len<=6){		//if the length is less than 6 then prompt the user another password with more than 6 characters
						System.out.println("\n.....................................................\nThe length of the password should be greater than six, please try again!");
					
					}
					if(signUpPassword.equals("") || signUpPassword.equals(" ")){			//if the user enters a blank space as a password then prompt the user to enter another password
						System.out.println("\nYou can't leave your password space blank, please put something!");
					}


				}




				int makingSure;				//initiate the variable that we will ask the user wants to keep trying to verify a password ot maybe change another password
				while(true){
					Scanner addPasswordVerify=new Scanner(System.in);	//prompt the user to verify the password
					System.out.println("please verify your password");
					String signUpPasswordVerify=addPasswordVerify.nextLine();
					if(signUpPasswordVerify.equals(signUpPassword)){
						makingSure=1;
						break;			
					}
					System.out.println(".......................................\n\nthe password you entered didint match\n");
					
					Scanner decideToContinue=new Scanner(System.in);
					System.out.println("1. if you wanna try verifying again\n2. if you wanna Exit\n\n");
					int decision=decideToContinue.nextInt();

					if(decision==2){		//if the user chooses to quite the keep trying to verify the password then break out of the while loop
						makingSure=0;
						break;
					}


				}

				if(makingSure==0){
					break;
				}

				String signUpUserName;		//initiate the variable we will store the username from the user
				while(true){
					Scanner addUserName=new Scanner(System.in);			//get the user name 
					System.out.println("please choose a User Name");
					signUpUserName=addUserName.nextLine();
					try {
			     		File myObj = new File(signUpUserName+".txt");			//create a file with the username
			      		if (myObj.createNewFile()) {
			      			break;
			        		//System.out.println("File created: " + myObj.getName());
			        	} else {
			        		System.out.println("UserName already exists please try another one.");		//if a file with that name or the user name already exisits then prompt for another 
				
			        	}
					
					} catch (IOException e) {
			      		System.out.println("An error occurred.");
			      		e.printStackTrace();
			    }


				}	







				System.out.println("\n........................................................................\nCongradulations! you have succesfully created an account\nWelcome to this plateform" +" "+signUpName+" "+"\n\nNow let's customize your profile");

				try {
					FileWriter myWriter = new FileWriter(signUpUserName+".txt");

					myWriter.write(signUpName+"\n");			//write the name of the user to the file

					myWriter.write(emailAdress+"\n");			//write the email adress to the file 

					myWriter.write(signUpPassword+"\n");		//write the password to the file


					Scanner cYear=new Scanner(System.in);
					System.out.println("what is your class year?\n");
					String classYear=cYear.nextLine();

					myWriter.write(classYear+"\n");			//write the class year of the user to the file

					

					Scanner signUpPlan=new Scanner(System.in);		//get the events the user is planning to attend 
					System.out.println("......................................................\nplease enter the events your are anticipating in the future separated by a comma\nexample--- yyyy/MM/dd/hh/mm/ss going to church,yyyy/MM/dd/hh/mm/ss Work out \n");
					String signUpEvent=signUpPlan.nextLine();

					myWriter.write(signUpEvent+"\n");				//write the new events to the file

					Scanner signUpPost=new Scanner(System.in);		//get a new post from the user 
					System.out.println("\n\nplease enter things you wish to post on your timeline separated by a comma\nexample:\nAfrica on the rising...,COVID 19 effect on developing countries...,\n");
					String signUpTimeline=signUpPost.nextLine();

					myWriter.write(signUpTimeline+"\n");			//write the new post to the file

					Scanner signUPFriend=new Scanner(System.in);		//get the email adress of the people we want to add as a friend
					System.out.println("\nplease enter the email adress of the people you want to add as friends separatred by a comma:");
					String signUpAddFriend=signUPFriend.nextLine();

					myWriter.write(signUpAddFriend+"\n");		//write the email adresses on the file

					myWriter.close();			



				} catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }

			    System.out.println("\n\n\n\n...................................................................\nyou are done, thank you very much for joining our community! Enjoy!");
			    
			    System.out.println("Press Enter to continue");
				try{
					System.in.read();
				}catch(Exception e){};


			}
			m=0;			//update m to be 0 so when the program goes back to the main menu it'll kmow what to do
		
		}

		
		
		

	}
}


