package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Actor;
import model.IActorDAO;

/**
 * @author Matt Hobler
 * @version 20151015
 *
 */
public class ActorDAO implements IActorDAO {
	
	protected final static boolean DEBUG = true;

	@Override
	public void createRecord1(Actor actor) {
		final String QUERY = "insert into actor (actId, lastName, firstName, homePhone, salary) VALUES (null, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, actor.getLastName());
			stmt.setString(2, actor.getFirstName());
			stmt.setString(3, actor.getHomePhone());
			stmt.setDouble(4, actor.getSalary());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public Actor retrieveRecordById1(int id) {
		final String QUERY = "select actId, lastName, firstName, homePhone, salary from employee where actId = " + id;
		// final String QUERY = "select actId, lastName, firstName, homePhone,
		// salary from actor where actId = ?";
		Actor act = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				act = new Actor(rs.getInt("actId"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("homePhone"), rs.getDouble("salary"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordById SQLException: " + ex.getMessage());
		}

		return act;
	}

	@Override
	public List<Actor> retrieveAllRecords() {
		final List<Actor> myList = new ArrayList<>();
		final String QUERY = "select actId, lastName, firstName, homePhone, salary from actor";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Actor(rs.getInt("actId"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("homePhone"), rs.getDouble("salary")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	@Override
	public void updateRecord1(Actor updatedActor) {
		final String QUERY = "update actor set lastName=?, firstName=?, homePhone=?, salary=? where actId=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedActor.getLastName());
			stmt.setString(2, updatedActor.getFirstName());
			stmt.setString(3, updatedActor.getHomePhone());
			stmt.setDouble(4, updatedActor.getSalary());
			stmt.setInt(5, updatedActor.getActId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from actor where actId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord1(Actor actor) {
		final String QUERY = "delete from employee where empId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, actor.getActId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Actor actor : retrieveAllRecords()) {
			sb.append(actor.toString() + "\n");
		}

		return sb.toString();
	}

	@Override
	public void createRecord(Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Actor retrieveRecordById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Actor updatedActor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecord(Actor actor) {
		// TODO Auto-generated method stub
		
	}
}
