package org.mysoft.brainless.test.launch;

import javax.swing.JFrame;

import org.jbox2d.testbed.framework.TestbedFrame;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedPanel;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;
import org.mysoft.brainless.test.human.HumanSimulationTestbedModel;

public class TestLauncher {

	public static void main(String[] args) {
		
		
		//TestbedModel model = new SimpleCreatureSimulationTestbedModel();
		TestbedModel model = new HumanSimulationTestbedModel();

		TestbedPanel panel = new TestPanelJ2D(model);   

		JFrame testbed = new TestbedFrame(model, panel); 
		
		testbed.setVisible(true);
		testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
