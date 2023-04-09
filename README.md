# Sample_Blackboard_Android_Mobile_App
For AS1 Mobile Applications. Blackboard app with 10 logins, notes application (local sharedpreferences storage), calculator with GUI, simple quiz with file saving for answers, background colour change application, and random number generator



1.	Usernames and Passwords for all relevant accounts
JoyalJohn	21410308
NadielFassin	supersecurepasswordyes
DhalikOsemiCircle	thisismypassword
BobMarley	isthislove?
Emmanuel	Ofori
Walter	White
Dio	Brando
CRonaldo	CR7
KidCudi	mrRager

2.	Table of features implemented

Feature	Implemented (Partial/Full)	Comments
Fixed Username and Password Login	Full	
On each activity, the user’s information and details should be displayed at the top of the
application	Full	
File Storage for the students to save their notes	Partial	Done in RecyclerView, with storage entirely based on SharedPreferences
a. Allows a user to create a file.
i. The name of the file should be created with users’ name and the current date	Partial	Appears and stays in RecyclerView even when off the app and closing the app. Name and date is not implemented
b. The user should be able to update the name of the file	None	
c. The user should be able to delete the file	Full	
d. The user should be able to update the contents of the file	Partial	Bug when deleting, then creating a new file with the same name, the contents from the deleted note will reappear if not left empty when deleted beforehand
e. The user should be able to view all the files that have been created and display it in a
recycler view	Full	
Multiple Choice Quiz Application	Full	
a. The Quiz Choice Application needs to allow users to select the correct answer by
selecting the right radio button	Full	
b. The Quiz application needs to display the marks for the user	Full	Displays scores at the end and in the download of the file
c. The user should have the option of saving the score to a file	Full	
d. The file should contain the name of the user and the score from the quiz	Full	
Images and Videos into the Quiz Activity	Partial	2 simple imageviews that suit the theme of the quiz
GUI	Full	GUI is made to look appealing to most activities
Implement Activity lifecycle callbacks to the application	Partial	
The calculator application	Full	Simple calculator with a C button bug of displaying the wrong text when clearing an empty field
The change the background application	Partial	Only changes background of ChangeBackgroundActivity.java
The dice roller application	Partial	Rolls 2 numbers 1-6; very simple


I.	MainActivity.java and activity_main.xml

The layout for activity_main is a simple login, set with a color scheme of navy blue and white, in a LinearLayout.

For MainActivity.java, it contains the code that makes the login system function that is then vital for all other activities inside the app. The user enters their username and password, which are then compared to an ArrayList of predetermined login credentials. If the entered data matches a credential in the list, the user is redirected to the UserActivity, where they can access their account information. Otherwise, an error message is displayed. The main functionality of the app is implemented within the setOnClickListener method of the btnLogin Button, with if else statements that pass intents to the next activity as well as error checking. This method retrieves the input data from the EditText fields and compares them to the 10 login credentials in the ArrayList.

Overall, this code provides a simple and effective way to implement a login system in an Android application. However, for a production-level application, additional security measures, such as encryption of login credentials, as well as possibly have an external database to store logins, that could be implemented.


II.	UserActivity.java and activity_user.xml
The layout for activity_user.xml is a simple LinearLayout with a textview for the “Logged In as: username” function. Underneath is 5 buttons that take you to all the other activities in the app.

For UserActivity.java, it is a class that only displays the username of the user logged-in in terms of visible functionality, however the rest of the class if you look at the code are intents for the buttons to pass onto other activities. Each listener creates an intent for a different activity (NotesActivity, QuizActivity, CalculatorActivity, ChangeBackgroundActivity, and DiceActivity) and passes the username as an extra or other activities to display the username on top of the page.

III.	NotesActivity.java, DetailsActivity.java, MyAdapter.java, activity_notes.xml and sample.xml
The layouts, activity_notes.xml and sample.xml, are used in conjunction to display the recyclerview GUI within the NotesActivity class. Sample.xml contains the design for a card in the recyclerview, with a drawable vector asset, whilst activity_notes.xml contains basic GUI EditTexts and buttons to modify the notes.

In NotesActivity.java, the onCreate() method initializes the views and sets up the RecyclerView, which displays a list of notes. The addItem() and removeItem() methods are called when the user clicks on the corresponding buttons and add or remove notes from the list. The saveArray() and loadArray() methods are used to save and load the notes from SharedPreferences, which is an Android mechanism for storing key-value pairs of data.

MyAdapter.java is used to bind data to the RecyclerView. The class has methods to create a new instance of MyViewHolder which inflates the layout file, to bind data to each item in the RecyclerView and to get the count of items in the RecyclerView. The class also has a constructor that sets an OnClickListener for the TextView object which creates an Intent to navigate to DetailsActivity.java.

In DetailsActivity.java, the onCreate() method initializes various views and objects including an EditText view, a TextView view, and a SharedPreferences object. The title string value is retrieved from the intent's extra data using the "key" identifier. The saved text from SharedPreferences is retrieved using the SharedPreferences object and set to the EditText view. There’s also a private method, saveTextToPrefs() that saves the text entered in the EditText view into SharedPreferences. Activity lifecycle is implemented here where the onPause() method is overridden to call the saveTextToPrefs() method to save the entered text when the activity is paused. 

To extend or maintain the code, developers could consider adding new features, such as adding a search function, or improving the user interface.

IV.	QuizActivity.java, QuizEndActivity.java, activity_quiz.xml, and activity_quiz_end.xml
The layouts for the quiz are straightforward, following the same colour scheme as the rest of the site, with radio buttons and a submit button, as well as the username displayed on top of the activities. Once submitted all the questions, you would be taken to QuizEndActivity where it’s a button you can press if you would like your results to be downloaded into a file in the directory.

The QuizActivity contains a quiz with five multiple choice questions that the user can answer by selecting one of four options. There is also an image in the activity for decoration purposes, as well as a different one in QuizEndActivity.
The UI elements for the quiz, including a text view for the question, radio buttons for the answer options, and a submit button, are initialized and set up in the onCreate() method. The questions and answers for the quiz are stored in arrays, and the current score is kept track of using a variable. 
The setQuestionAndOptions() method is used to set the text of the question and answer options for each question. When the user submits their answer, the app checks if the selected option is correct and displays a message indicating whether it was correct or not. If the user has answered all questions, the app starts the QuizEndActivity through the use of intent to display the final score and end the quiz.

QuizEndActivity.java displays the final score of a quiz and allows the user to save their score to a file. The activity receives the score and username as extras in the Intent that launches it, and sets the corresponding TextViews to display this information. The "printButton" Button has a listener that writes the score and username to a file named "username_score.txt" in a directory named "QuizScores" in the app's private files directory. If the directory doesn't exist, it creates it. If the write operation fails, it displays an error message using a Toast. The activity also sets a "LoggedInAs" TextView to display the username passed in the Intent.
V.	CalculatorActivity.java and activity_calculator.xml
The GUI for the calculator in activity_calculator.xml consists of the logged in users display at the top, with a large text view below for displaying the result of calculations, and a smaller text view below it for displaying the input as it is being entered. The buttons for numbers, arithmetic operations, and other functions are arranged in two rows at the bottom of the screen in a purple background. The buttons are stylized using the Material Design components.

CalculatorActivity.java is a simple calculator application uses a combination of TextView and MaterialButton views to create a user interface for the calculator. The calculator logic is implemented using third-party library integration, (explained well and referring to the Mozilla Rhino JavaScript engine, which evaluates the calculation string entered by the user and returns the result. The onClick method is used to handle the button presses, and the getResult method is used to evaluate the calculation string and return the result.

VI.	ChangeBackgroundActivity.java and activity_change_background.xml
The layout includes a TextView with an ID of "set_username" to display the user's name, an EditText with an ID of "etColor" to input a color value, and a Button with an ID of "btnChangeBackground" to change the background color.

In the "onCreate" method, the activity initializes the USERNAME TextView by retrieving the username passed from the previous activity through an Intent. It also sets up the EditText, Button, and LinearLayout objects to interact with user input and update the background color. When the user clicks the "btnChangeBackground" button, the activity reads the text entered in the EditText and attempts to parse it as a color value. If the value is valid, the LinearLayout's background color is set to the entered value, and a toast message is displayed to confirm the change. If the value is invalid or empty, a different toast message is displayed to prompt the user to enter a valid color value.

VII.	DiceActivity.java and activity_dice.xml
Activity_dice.xml only contains the base whole assignment theme with the set_username TextView as well as two other TextViews that are used to display the numbers, stylized and in the middle of the page

DiceActivity.java is a simple dice number random generator by creating a Random object to generate the random numbers. The integers generated are then displayed in the TextView objects by converting them to strings using the Integer.toString() method.

Known Bugs In the System

UserActivity – none known
NotesActivity

•	Spaces also count as a “special character” so it doesn’t let you name anything with spaces
•	Other users who log in can access and modify the same files
•	When a removed item which had some text on already is created again when the same name is added, it saves the text before

QuizActivity – none known

QuizEndActivity – none known

CalculatorActivity

•	Clicking “C” when field is empty produces a third party error org.mozilla.javascript.Undefined@0

ChangeBackgroundActivity

•	A lot of colours are unrecognized, such as “orange, amber”

DiceActivity – none known
