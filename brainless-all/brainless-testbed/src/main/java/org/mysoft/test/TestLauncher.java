package org.mysoft.test;

import javax.swing.JFrame;

import org.jbox2d.testbed.framework.TestbedFrame;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedPanel;
import org.jbox2d.testbed.framework.TestbedSetting;
import org.jbox2d.testbed.framework.TestbedSetting.SettingType;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;

public class TestLauncher {

	public static void main(String[] args) {
		
		TestbedModel model = new TestbedModel();
		
		model.addCategory("My Super Tests");      
		model.addTest(new GeneticJumperTest()); 
		model.addTest(new Test1Definition());

		model.getSettings().addSetting(new TestbedSetting("My Range Setting", SettingType.ENGINE, 10, 0, 20));

		TestbedPanel panel = new TestPanelJ2D(model);   

		JFrame testbed = new TestbedFrame(model, panel); 
		
		testbed.setVisible(true);
		testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
