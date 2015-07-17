package kr.dyyi.pma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class LogicComponent {
	// Fields
	private String name;
	private Cell cell;
	private HashMap<Pin, Net> connectionHash;
	//private LinkedList<LogicalGraphComponent> visitingFFList;
	private HashSet<LogicComponent> visitingComponentSet;
	//private Integer descendentCount;
	//private Boolean visit;
	
	// Getter and Setter
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public Cell getCell() { return cell; }
	public void setCell(Cell cell) { this.cell = cell; }
	public HashMap<Pin, Net> getConnectionHash() { return connectionHash; }
	public void setConnectionHash(HashMap<Pin, Net> connectionHash) { this.connectionHash = connectionHash; }
	//public LinkedList<LogicalGraphComponent> getVisitingFFList() { return visitingFFList; }
	//public void setVisitingFFList(LinkedList<LogicalGraphComponent> visitingFFList) { this.visitingFFList = visitingFFList; }
	public HashSet<LogicComponent> getVisitingComponentSet() { return visitingComponentSet; }
	public void setVisitingComponentSet(HashSet<LogicComponent> visitingComponentList) { this.visitingComponentSet = visitingComponentSet; }
	//public Integer getDescendentCount() { return descendentCount;}
	//public void setDescendentCount(Integer descendentCount) { this.descendentCount = descendentCount; }
	//public Boolean getVisit() { return visit; }
	//public void setVisit(Boolean visit) { this.visit = visit; }

	// Constructor
	public LogicComponent() {
		name = null;
		cell = null;
		connectionHash = new HashMap<Pin, Net>();
		//visitingFFList = new LinkedList<LogicalGraphComponent>();
		visitingComponentSet = new HashSet<LogicComponent>();
		//descendentCount = null;
		//visit = false;
	}
	
	// Methods
	//public Boolean isVisit() { return visit; }
	//public void addDescendentCount(Integer descendentCount) { this.descendentCount += descendentCount; }

}
