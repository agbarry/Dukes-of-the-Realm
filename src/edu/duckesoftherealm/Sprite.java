package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite {

	private ImageView imageView;

    private Pane layer;

    protected double x;	// La position x de l'image
    protected double y;	// La position y de l'image

    protected double dx;
    protected double dy; 

    private double w;	// La largeur de l'image
    private double h;	// La hauteur de l'image

    public Sprite(Pane layer, Image image, double x, double y) {

        this.layer = layer;
        this.x = x;
        this.y = y;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);

        this.w = image.getWidth(); 
        this.h = image.getHeight(); 

        addToLayer();

    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    protected ImageView getView() {
        return imageView;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }
    
    public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void updateUI() {
        imageView.relocate(x, y);
    }
    
    public void move() {
        x += dx;
        y += dy;
    }

    // TODO: per-pixel-collision
    public boolean collidesWith(Sprite sprite) {
    	return getView().getBoundsInParent().intersects(sprite.getView().getBoundsInParent());
    }
    
    //public abstract void spriteMove(double x, double y);

    public abstract void checkRemovability();

}
