## Introduction

**This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).**

*Problem we are trying to solve:* 

In this program, we are trying to solve the problem of simulating several cell societies, which takes in a criteria of “rules” that cause certain cells in a 2-D grid to change their state. 

*Primary design goals:* 

The primary design goal of the project is to implement several flexible classes that can work together to represent a variety of CA simulations that can be specified through a set of rules. 

*Primary architecture of the design:* 

We feel that our grid should be open in the sense that it can assign cell neighbours in different ways based on the rules it is given. We think the cells should be open for extension since cells may have different rules for different simulations. Moreover, we believe that the simulation class will be closed based on the information that we have now.

## Overview

**This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should discuss specific classes, methods, and data structures, but not individual lines of code.**

*Specific components:* 

We think that there should be at least 3 main components that work together to carry out the simulation. Those components are a cell class, grid class, and simulation class.  

* The Cell Class: The cell class will have at least 3 methods that help design a Cell object. There should be:

	* a method that will allow the cell to know what its neighbors are given a parameter of a list of neighbor cells

	* a method to allow the cell to know what state it is currently in
	
    * a method that allows the cell to know what rules it abides by in the CA simulation and returns a next state given these rules.
	
    * A method that updates the Cell’s state to the next state.

* The Grid Class: The grid class should at the bare minimum contain a list of cells in a data structure, most likely a double array given the information that we have right now. It should have the following behaviors: 
	
    * sets cell neighbors
	
    * gets its cells
	
    * sets the rules for each individual cell.
	
    * Allows a cell to move within the grid if needed

* The Simulation Class: The simulation class holds the Grid, updates the grid, and controls the speed at which the grid updates. Important methods and fields that will be included in the UI include:
	
    * A Grid instance variable that holds the data of the current simulation
	
    * A speed instance variable that dictates the speed at which the simulation is run
	
    * A method to instantiate the Grid instance variable that the simulation will operate around based on an XML file input that specifies the rules of the simulation.
	
    * An update method that will update the status of the grid for each step/tick in the simulation.
	
    * A getGrid method that returns the grid

* The Visualizer Class: Works hand in hand with the Simulation Class but focus more on UI. It will act as a container class for the UI. It is also the class from which the program will be launched. This class will include:
	
    * A method to display the current states of the 2D grid and animate the simulation from its initial state until the user stops it
	
    * A method to allow users to load a new configuration file, which stops the current simulation and starts the new one. 
	
    * A method to allow users to pause and resume the simulation, as well as step forward through it.
	
    * A method to allow users to speed up or slow down the simulation's animation rate.




## User Interface

**This section describes how the user will interact with your program (keep it very simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.**

*Overall appearance of and interaction with User Interface Components:*

The user interface will have a toolbar on the screen while simulations run that allow the user to load a new file that would stop the current simulation and start a new one. We can do this using a drop-down menu for the user to choose between different types of simulations and a button that allows the user to go to another simulation. There will also be buttons on the toolbar that allow the user to pause, resume, speed through, slow down, and advance through a given simulation.
Pictures:

*Erroneous Situations Reported to User:* 

When there is bad input data/empty data, there will be a text message indicating to the user that the data is corrupt/empty on the screen and an exception will be thrown. Examples of erroneous situations that could occur are:

* Empty files

* Misformatted files

* Invalid Grid Size

* Trying to speed up a simulation that isn’t set up

* Design Details 

**This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.**

* Cell Class Details: The cell class should be an abstract class that is responsible for knowing its neighbors, state, and rules given to it that it abides by. The Cell Class will use the rules as a resource, and update its own state depending on its neighboring Cell objects. Because the Cell class is an abstract class, this allows us to make extensions of the Cell class that behave differently depending on different rules and simulation scenarios. For instance, one of the Cell subclasses would be FireCell, which has all of Cell’s attributes, but with its own implementation for the applyRules method. An example where this would be needed is the use case of setting a simulation parameter, probCatch, for a simulation, Fire, based on the value given in an XML file. The FireCell’s applyRules method would use the variable probCatch in determining its next state. We have decided that an Abstract Cell Class is very necessary with the design’s key goals because a) keeping track of multiple cells in a grid would be very tough to do without having a smaller class act as the cell in that grid and b) it allows for further abstraction in terms of creating different types of simulations for a Cell.

* Grid Class Details: The Grid Class is responsible for setting each cell’s neighbors as well as knowing information about all of the cells. Additionally, it sets the rules that each cell must abide by and has a method that would allow for the moving of a cell within the grid like would be required by the Segregation simulation. The Grid Class would use the XML file of rules as a resource so that it can apply the rules to its cells. Thus, the grid would be able to apply the rules to a middle cell by setting the next state of a cell to dead by counting its number of neighbors using the Game of Life rules/other rules for a cell in the middle, satisfying a use case. Additionally, the grid would also satisfy another use case by applying the rules to an edge cell, setting the next state of a cell to live by counting its number of neighbors using the Game of Life rules/other rules for a cell on the edge. The Grid class will have an update method that updates the cell’s to their next states according to these rules.  The Grid Class thus collaborates with the Cell Class because it contains a grid of Cell objects. The Grid class could be extended to account for varying grid shapes. For instance, we can have a TriangleGrid class that offers a different implementation for the assignNeighbor method. We felt that the Grid class was important with respect to the design goals of this project because it made the work for the Simulation Class a lot easier--for example, rather than having the simulation class keep track of all of the cells, assigning neighbors, and updating the cell every time, the Simulation class can simply tell the Grid to update its cells. The Simulation class does not even need to know anything about the Cells in doing this.

* Simulation Class Details: The Simulation Class will contain a Grid object and has a speed instance variable as well that dictates how fast the simulation is run. Additionally, the Simulation Class will have an update method that updates the grid as the simulation runs. Moreover, the Simulation Class will also have a getGrid method that will allow for the visual display of a grid when called. The Simulation Class will be able to satisfy the use case of moving the grid to the next generation by updating all cells in a simulation from their current state to their next state and displaying the result graphically. It will do this by telling the Grid object to update itself. We felt that having a Simulation Class was an important component of this project because it allows us to account for time, which is a factor in progressing the cells through different stages in a given simulation. By only giving the Simulation class the duty of updating the grid at certain speeds, this makes the class more compact and from a design perspective, easier to code.

* Visualizer Class Details: The Visualizer Class extends the Application class and is responsible for setting up and displaying the UI, displaying and animating the simulation, launching the program, and handling user input. The Visualizer class will create the window in which the simulation will happen and create an interactive toolbar that allows the user to speed up, slow down, pause, and resume the program. Users can also choose to advance the simulation frame by frame using the skip button. Additionally, the toolbar will permit users to select which file they wish to use to specify the simulation rules. The Visualizer Class displays the simulation by drawing the corresponding grid and updates the display based on data contained in the Simulation Class. The Visualizer Class takes care of the last use case by allowing switching simulations through a drop down menu. We felt that it was necessary to have the Visualizer class because it allowed us to separate the task of displaying the UI from the task of the actual simulation/updating of the cells. Originally we had thought of keeping the visuals in the Simulation class, but having a Visualizer class allows for these two classes to be more separate and well-defined within the bounds of the design.

## Design Considerations

**This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.**

One design decision we have considered making is to have a Neighborhood object that each cell contains. This Neighborhood class would give the cell information about what other cells can be defined as “neighbors” based on the geometry of the grid and the rules dictated by the file that is read into the program. This helps to facilitate interactions between cells, which can vary greatly depending on the specifications given. At the moment, we have decided not to add this class until we figure out the mathematics behind the relationship Grid and Neighborhoods. For us, this is a major assumption that we made, since it could impact how our class structure lines up for the rest of the project.

## Team Responsibilities

This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

* *Dana:* 

	* Primary: Visualizer and some of Simulation

	* Secondary: Cell and Grid class

* *Brendan:* 

	* Primary: Neighborhood assignment math, Grid Class

	* Secondary: Cell class and extensions of Cell classes

* *Andy:* 

	* Primary: Extensions of Grid and handling IO from XML

	* Secondary: Some of simulation and Grid class

* *High-level plan:*

We plan to complete the abstractions for the game first (components that won’t change), which includes the Abstract Cell class and some aspects of the Grid class and Simulation/Visualizer classes. Once we have a foundation for these classes, we want to specifically work on the GameOfLife simulation so that we have an implementation that we can test and so that we can figure out how to work with XML files and ResourceBundles. Lastly, we want to work on different extensions of our classes so that our application can handle multiple types of simulations (the ones given to us in the project description).
