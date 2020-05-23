package movieRental.core.service;

import movieRental.core.model.Client;
import movieRental.core.model.Movie;
import movieRental.core.repository.ClientRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepository clientRepository;

    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Override
    public List<Client> getAll() {
        log.trace("getAll clients - method entered");
        log.trace("getAll clients - method ended");
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client entity) {
        log.trace("save client - method entered");
        Client result = clientRepository.save(entity);
        log.debug("save - added ", result);
        log.trace("save client - method ended");
        return result;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.trace("delete client - method entered");
        AtomicBoolean deleted = new AtomicBoolean(false);
        clientRepository.findById(id).ifPresent(client -> {
            clientRepository.delete(client);
            deleted.set(true);
            log.debug("delete - deleted ", client);
        });
        log.trace("delete client - method ended");
        return deleted.get();
    }

    @Override
    @Transactional
    public Client update(Long id, Client entity) {
        log.trace("update client - method entered");
        clientRepository.findById(id).ifPresent(client -> {
            client.setFirstname(entity.getFirstname());
            client.setSecondname(entity.getSecondname());
            client.setJob(entity.getJob());
            client.setAge(entity.getAge());
            log.debug("update - updated ", client);
        });
        log.trace("update client - method ended");

        return entity;
    }

    @Override
    public List<Client> filterBySecondName(String value) {
        log.trace("filter clients by second name - method entered");
        log.trace("filter clients by second name- method ended");
        return clientRepository.findAll().stream()
                .filter(client -> client.getSecondname().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterByJob(String value) {
        log.trace("filter clients by job - method entered");
        log.trace("filter clients by job- method ended");
        return clientRepository.findAll().stream()
                .filter(client -> client.getJob().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterByAge(int age) {
        log.trace("filter clients by age - method entered");
        log.trace("filter clients by age- method ended");
        return clientRepository.findAll().stream()
                .filter(client -> client.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllSortedDescendingByFields(String... fields) {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", (Object[]) fields);
        Sort sort = new Sort(Sort.Direction.DESC, fields);
        Iterable<Client> clients = clientRepository.findAll(sort);
        log.trace("getAllSortedDescendingByFields - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllSortedAscendingByFields(String... fields) {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", (Object[]) fields);
        Sort sort = new Sort(Sort.Direction.ASC, fields);
        Iterable<Client> clients = clientRepository.findAll(sort);
        log.trace("getAllSortedAscendingByFields - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterBy(Client example)
    {
        log.trace("filterBy - method entered: example={}", example);
        return clientRepository.findAll(Example.of(example));
    }

    @Override
    public List<Client> filter(Client client, Client sortClient) {
        log.trace("filter clients - method entered");
        log.trace("filter clients - method ended");
        Sort sort = buildSort(sortClient);
        if (client.getJob() == null || client.getAge() == -1 )
            return clientRepository.findClientsByFirstnameContainingAndSecondnameContaining(client.getFirstname(), client.getSecondname(), sort);
        return clientRepository.findClientsByFirstnameContainingAndSecondnameContainingAndJobContainingAndAgeEquals(client.getFirstname(), client.getSecondname(), client.getJob(),client.getAge(), sort);
    }

    private Sort buildSort(Client sortClient) {
        List<String> attributes = new LinkedList<>();
        if (!sortClient.getFirstname().equals("-")) {
            attributes.add("firstname");
        }

        if (!sortClient.getSecondname().equals("-")) {
            attributes.add("secondname");
        }

        if (sortClient.getJob().equals("-")) {
            attributes.add("job");
        }

        if (sortClient.getAge() != 0) {
            attributes.add("age");
        }

        if (sortClient.getId() == 1)
            return new Sort(Sort.Direction.ASC,  attributes);
        return new Sort(Sort.Direction.DESC,  attributes);

    }

    @Override
    public Page<Client> getPage(PageRequest pageRequest) {
        log.trace("get page clients - method entered");
        log.trace("get page clients - method ended");
        return clientRepository.findAll(pageRequest);
    }

    @Override
    public List<Client> getSorted(String params) {
        log.trace("sort clients - method entered");
        log.trace("sort clients - method ended");
        return clientRepository.findAll(Sort.by(params.split("_")));
    }
}
