package thesis.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import thesis.domain.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
