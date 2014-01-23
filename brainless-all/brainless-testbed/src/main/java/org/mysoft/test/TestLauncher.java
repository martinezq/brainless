package org.mysoft.test;

import javax.swing.JFrame;

import org.jbox2d.testbed.framework.TestbedFrame;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedPanel;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;
import org.mysoft.brainless.human.HumanSimulation;

public class TestLauncher {

	public static void main(String[] args) {
		
		TestbedModel model = new TestbedModel();
		
		model.addCategory("My Super Tests");      
		//model.addTest(new SimpleCreatureCharacterTest());
		model.addTest(new HumanCharacterTest()); 
		
		model.getSettings().getSetting("Hz").value = HumanSimulation.HZ;
		model.getSettings().getSetting("Vel Iters").value = HumanSimulation.VEL_ITERATIONS;
		model.getSettings().getSetting("Pos Iters").value = HumanSimulation.POS_ITERATIONS;

		
		//model.getSettings().addSetting(new TestbedSetting("My Range Setting", SettingType.ENGINE, 10, 0, 20));

		TestbedPanel panel = new TestPanelJ2D(model);   

		JFrame testbed = new TestbedFrame(model, panel); 
		
		
		testbed.setVisible(true);
		testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
