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
	private String paperAbstract;
	private String s2Url;
	private String venue;

	@Relationship(type = "AUTHOR_IN", direction = Relationship.INCOMING)
	private List<Role> persons = new ArrayList<>();

	public Paper() {
	}

	public Paper(String title, int released) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Collection<Role> getRoles() {
		return persons;
	}

	public void addRole(Role role) {
		this.persons.add(role);
	}
	
	public String getPaperId() {
		return paperId;
	}
	
	public int getYear() {
		return year;
	}

	public String getPaperAbstract() {
		return paperAbstract;
	}

	public String getVenue() {
		return venue;
	}

	public String getS2Url() {
		return s2Url;
	}
}
