## Part 1

I worked with Ben Auriemma, Conrad Mitchell, and Kelley Scroggs on this document.

	1. We intend for our simulation to take advantage of encapsulation in a mnumber of ways. Our cell class knows its current state but does not know the state of its neighbors. The Grid class takes care of determining a given cell's next state by examining the states of its neighbors and adjusting that cell's nextState field accordingly.
	
	2. There will be inheritance hierarchies for the Cell class and the Grid class. Both classes will have extensions based on the type of simulation (e.g. there will a different classes for Fire Simulation Cells and Game of Life Simulation Cells). This is because the Cells need to know the rules for how many states they can have and what states they can take. The Grid class needs to have different implementations so that it can know the rules for how a given cell should update its state based on the cells around it.
	
	3. Both the Grid and Cell classes will be closed in that the only changes that will be made are to create subclasses of them. This takes advantage of polymorphsim by keeping the superclass constant but allowing improved flexibility by implementing new child classes.
	
	4. 
	
	5. I think our design is good because it allows for flexibility in front end classes, since they do not need to take into account the type of simulation. In order to allow for a new simulation type, one need only create a new type of Grid and a new type of Child that is capable of implementing the rules of that simulation, which is not difficult given the way both classes encapsulate information.
	
## Part 2

	1. My area is in the back end. The front end classes operate on abstractions of the classes that I am working on. In other words, front end classes will take in objects of type Grid and Cell, which allows them to operate without worrying about the type of Grid and Cell that is being used. As long as the Grid class matches the Cell class, then the rules will be implemented accordingly and the front end can operate smoothly.
	
	2. The only major dependency I can see is that a given Grid type must implement the corresponding Cell type. This dependency can be handled in the constructor to avoid implementing a Grid with the wrong Cell type.
	
	3. Was handled in question 2
	
	4. One set of sub/super classes that I have addressed is the Cell class and its children. Constants include the update() method which updates the Cell from its current state to its next state. They also have an ArrayList of neighboring cells, from which they can determine their next state. Things that vary include the number of states that they can take on and the rules that each cell follows. This can be improved, since I know the Grid class also knows the rules of how cells interact; perhaps the neighbors ArrayList can be eliminated and each cell can have a method called setNextState which is called in the Grid class based on the Grid's knowledge of the Cells' neighbors.
	
## Part 3 

	The feature that I am most excited to work on is implementing the logic for how cells change state (i.e. the rules). It will take some creativity to handle for each simulation, but I don't think it will be too difficult. The feature that I am most worried about working on is implementing the Grid for geometries that are not square.