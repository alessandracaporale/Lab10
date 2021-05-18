package it.polito.tdp.rivers.db;

import java.util.*;

import it.polito.tdp.rivers.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getFlowsByRiver (River river) {
		String sql = "SELECT id, day, flow, river "
				+ "FROM flow "
				+ "WHERE river = ?";
		List<Flow> flows = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Flow flow = new Flow (rs.getInt("id"), rs.getDate("day").toLocalDate(), rs.getFloat("flow"), river);
				flows.add(flow);
			}
			conn.close();
			return flows;
		}
		catch (SQLException e) {
			throw new RuntimeException ("Errore in getFlowsByRiver");
		}
	}
}
