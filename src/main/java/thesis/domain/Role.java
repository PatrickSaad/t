package thesis.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@RelationshipEntity(type = "AUTHOR_IN")
public class Role {

	@GraphId
	private Long id;

	@StartNode
	private Person person;

	@EndNode
	private Paper paper;

	public Role() {
	}

	public Role(Paper paper, Person person) {
		this.paper = paper;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public Paper getPaper() {
		return paper;
	}
}
