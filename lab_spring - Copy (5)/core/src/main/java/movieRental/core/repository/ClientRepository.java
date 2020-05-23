package movieRental.core.repository;

import movieRental.core.model.Client;
import movieRental.core.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {
    List<Client> findClientsByFirstnameContainingAndSecondnameContaining(String firstname, String secondname, Sort sort);

    List<Client> findClientsByFirstnameContainingAndSecondnameContainingAndJobContainingAndAgeEquals(String firstname, String secondname, String job, int age, Sort sort);

}

