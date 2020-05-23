package controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import converter.BaseConverter;
import dto.ClientsDto;
import dto.MovieDto;
import dto.RentalDto;
import dto.RentalsDto;
import movieRental.core.model.Rental;
import movieRental.core.service.RentalServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RentalController {

    @Autowired
    private RentalServiceInterface rentalService;

    @Autowired
    private BaseConverter<Rental, RentalDto> rentalConverter;

    public static final Logger log = LoggerFactory.getLogger(RentalController.class);

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    List<RentalDto> getAll(){
        log.trace("getAll --- method entered");
        log.trace("getAll --- method ended");
        return rentalConverter.
                convertModelsToDtos(rentalService.getAll());
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    RentalDto save(@RequestBody RentalDto rentalDto){
        log.trace("save --- method entered");
        log.trace("save --- method ended");
        return rentalConverter.convertModelToDto(rentalService.save(
                rentalConverter.convertDtoToModel(rentalDto)));
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.PUT)
    RentalDto update(@PathVariable Long id, @RequestBody RentalDto rentalDto){
        log.trace("update --- method entered");
        log.trace("update --- method ended");
        return rentalConverter.convertModelToDto(rentalService.update(id,
                rentalConverter.convertDtoToModel(rentalDto)));
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        log.trace("delete --- method entered");
        log.trace("delete --- method ended");
        if (rentalService.deleteById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/rentals/{clientID}/{movieID}", method = RequestMethod.GET)
    List<RentalDto> filter(@PathVariable Long clientID, @PathVariable Long movieID) {
        return rentalConverter.
                convertModelsToDtos(rentalService.filter(clientID, movieID));
    }

    @RequestMapping(value = "/rentals/sort/{params}", method = RequestMethod.GET)
    List<RentalDto> sort(@PathVariable String params) {
        return rentalConverter
                .convertModelsToDtos(rentalService.getSorted(params));
    }
}
