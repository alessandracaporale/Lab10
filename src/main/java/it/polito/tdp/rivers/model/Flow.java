package it.polito.tdp.rivers.model;

import java.time.*;

public class Flow {
	
	private int idFlow;
	private LocalDate day;
	private double flow;
	private River river;

	public Flow(int idFlow, LocalDate day, double flow, River river) {
		this.idFlow = idFlow;
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	

	public int getIdFlow() {
		return idFlow;
	}

	public void setIdFlow(int idFlow) {
		this.idFlow = idFlow;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFlow;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (idFlow != other.idFlow)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}
}
