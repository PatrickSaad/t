package thesis.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import thesis.domain.Paper;

@RepositoryRestResource(collectionResourceRel = "papers", path = "papers")
public interface PaperRepository extends PagingAndSortingRepository<Paper, Long> {

	Paper findByTitle(@Param("title") String title);

	Paper findByPaperId(@Param("paperId") String paperId);

	Collection<Paper> findByTitleLike(@Param("title") String title);

	//@Query("MATCH (m:Paper)<-[r:AUTHOR_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
	//Collection<Paper> graph(@Param("limit") int limit);
	
	@Query("MATCH (m:Paper) RETURN m LIMIT {limit}")
	Collection<Paper> getPapers(@Param("limit") int limit);
}