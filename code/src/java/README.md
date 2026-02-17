# POKEQUIZ!
Here's the explanation of how I structured and build the game. So let's get started! :D

### PokeApplication
I define a class called PokeApplication to launch the game and show its home window. 

Specifically, inside the method `start()` I load the graphic user interface that I created with Scene Builder and I defined in the FXML file poke-view.fxml. 
With this file I create a new Scene which will contain all the GUI components I used and I link it to a Stage to be shown. Now the home window will be displayed.


### PokeController
This class controls all the actions that you can do in the home window.

The first thing I coded is how to get and treat the name given by the player: before starting the quiz the gamer should insert his name in the TextField I put on the bottom left of the window and click the button "Done!". 
When this button is clicked the name given is saved in a variable that I will use later to update the chart. If no name is given a random one is created.
There are three other buttons displayed:
- PLAY NOW! --> If pressed, the home window is closed and the quiz one is loaded and shown to start the game. The code used it's almost the same written in the PokeApplication.
- RULES --> If pressed, the home window is closed and the one listing all the rules to follow is loaded and shown. Same code as the PokeApplication.
- EXIT --> If pressed, it shows a WARNING alert, asking the player if he/she really wants to exit the game. The OK button closes the window and stops the application whereas the CANCEL button returns back.

To know when one of all this buttons is pressed and execute the right next actions I used and the same code: the method `SetOnAction()` links the button on which I call it to an event handler **ActionEvent**. 
The interface `EventHandler<T>` has just one method, _handle()_, which I override with the action to be executed when this button is pressed.


### RulesController
This class controls all the actions you can do in the rules window.

Once the RULES button is pressed this new window will get displayed. Il contains a TextArea with all the rules of the game and two more buttons:
- GO BACK --> If pressed, the rules window closes and reopens the home window.
- START NOW --> If pressed, the rules window closes and opens the quiz window to start the game.

The buttons pressed are treated like the ones before (`SetOnAction()` and `EventHandler<T>`).


### QuizController
This is the class that coordinates the quiz game.

This window is reached from the home window or from the rules window. There's a TextFiled that shows the current question and there are four buttons with four options, just one of them is the correct one. At the top left there's a countdown with the time left to answer.

The first method coded is `loadNextQuestion()`: it controls the time counter and displays the next question after the player gives an answer or the time runs out. 
For the time counter I use the **TimeLine** class with its methods. Every second the counter decreases of 1 unit and displays the new seconds left to the gamer and if it reaches 0 seconds the counter for the wrong answers goes up and the next question is shown. 
Before this method I created another variable for counting how many questions are left to end the game. Everytime the time runs out or the player answers this counter increases of 1 unit and has to be checked: if it's equal to 10, which is the number of questions I chose for this game, the game has ended otherwise it goes on.

The second part of this method contains the questions. I use a switch to check which question along with its options has to be displayed to the gamer based on the value of the specific counter. 

The method `checkAnswer()` uses another switch which contains the correct answers to the current question shown. This method is called whenever the player presses the button containing his answer.

Then there are four methods which make the same actions: each option shown to the player is a button that is linked to one of this method. Whenever one of them is clicked (this action is passed as an **ActionEvent** parameter) its method activates and executes its code.  
It checks if the option chosen is the correct one: if it is the counter _correct_ increases otherwise the counter _wrong_ goes up. The current time counter is stopped and it's checked the question counter: if there's another question to be displayed the game goes on and call `loadNextQuestion()` otherwise il call `showResult()`;

The last method `showResult()` just closes this window and opens the result one. The code it's the same already explained before.


### ResultController
This is the class that shows the result window.

The page displays the score and two diagrams showing the percentage of how many correct and wrong answers were given by the current player. There are two more buttons, one to exit the game (THE END) and one to make the chart visible (CHECK CHART).

At first inside this class I create new table inside the database (if it doesn't exist otherwise it does nothing) and then I add the new gamer who's just played the quiz. 
To update the table I use the name that the gamer gave me at the beginning (or the random one) inside the variable _nameGamer_ inside PokeController and the number of correct answers given inside the variable _correct_ in QuizController.
The action of pressing the buttons is treated as I did before for the others button, using `setOnAction()` and an **event handler**.
If CHECK CHART is pressed the information about the updated chart are loaded from the database and inserted into the two columns of the TableView that displays the graphic representation of the chart.

To create the diagrams I use two ProgressIndicator and the variables about the answers in QuizController (_correct_ and _wrong_ variables). Based on their real value or divided for 10 I update the text and diagrams shown.

At last, I set the button THE END to close everything when pressed. Same code of the other buttons.


### DBController
This class takes care of the database and its interactions with the game.

To save and maintain infos about the previous games I use a database created with SQLite. At first, the method `connect()` creates a connection between the application and the right database called PokeDB, which I saved inside the resources package.

After the connection `createTableChart()` create the table (if it doesn't exist already) which will contain all the scores. The table is composed by three columns which will contain an id (primary key), the name and the score of the players.
The last part of the method tries to obtain the connection using `connect()` and creates an object **Statement** to execute the SQL commands.

Then I created the static class `Gamer` to save infos about the players and add/get to/from the chart. It contains just the name and the score.

`addGamerAndScore()` adds the name and the score of the current player to the chart. At first a String with the SQL command is created (adds a new record with values undefined with ?) and then I use the same code in `createTableChart()`: after creating the connection to the db, a new `PreparedStatement` is created and used to execute the String written before.
`setString()` and `setInt()` replace the symbol ? with the parameters passed tho the main method (name and score). Then the chart is updated.

At last there's `getChart()` which get the infos from the chart to load them into the TableView. As before, There's a String with the SLQ command to be executed, which selects the infos and sorts them in descending order based on the score. An empty list of Gamer is created and it will be filled with the infos loaded from the db.
The SQL command is executed and the results is saved inside a variable that will be used inside the while loop to iterate on them. Each result obtained from the query is added tho the list _chart_ created before.
The method return the list full of the infos needed and it will be used in ResultControl to update the TableView.


### Conclusions

And that's it. I'm aware that something could be coded better and should be enhanced, but for a first time Java project I'm already satisfied. Hope you like it too! :D

