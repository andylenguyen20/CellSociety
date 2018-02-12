# cellsociety

CompSci 308 Cell Society Project

**Names of all people who worked on the project**

Brendan Cheng, Andy Nguyen, Dana Park.

**Date you started, date you finished, and an estimate of the number of hours worked on the project**

Started January 23 and completed February 11. We spent around 100 hours on this project.

**Each person's role in developing the project**

For the first sprint, Dana spent most of the time working on the user interface and user interactions. Andy spent most of his time configuring the simulation xml files, file readers, and worked on some cell and grid classes. Brendan spent most of his time primarily on the back end with the cell classes and grid classes as well. Aside from this, we all still collaborated with each other in regards to the processes and simulation implementations as well as debugging of the back end (Game of Life, Wator, Fire, Segregation).

During the second sprint, we decided to delegate much more separated responsibility, so we branched into three categories: back-end, xml-configuration, and front-end. Dana handled the front-end work: refining the user interface, adding charts, adding styling, and several user input events including two new text fields to change cells states and parameters and mouse-clicks to change states of specific cells. Brendan spent his time with the XML configuration work: generating random XML files from user input information and reading the current state of the grid and saving the state of the simulation to an XML file. Andy spent his time changing the back-end processes to account for the flexibility in having different shape types: generating a coordinate system for triangles, creating more general neighbor-finding algorithms for different shapes, and changing the ways in which cells are initialized/work within the four simulations.

**Any books, papers, online, or human resources that you used in developing the project**

We used a website [article on writing to an XML file](http://crunchify.com/java-simple-way-to-write-xml-dom-file-in-java/) in order to figure out how to save the current state of our simulation into an XML file. We similarly used concepts in this article to write random XML configurations into a random XML file.

We also used [this discussion on linecharts](https://stackoverflow.com/questions/44036735/live-update-linechart-in-javafx) to help update our line charts and incorporate threading into our project.


**Files used to start the project (the class(es) containing main)**

The main class that runs this project is Visualizer.java. 

**Files used to test the project and errors you expect your program to handle without crashing**

The main files we use to test our programs were XML files: fire_simulation.xml, gol_simulation.xml, segregation_simulation.xml, and wator_simuation.xml

Errors we expect our program to handle without crashing: We expect that the user can input invalid information into our four text boxes. For instance, the user can type in out-of-bound indices for states and props as well as null props. In addition, the user can input an invalid number of arguments into the "GET RANDOM" simulation feature.

**Any data or resource files required by the project (including format of non-standard files)**

We used CommandBar.properties and SimulationBar.properties as our resource bundles for this project.

As mentioned before, we also needed the fire_simulation.xml, gol_simulation.xml, segregation_simulation.xml, wator_simulation.xml files to test our application.

In regards to the "extra" saving state configuration feature, we saved our files under the format: "<simulation_type>State.xml"

Lastly, in regards to the "extra" random configuration feature, we also used a random_properties.xml file to read in the ranges/possibilities for cell states and simulation parameters under the specific types of simulations.

**Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)**

To change the simulation type from one to another, go to the Choose Simulation drop-down menu and select the simulation of your choice. After your selection, the new simulation will run on the screen.

To Pause, Play, Slow Down, or Speed Up the simulation that is currently running, go to the Choose Command drop-down menu and select the command of your choice. After you click the command, the action you want will execute.

To Step Forward through the current simulation, click the Step Forward button each time you want to update the grid once. 

To change the state of any of the cells, go to the Text Field that states “Enter State”. Enter the index of the state that you want to change selected cells to(e.g. Index 0 = ALIVE in the Game of Life simulation). Afterwards, click the Enter button. When you click the Simulation again, you can change states of cells to your specified state by clicking on the cells directly through your mousepad.

To change the parameters of the Simulations, click the “Index:Prop” Text Field on the right of the screen. Enter the index of the parameter, followed by a colon, and then the new property of the simulation that you want to set. (e.g. if you input 0:0.99 in the text field while running the Fire simulation, the fire will swipe the entire screen every time until you re-change the parameters). After you input your data, click the Submit button and run the simulation again to see the effects.

To generate a new XML file of the exact configurations of the simulation at hand, click the Save State button, and refresh the folder containing the xml files to see a new file pop up.

We also have the ability to generate a random XML file configuration given the user inputs a dimension and simulation type and clicks the  “GENERATE RANDOM” button. DISCLAIMER: There is a maximum grid size at which the program can run without lagging considerably, so if the user decided to generate a grid size that was too large, the simulation would just be a 20x20 grid. In addition, we made it so that the user can’t input an odd number of rows for the triangle simulation because we felt that the definition of “neighbor” for triangles in the unbounded case was very subjective and undefined.

**Any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements**
Much like I mentioned above, we made it so that the user can’t input an odd number of rows for the triangle simulation because we decided that the definition of triangle neighbors in the unbounded case was really subjective.


**Any known bugs, crashes, or problems with the project's functionality**
We do not have any major known bugs within our program's functionality. However, something that should be mentioned is that at times, the program has lagging issues, e.g. with regards to the graph. In addition, it can't handle grid sizes larger than 400 cells due to these lagging issues, so we set a cap error check on that within our program so that users can't input more than this number of cells.

**Any extra features included in the project**
-Added a live-action graph that shows current populations of each cell type displayed on the grid of cells
-Allowed for users to change parameters of a simulation
-Allowed for users to change states of cells by directly clicking on them
-Allowed for users to save an XML file to the exact configuration of a current simulation running on screen by clicking a button
-Adjusted the program to allow for shapes other than squares:
- had cells extend the Polygon class so that can be drawn to other shapes and have a coordinate system that can be drawn to the screen. Specifically created an extra triangle grid implementation, but the program is fit to handle hexagonal as well because all you have to do is generate a coordinate system for octagons (bounded neighbor finding will always work and drawing will always work as long as the coordinate system is valid)

-allowed for a variety of grid edge types, such as finite and toroidal by adjusting the neighbor-finding algorithm to vertice-intersections
- vertice intersections allow our neighbor-finding to handle diagonal, adjacent, or touching neighbors

-allowed simulations initial configurations to be set both by a list of specific locations and states and also by a random generator based on a file random_properties.xml, which has the range information as well as the cell types and possible states to choose from for each simulation
-allowed users to save the current state of the simulation as an XML configuration file: named "<simulation_type>State.xml"

**Your impressions of the assignment to help improve it in the future**
We feel that this assignment was a lot of hard work and could be improved by giving the students more time to work on the project, since we ended up devoting most of our lives these past two weeks on this project alone.
