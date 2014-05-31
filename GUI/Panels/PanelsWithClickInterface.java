package Panels;

import Utils.Utils;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public abstract class PanelsWithClickInterface  extends ImageView{
	protected BorderPane mainPane;
	private ScaleTransition mouseEnteredTransition;
	private ScaleTransition mouseExitedTransition;
	private final double MAX_TRANSITION_SIZE = 1.05;
	
	public PanelsWithClickInterface(BorderPane mainPane, String imageLocation) {
		this.mainPane = mainPane;
		this.setImage(Utils.getImageViewFromLocation(this.getClass(),imageLocation));
		this.setPreserveRatio(true);
		this.setSmooth(true);
		this.setCache(true);

		this.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				applyClickOnPanel();
				
			}
		});
		applyOnHover();
	}
	public abstract void applyClickOnPanel();
	
	public void applyOnHover() {
		this.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {  
				getMouseExitedTransitionTransition().stop();
				
				getMouseEnteredTransitionTransition().play();
			}
		});
		this.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				getMouseEnteredTransitionTransition().stop();
				getMouseExitedTransitionTransition().play();
			}
		});
	}
	
	private ScaleTransition getMouseEnteredTransitionTransition(){
		if(mouseEnteredTransition == null){
			mouseEnteredTransition = new ScaleTransition();
			mouseEnteredTransition.setNode(this);
			mouseEnteredTransition.setFromX(1);
			mouseEnteredTransition.setFromY(1);
			mouseEnteredTransition.setToX(MAX_TRANSITION_SIZE);
			mouseEnteredTransition.setToY(MAX_TRANSITION_SIZE);
			mouseEnteredTransition.setCycleCount(1);
			mouseEnteredTransition.setAutoReverse(true);
		}
		return mouseEnteredTransition;
	}
	private ScaleTransition getMouseExitedTransitionTransition(){
		if(mouseExitedTransition == null){
			mouseExitedTransition = new ScaleTransition();
			mouseExitedTransition.setNode(this);
			mouseExitedTransition.setFromX(MAX_TRANSITION_SIZE);
			mouseExitedTransition.setFromY(MAX_TRANSITION_SIZE);
			mouseExitedTransition.setToX(1);
			mouseExitedTransition.setToY(1);
			mouseExitedTransition.setCycleCount(1);
			mouseExitedTransition.setAutoReverse(true);
		}
		return mouseExitedTransition;
	}
}