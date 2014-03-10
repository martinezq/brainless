package org.mysoft.brainless.studio.gui;

import org.mysoft.brainless.creature.SimpleCreatureSimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SimulationPreview extends Group {

	protected SimpleCreatureSimulation simulation;
	
	public void init() {
		Rectangle r = new Rectangle(25, 25, 250, 250);
		r.setFill(Color.BLUE);
		getChildren().add(r);
	}

	public void start() {

		final Duration oneFrameAmt = Duration.millis(1000 / 30);
		final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, new EventHandler() {

			@Override
			public void handle(Event arg0) {
				System.out.println("handle");
				
			}

		}); // oneFrame

		// sets the game world's game loop (Timeline)
		TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(oneFrame).build().play();

	}

}
