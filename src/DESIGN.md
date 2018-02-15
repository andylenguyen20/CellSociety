# cellsociety 

Provide the high-level design goals of your project.

* On the front-end, the design goal was to create a simple, easy-to-understand user experience for the CA Simulations, allowing the user
  to easily understand how to manipulate the simulations to play, pause, switch simulations, change simulation parameters, etc. This was achieved through
  having a main Visualizer class that was responsible for running and updating the program. Visualizer called on different classes located within the visualization
  package to do work for it, such as the CommandHandler class or the DataPlotter class. The UI components were laid out in a BorderPane to make the layout
  easy for the user. 




Explain, in detail, how to add new features to your project

* To run multiple simulations at the same time so they can compare the results side by side, you would make a Button that reads from a ResourceBundle
  to display a String called "Multiple Simulations". You would add an event handler to that button so that when it is pressed, the program knows to react
  to it. In order to run multiple simulations, you would have to click the same type of simulation again. Once you do that, there should be a method
  called drawSecondGrid that calls upon the CellsToVisualize class and the drawFreshGrid method to initialize another grid of cells side by side to the 
  original grid of cells. Then, the two simulations would start off in the same point and run side by side. Because the two simulations would be on the same
  screen, you would be able to play/pause/step forward (etc.) through both. 



Justify major design choices, including trade-offs (i.e., pros and cons), made in your project

* One design decision made by the team during the Basic Implementation was to have a separate Fish and Shark cell for Wator. However, there were still bugs in
  the Wator simulation by the time that the Basic Implementation was due, and it was very hard to debug and fix those changes because the two separate Cell
  classes were not reacting to eachother the way that they should. We had originally designed it this way to allow additional flexibility and compartmentalizing
  of the different types of cells. Since Fish and Shark abide by different rules, we thought it would be more clean/logical to separate them into two classes.
  During the second sprint, however, we realized that implementing user input actions for Wator would not work with the same methodology as the other classes.
  Additionally, changes we made in the front-end further highlighted the bugs we had in the Wator simulation. Therefore, we designed a single Wator cell that
  includes both Fish and Shark. While the tradeoff was that we were not able to separate Fish and Shark into two different entitities, I think this designed helped
  both the back-end and front-end of the program. This one Wator cell implementation was easier to implement and fixed the original bugs in the program.

* Another design decsision made by the team was to stick with using a double[]props to represent Simulation Parameters, just like we had done in the basic
  implementation, instead of switching to a HashMap of Strings, representing the String names of Parameters, mapped to values of double parameters. We had 
  originally debated switching to the HashMap format because that would make it easier for the user to input changed into the program. For example, inputting
  "SharkStartingEnergy:5.0" is way easier and clearer to the user than having to put the index of the double array and then the parameter value ("2:5.0"). However, 
  we did not change this because we realized that we would have to make changes to front-end, back-end, and all of the XML files and realized that although
  it was not a hard change to make, we would rather focus on getting more features on the Simulation and Configuration sides first. In the end, we did not
  have the time to implement this change. I would have preferred the HashMap method for clarity for the user, but the tradeoff of spending time to implement it
  outweighed the benefit.



State any assumptions or decisions made to simplify or resolve ambiguities in your the project's functionality

* On the UI side, we assumed that the user would know the indexes of the parameters that she/he would like to change when typing in a new desired parameter
  in the TextField designed to allow the user to change parameters. We also assumed the user would know the integer corresponding to the state of a cell, so they
  would, for example, type in 1 to the TextField in Game of Life to change cell states to alive, and 0 to change cell states to dead.
