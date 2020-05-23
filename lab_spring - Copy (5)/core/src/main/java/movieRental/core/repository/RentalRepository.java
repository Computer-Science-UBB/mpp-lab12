package movieRental.core.repository;

import movieRental.core.model.Rental;

import java.util.List;

public interface RentalRepository extends BaseRepository<Rental, Long> {

    List<Rental> getRentalsByClient_Id(Long clientID);

    List<Rental> getRentalsByMovie_Id(Long movieID);

    List<Rental> getRentalsByClient_IdAndAndMovie_Id(Long clientID, Long movieID);

}
