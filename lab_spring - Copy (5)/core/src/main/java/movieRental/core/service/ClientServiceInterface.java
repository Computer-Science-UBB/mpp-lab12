package movieRental.core.service;

import movieRental.core.model.Client;
import movieRental.core.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ClientServiceInterface extends BaseServiceInterface<Client, Long> {

    List<Client> filterBy(Client example);
    List<Client> filterBySecondName(String value);
    List<Client> filterByJob(String value);
    List<Client> filterByAge(int age);
    List<Client> getAllSortedDescendingByFields(String... fields);
    List<Client> getAllSortedAscendingByFields(String... fields);
    Page<Client> getPage(PageRequest pageRequest);
    List<Client> filter(Client client, Client sortClient);
}
