/**
 * 
 */
package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public abstract class Castle {
	
	private ImageView imageView;

    private Pane layer;
    
    private long treasure;
    private long level;
    
    protected ArrayList<Soldier> listSoldier; 
    protected Production_Unit pUnit;
    private Displacement moveOrder;
    
    private int gateX;
    private int gateY;
    
 // A terminer après
    
    

}
