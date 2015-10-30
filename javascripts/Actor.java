package model;

/**
 * The Actor class represents a single actor.
 * 
 * @author Matt Hobler
 * @version 20151015
 *
 */
public class Actor {

	private int actId;
	private String lastName;
	private String firstName;
	private String homePhone;
	private double salary;

	public Actor() {
		actId = 0;
		lastName = "";
		firstName = "";
		homePhone = "";
		salary = 0;
	}

	public Actor(int actId, String lastName, String firstName, String homePhone, double salary) {
		this.actId = actId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.homePhone = homePhone;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getActId() {
		return actId;
	}

	public void setActId(int actId) {
		this.actId = actId;
	}

	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format("%5d : %s, %s, %s, %.2f", this.getActId(), this.getLastName(),
				this.getFirstName(), this.getHomePhone(), this.getSalary());
	}
}
