package thesis.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Paper {

	@GraphId
	private Long id;

	private String paperId;

	private String title;
	private int year;

	@Relationship(type = "AUTHOR_IN", direction = Relationship.INCOMING)
	private List<Role> roles = new ArrayList<>();

	public Paper() {
	}

	public Paper(String title, int released) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
