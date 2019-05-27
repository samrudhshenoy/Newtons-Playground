Newton's Playground


By Samarth Shah and Samrudh Shenoy
May 2019


Our program is a visual physics simulator, specifically designed for building and running user-built kinematic ball and obstacle configurations. Our program is very
customizable, letting the user set their personal preference for aspects such as downward gravitational acceleration, obstacles in the simulation, and ball weight.


The overall goal of the program is to allow the user to test various and infinitely many scenarios to experiment with how the physics in our world affect the way that
objects move. The program can be used in both a scientific setting, as well as simply for entertainment. Due to the inability or inconvenience to change certain real-life 
variables (gravity, a singular object's mass, etc.), our program aims to allow users to have the power to do so at their fingertips.


One of our main inspirations motivating the creation of Newton's Playground is our knowledge of Physics. We both have knowledge in Physics A and B, and wanted to create
an application at the crossroads of Computer Science as well as another science. Additionally, during class, our physics teacher often mentions that she wishes we could 
take our experiments farther, but that our limitations to alter physical qualities stand in the way. Through this online program, students can conduct such experiments 
in the manner preferred, including our physics teacher who we hope to show this program to.


Regarding usability, our designated target market are those who are involved in physics, so curious students or physics departments at schools could use the program provide
an easy to comprehend visualization of common word problems students tend to have trouble on, or to explain the meanings behind formulas.




Instructions:
- Set values for parameters on the side bar
- Enter values to change the values for the world or add a new plane as an obstacle
- Pause and play to set the ball into motion into the simulation


Class List:
- Main
- Obstacle
- Ball
- Force
- World
- MainScreenController
- MenuScreenController
- ObjectAdderPanelController
- WorldEditorPanelController
- LoadScreenController
- SaveScreenController



Features:


Must Have:
- Balls can be let go from different place
- Gravity and ball weight can be set to custom values
- Ability to set initial x and y velocity
- Pause and play options
- Ability add new planes into the simulation, as well as reset them
- Data collection during experiments


Want to Have:
- Ability to save experiments
- 3D User Interface
- Ability to set air pressure and resistance
- Launching the ball out of cannons/catapults
- Inclusion of water mechanics
- Ability to set speed of process (slow mo or fast forward, or even rewind)


Stretch:
- POV from object's standpoint
- Inclusion of electricity and/or magnetism
- Different objects (spherical shell, box, pole)
- Parachute
- Ability to record


Credits:

- Samrudh Shenoy: making most of the math work like the angles and the forces currently on the ball
- Samarth Shah: making the UI as well as the basic parts of the model like the force class, writing readme, and making uml
- Mrs. Shreve: for helping us with angles because we had no idea how to do that in terms of adding a new force
- Websites we used:
	Collisions: https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
	Animations/Graphics: https://netopyr.com/2012/06/14/using-the-javafx-animationtimer/
	Canvas: https://docs.oracle.com/javafx/2/canvas/jfxpub-canvas.htm


Feedback


* Move “Different objects” to Want to Have
* Perhaps use different materials
   * Such as a rubber ball or metal ball
* Change the readme to flow better and “sell” your program


UML
* Add depends on relationships
* Nothing is going into ball, ramp, wall, and platform