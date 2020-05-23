package controller;

import converter.BaseConverter;
import dto.ClientDto;
import dto.ClientsDto;
import dto.MovieDto;
import movieRental.core.model.Client;
import movieRental.core.model.Movie;
import movieRental.core.service.ClientService;
import movieRental.core.service.ClientServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class ClientController {

    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private BaseConverter<Client, ClientDto> clientConverter;

    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    List<ClientDto> getClients(){
        log.trace("getAll --- method entered");
        log.trace("getAll --- method ended");
        List<Client> students = clientService.getAll();
        return new ArrayList<>(clientConverter.convertModelsToDtos(students));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto save(@RequestBody ClientDto clientDto){
        log.trace("save --- method entered");
        log.trace("save --- method ended");
        return clientConverter.convertModelToDto(clientService.save(
                clientConverter.convertDtoToModel(clientDto)));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto update(@PathVariable Long id,@RequestBody ClientDto clientDto){
        log.trace("update --- method entered");
        log.trace("update --- method ended");
        return  clientConverter.convertModelToDto(clientService.update(id,
                clientConverter.convertDtoToModel(clientDto)));
    }

    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        log.trace("delete --- method entered");
        log.trace("delete --- method ended");
        if (clientService.deleteById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clients/filter", method  = RequestMethod.GET)
    List<ClientDto> filterBy(@RequestParam Optional<String> firstName, @RequestParam Optional<String> secondName, @RequestParam Optional<String> job, @RequestParam Optional<Integer> age)
    {
        log.trace("filterBy - method entered: firstName={}, secondName={}, job={}, age={}", firstName, secondName, job, age);

        Client client = new Client();

        firstName.ifPresent(client::setFirstname);
        secondName.ifPresent(client::setSecondname);
        job.ifPresent(client::setJob);
        age.ifPresent(client::setAge);

        return new ArrayList<>(clientConverter.convertModelsToDtos(clientService.filterBy(client)));
    }

    @RequestMapping(value = "/clients/{firstname}/{secondname}/{job}/{age}", method = RequestMethod.POST)
    List<ClientDto> filter(@PathVariable String firstname, @PathVariable String secondname, @PathVariable String job, @PathVariable int age, @RequestBody ClientDto sortClient) {
        Client auxClient = Client.builder()
                .firstname(firstname)
                .secondname(secondname)
                .job(job)
                .age(age)
                .build();
        return clientConverter.
                convertModelsToDtos(clientService.filter(auxClient,
                        clientConverter.convertDtoToModel(sortClient)));
    }

    @RequestMapping(value = "/clients/page", method = RequestMethod.GET)
    Page<ClientDto> getPage(@RequestParam(defaultValue = "0") int page) {
        PageRequest pageRequest = new PageRequest(page, 3);
        Page<Client> result = clientService.getPage(pageRequest);
        return new PageImpl<ClientDto>(
                result.getContent().stream()
                        .map(client -> clientConverter.convertModelToDto(client))
                        .collect(Collectors.toList()),
                pageRequest,
                result.getTotalPages());
    }



}
