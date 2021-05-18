package it.polito.tdp.rivers.model;

import java.util.*;
import java.time.*;

import it.polito.tdp.rivers.db.*;

public class Model {
	
	
	public Model() {
	}

	RiversDAO dao = new RiversDAO();
	
	public List<River> getAllRivers() {
		return dao.getAllRivers();
	}
	
	public List<Flow> getFlowsByRiver (River river) {
		return dao.getFlowsByRiver(river);
	}
	
	public LocalDate getStartDate (River river) {
		List<Flow> flows = this.getFlowsByRiver(river);
		
		LocalDate startDate = flows.get(0).getDay();
		for (Flow f : flows) {
			if (f.getDay().isBefore(startDate)) {
				startDate = f.getDay();
			}
		}
		return startDate;
	}
	
	public LocalDate getEndDate (River river) {
		List<Flow> flows = this.getFlowsByRiver(river);
		
		LocalDate endDate = flows.get(0).getDay();
		for (Flow f : flows) {
			if (f.getDay().isBefore(endDate)) {
				endDate = f.getDay();
			}
		}
		return endDate;
	}
	
	public int getNumMeasurements (River river) {
		return this.getFlowsByRiver(river).size();
	}
	
	public double getFMed (River river) {
		List<Flow> flows = this.getFlowsByRiver(river);
		
		double fTot = 0;
		for (Flow f : flows) {
			fTot = fTot + f.getFlow();
		}
		double fMed = fTot / flows.size();
		return fMed;
	}
}
