/**
 * 
 */
package com.loinx.java.packaging.izpack.panels.action;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.data.PanelActionConfiguration;
import com.izforge.izpack.api.handler.AbstractUIHandler;
import com.izforge.izpack.data.PanelAction;

/**
 * @author LoiNX
 *
 */
public class PrintCustomFields implements PanelAction {

	@Override
	public void executeAction(InstallData installData, AbstractUIHandler handler) {
		int count = Integer.parseInt(installData.getVariable("creature.count"));
		System.out.println("============= Data Gathers Through Custom Creatures ============");
		System.out.println(count + " number of creatures were selected. This information was stored in creature.count");
		for (int i = 1; i <= count; i++) {
			System.out.println("creature.type." + i + ": " + installData.getVariable("creature.type." + i));
			System.out.println("creature.colour." + i + ": " + installData.getVariable("creature.colour." + i));
		}
		System.out.println("=================================================================");
	}

	@Override
	public void initialize(PanelActionConfiguration arg0) {
		// Do nothing
	}

}
