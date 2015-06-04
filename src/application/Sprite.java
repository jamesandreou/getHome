package application;

import javafx.geometry.BoundingBox;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	
    public ImageView node;
    public double vX = 0;
    public double vY = 0;
    public double x = 0;
    public double y =0;
    public BoundingBox bounds;
    public boolean active = true;
 

	
}
