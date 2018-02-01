## WORKED ON BY DANA PARK AND RYAN POND

## Part 1

What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?

* My design has it so that my Visualizer class is closed, and will be hidden from other areas of the program except when it is called in the Simulation class.

What inheritance hierarchies are you intending to build within your area and what behavior are they based around?

* I have a cell class that will extend different types of cells (like fire cell, Game of Life cell) and a Grid class that will know information about positions of the grid. Additionally, we have an XMLPaster class to read in the XML files for the Grid class. The UI and grid simulation is handled in Visualizer and everything is run in the Simulation class.

What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?

* I think that in the end, Visualizer and Simulation classes will be closed because the different simulations will be handled within the Grid/Cell/XMLParser classes. There would not be a need to change how the program is run or the UI components.

What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?

* We will handle an exception relating to corrupt files/nonexistent files in the Simulation Class and a potential NullPointerException in the Visualizer class if the grid is not initialized / in view.

Why do you think your design is good (also define what your measure of good is)?

* I think the design is good because having two classes for front-end makes it easier to debug the program and compartmentalizing aspects of the project keep it organized. My measure of good is that the program runs and is not unnecessarily bulky. The logic also has to be easy to follow.

## Part 2

How is your area linked to/dependent on other areas of the project?

* Both the Visualizer and the Simulation class cannot run without the back-end classes fully functional, so my area is heavily dependent on the back-end of the program.

Are these dependencies based on the other class's behavior or implementation?

* Yes. The Simulation/Visualizer Class will run different versions of CA simulations dependent on changes in the back-end side of programming.

How can you minimize these dependencies?

* It is hard to minimize dependencies because both of these classes are inherently dependent on changes/implementations of the back-end. The best thing to do is to make sure that the front-end classes are robust enough to handle changes in the back end.

Go over one pair of super/sub classes in detail to see if there is room for improvement. 

* We do not have super/sub classes to go over yet.

Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).

*Our area does not have super/sub classes yet, so there is nothing to discuss as of now.

## Part 3

Come up with at least five use cases for your part (most likely these will be useful for both teams).

* The Visualizer class will have a method so that if the button "Pause" is pressed in the drop down menu, the simulation will pause.
* The Visualizer class will have a method so that if the button "Play" is pressed in the drop down menu, the simulation will play.
* The Visualizer class will have a method so that if the button "Skip forward" is pressed in the drop down menu, the simulation will skip forward a step.
* The Visualizer class will have a method so that if a button is pressed for a new simulation in the drop down menu, the simulation will change.
* The Visualizer class will also have a method to reset the simulation that will take effect if a reset button is pushed in the UI.

What feature/design problem are you most excited to work on?

* I am super excited to make the switching of simulations easy by just a press of a button on the dropdown menu for simulation selection.

What feature/design problem are you most worried about working on?

* I am most worried about being able to switch simulations seamlessly. I think that is the hardest problem that I have to face.

