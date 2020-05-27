package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@SequenceGenerator(name="roles_role_id_seq", allocationSize = 1)
	@GeneratedValue(generator="roles_role_id_seq", strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int id;
	@Column(name="role_name")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* Converts Role object values into Hashcode
	* <p>
	* This method is used to convert the values of the selected Role into Hash Code and returns those values as an int.
	*
	* @return returns int value from hashcode
	* 
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	* Returns a boolean if the instance of Role equals in value to the parameter entered
	* <p>
	* This method is used to check if the instance of Role is equal to object and checking if the obj is null.
	* 
	* @param  obj  object that is to be compared to instance of Role class
	* @return true if Role equals parameter, obj is not null, and value of Role equals value of parameter,
	* 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	* Converts Role object into a String
	* <p>
	* This method overrides the toString method to output proper format for webpages.
	*
	* @return Role instance as a String.
	* 
	*/
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
