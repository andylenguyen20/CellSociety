# Cell Society DesignExercise.md

Completed by DANA PARK(dp178), ANDY NGUYEN(aln20), BRENDAN CHENG(bmc41)

## Design Description

#### Cell Class
```java
    // get neighbors
    public void setNeighbors(List<Cells> neighbors);
	// understands its state
	public void updateState();
    // Knows what its rules are
    public void giveRules (Rule rule);
    
```

#### State Class
```java
    // knows current state
    public void getCurrentState ();
    // knows next state
    public void getNextState ();
    // able to update state
    public void updateState ();
    

    
```
#### Grid Class
```java
    // knows its cells
    public void getCells ();
    // assigns cell neighbors
    public void setCellNeighbors ();
    // Knows what its rules are
    public void giveRules (Rule rule);

    
```
#### Simulation Class
```java
    // knows its grid
    public void getGrid ();
    // knows when to update states
    public void goToState ();

    
```

```



