//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.08 at 11:47:04 AM BST 
//

package com.bridge.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "activitylist")
public class Activitylist {
	protected List<Activity> act = new ArrayList<Activity>();
	@XmlElement(name = "activity")
	public List<Activity> getAct() {
		if (act == null) {
			act = new ArrayList<Activity>();
		}
		return this.act;
	}
}