# cellsociety 

Provide the high-level design goals of your project.

* The goal of this project is design a flexible model that allows users to create and run simulations of specific types that follow certain rules. It also allows users to style their simulations given different configurations and provide additional functionality beyond that of a simple simulation.

* On the front-end, the design goal was to create a simple, easy-to-understand user experience for the CA Simulations, allowing the user
  to easily understand how to manipulate the simulations to play, pause, switch simulations, change simulation parameters, etc. This was achieved through
  having a main Visualizer class that was responsible for running and updating the program. Visualizer called on different classes located within the visualization
  package to do work for it, such as the CommandHandler class or the DataPlotter class. The UI components were laid out in a BorderPane to make the layout
  easy for the user. 




Explain, in detail, how to add new features to your project

* To add a new feature to the project depends on what type of feature you're trying to add. In order to add a new simulation, you would need to create a simulation.xml file, as well as add in a specific cell and grid subclass that implements a set of rules specific to the simulation. For instance, the WatorCell and WatorGrid classes supply implementation of the Cell rules and Grid functionality that makes the Wator simulation unique from other simulations. You would also need to edit the configuration component of this project, which configures the simulation with a specific set of cells and its corresponding Grid. For instance, the CellFactory class generates a cell of a specific type. This type would need to be added to the CellFactory's implementation of the method used to generate cells. In regards to adding new features for visualization, it is most likely necessary to edit the Visualization class directly, since this class handles several user actions and most of the visuals of the simulation.

* To run multiple simulations at the same time so they can compare the results side by side, you would make a Button that reads from a ResourceBundle
  to display a String called "Multiple Simulations". You would add an event handler to that button so that when it is pressed, the program knows to react
  to it. In order to run multiple simulations, you would have to click the same type of simulation again. Once you do that, there should be a method
  called drawSecondGrid that calls upon the CellsToVisualize class and the drawFreshGrid method to initialize another grid of cells side by side to the 
  original grid of cells. Then, the two simulations would start off in the same point and run side by side. Because the two simulations would be on the same
  screen, you would be able to play/pause/step forward (etc.) through both. 





Justify major design choices, including trade-offs (i.e., pros and cons), made in your project

* One major design choice we decided to use was to have the Cell class extend Polygon, which meant that cells are drawn based off of their vertices rather than based off of their position in a two-dimensional array. By doing this, there was a huge tradeoff in the fact that we had to spend lots of time altering the way we configured the cells, draw the cells, as well as create a set of coordinate systems for the cells to live in. The pros of this design was that using vertices to map out the cells made neighbor-finding much more universal and made drawing actually easier; this was because finding neighbors via vertice intersection was simple and applies for all cell shapes and because drawing is now based off of a "scaling" factor based off the generated coordinate system of the grid type.
 
* Another major design choice was to offer plenty of public functionality in regards to the Cell class. While it may seem odd from a back-end perspective that a Cell has several public methods (such as getColors, getParams, setParams, setCurrentState), we felt it was necessary because these methods were used across packages by the Visualization component. This is because the Visualization component needed to be able to change cell states from user input. While there was a tradeoff in encapsulation, it allowed us to implement additional functionality within the Visualizer. We also felt that this made Cell more likely to stay closed, in that it supplied information that may be useful with other features.
 
* The last major design choice that I will talk about is the decision to have so many static factory classes: GridFactory, CellFactory, XMLWriterFactory, MapFactory, and several factory classes within visualization component. We felt that while object-oriented thinking was a necessary stepping stone into the simulation implementation (i.e. using grid and cell as abstractions with several subclasses), we felt that the configuration and visualization component required more one-time actions that didn't really differ based off the environment around it. For instance, there was no need to have a MenuCreator object that runs once during the entire program at the beginning. Instead, it made more sense to have a static class that performs actions that aren't affected by attributes that this class contains or any of the environment around it. A MenuCreator will always create a menu the same way given some specification information, so why not make it a factory class? The same goes for the other classes. The tradeoff was that while we let go of some object-oriented thinking (in the sense that an object doesn't always contain another object but instead uses a factory class to perform a given action), it was positive in the sense that it was more clear to the reader that these actions are static and don't vary regardless of the environment around it.

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


