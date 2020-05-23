package view;

import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class View {

    @Autowired
    private RestTemplate restTemplate;

    private static final String clientsURL = "http://localhost:8080/api/clients";
    private static final String moviesURL = "http://localhost:8080/api/movies";
    private static final String rentalsURL = "http://localhost:8080/api/rentals";

    private Scanner scanner = new Scanner(System.in);

    public void run(){
        while(true){
            printConsole();
            int option = scanner.nextInt();
            System.out.println(option);
            switch (option){
                case 0:
                    return;
                case 1:
                    addAction();
                    break;
                case 2:
                    showAction();
                    break;
                case 3:
                    filterAction();
                    break;
                case 4:
                    deleteAction();
                    break;
                case 5:
                    updateAction();
                    break;
            }
        }
    }

    private void updateAction() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                updateClient();
                break;
            case 2:
                updateMovie();
                break;
            case 3:
                updateRental();
                break;
        }
    }

    private void updateClient() {
        printAllClient();
        System.out.println("Give the ID of the client you want to update \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new firstname for this client \n >");
        String newClientName = scanner.nextLine();
        System.out.println("Enter a new secondname for this client \n >");
        String newSecondName = scanner.nextLine();
        System.out.println("Enter a new job for this client \n >");
        String newJob = scanner.nextLine();
        System.out.println("Enter a new age for this movie \n >");
        int newAge = scanner.nextInt();
        restTemplate.put(clientsURL + "/{id}",
                new ClientDto(newClientName,newSecondName,newJob,newAge), id);
        System.out.println("Done!\n");
    }

    private void updateMovie() {
        printAllMovies();
        System.out.println("Give the ID of the movie you want to update \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new name for this movie \n >");
        String newTitle = scanner.nextLine();
        System.out.println("Enter a new description for this movie \n >");
        String newDescription = scanner.nextLine();
        System.out.println("Enter a new rating for this movie \n >");
        int newRating = scanner.nextInt();
        System.out.println("Enter a new price for this movie \n >");
        int newPrice = scanner.nextInt();
        scanner.nextLine();

        restTemplate.put(moviesURL + "/{id}",
                new MovieDto(newTitle, newDescription, newRating, newPrice), id);

        System.out.println("Done!\n");
    }

    private void updateRental() {
        printAllRentals();
        System.out.println("Give the ID of the rental you want to update \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new Movie id \n >");
        Long movieId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new Client id \n >");
        Long clientId = scanner.nextLong();
        scanner.nextLine();

        restTemplate.put(rentalsURL + "/{id}",
                new RentalDto(clientId, movieId), id);

        System.out.println("Done!\n");
    }

    private void deleteAction() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Give the ID of the entity you want to delete \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        switch (option){
            case 1:
                deleteClient(id);
                break;
            case 2:
                deleteMovie(id);
                break;
            case 3:
                deleteRental(id);
                break;
        }
    }

    private void deleteClient(Long id) {
        restTemplate.delete(clientsURL + "/delete/{id}", id);
        System.out.println("Done!\n");
    }

    private void deleteMovie(Long id) {
        restTemplate.delete(moviesURL + "/{id}", id);
        System.out.println("Done!\n");
    }

    private void deleteRental(Long id) {
        restTemplate.delete(rentalsURL + "/{id}", id);
        System.out.println("Done!\n");
    }


    private void filterAction() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                printFilterOptionsClinets();
                int option2 = scanner.nextInt();
                scanner.nextLine();
                switch (option2){
                    case 1:
                        System.out.println("Firts Name:");
                        String firstName = scanner.nextLine();
                        filterClients(firstName);
                        break;
                    case 2:
                        System.out.println("Second Name:");
                        String secondName = scanner.nextLine();
                        filterClientsBySecondName(secondName);
                        break;
                    case 3:
                        System.out.println("Job:");
                        String job = scanner.nextLine();
                        filterClientsByJob(job);
                        break;
                    case 4:
                        System.out.println("Age:");
                        int age = scanner.nextInt();
                        filterClientsByAge(age);
                        break;
                }

                break;
            case 2:
                printFilterOptionsMovies();
                int option3 = scanner.nextInt();
                scanner.nextLine();
                switch (option3) {
                    case 1:
                        System.out.println("Title:");
                        String title = scanner.nextLine();
                        filterMoviesByTitle(title);
                        break;
                    case 2:
                        System.out.println("Description:");
                        String description = scanner.nextLine();
                        filterMoviesByDescription(description);
                        break;
                    case 3:
                        System.out.println("Price:");
                        int price = scanner.nextInt();
                        filterMoviesByPrice(price);
                        break;
                    case 4:
                        System.out.println("Rating:");
                        int rating = scanner.nextInt();
                        filterMoviesByRating(rating);
                        break;
                }
                break;
            case 3:
                printFilterOptionsRating();
                int option4 = scanner.nextInt();
                scanner.nextLine();
                switch (option4) {
                    case 1:
                        System.out.println("Client Id:");
                        long clientId = scanner.nextLong();
                        filterRentalsByClient(clientId);
                        break;
                    case 2:
                        System.out.println("Movie Id:");
                        long movieId = scanner.nextLong();
                        filterRentalsByMovies(movieId);
                        break;
                }
                break;

        }
    }

    private void showAction() {
        printEntityOption();
        int option = scanner.nextInt();
        switch (option){
            case 1:
                printAllClient();
                break;
            case 2:
                printAllMovies();
                break;
            case 3:
                printAllRentals();
        }
    }

    private void addAction() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(option);
        switch (option){
            case 1:
                addClient();
                break;
            case 2:
                addMovie();
                break;
            case 3:
                addRental();
        }
    }


    private void printConsole(){
        System.out.println("Choose your option: " +
                "\n\t1.Add" +
                "\n\t2.Show" +
                "\n\t3.Filter" +
                "\n\t4.Delete" +
                "\n\t5.Update" +
                "\n\t6.Sort" +
                "\n\t0.Exit");
    }

    private void printEntityOption(){
        System.out.println("Choose your option: " +
                "\n\t1.Clients" +
                "\n\t2.Movies" +
                "\n\t3.Rentals");
    }

    private void printFilterOptionsClinets(){
        System.out.println("Choose your option: " +
                "\n\t1.By First Name" +
                "\n\t2.By Second Name" +
                "\n\t3.By Job"+
                "\n\t4.By Age");
    }

    private void printFilterOptionsMovies(){
        System.out.println("Choose your option: " +
                "\n\t1.By Title" +
                "\n\t2.By Description" +
                "\n\t3.By Price"+
                "\n\t4.By Rating");
    }

    private void printFilterOptionsRating(){
        System.out.println("Choose your option: " +
                "\n\t1.By Client id" +
                "\n\t2.By Movie id");
    }

    private void printAllClient(){
        ClientsDto clients = restTemplate.getForObject(clientsURL, ClientsDto.class);
        System.out.println("Clients:\n\t"+ clients.toString());
    }

    private void printAllMovies(){
        MoviesDto movies = restTemplate.getForObject(moviesURL, MoviesDto.class);
        System.out.println("Movies:\n\t" + movies.toString());
    }

    private void printAllRentals(){
        RentalsDto rentals = restTemplate.getForObject(rentalsURL, RentalsDto.class);
        System.out.println("Rentals:\n\t" + rentals.toString());
    }

    private void filterClients(String name){
        ClientsDto clients = restTemplate.getForObject(clientsURL + "/firstname/{name}", ClientsDto.class, name);
        System.out.println("Clients with first name like %" + name + "%\n\t"
                + clients.toString());
    }
    private void filterClientsBySecondName(String name){
        ClientsDto clients = restTemplate.getForObject(clientsURL + "/secondname/{name}", ClientsDto.class, name);
        System.out.println("Clients with second name like %" + name + "%\n\t"
                + clients.toString());
    }
    private void filterClientsByJob(String name){
        ClientsDto clients = restTemplate.getForObject(clientsURL + "/job/{name}", ClientsDto.class, name);
        System.out.println("Clients with job like %" + name + "%\n\t"
                + clients.toString());
    }

    private void filterClientsByAge(int age){
        ClientsDto clients = restTemplate.getForObject(clientsURL + "/age/{age}", ClientsDto.class, age);
        System.out.println("Clients with age like %" + age + "%\n\t"
                + clients.toString());
    }

    private void filterMoviesByDescription(String description){
        MoviesDto movies = restTemplate.getForObject(moviesURL + "/description/{description}", MoviesDto.class, description);
        System.out.println("Movies with description like %" + description + "%\n\t"
                + movies.toString());
    }

    private void filterMoviesByTitle(String title){
        MoviesDto movies = restTemplate.getForObject(moviesURL + "/title/{title}", MoviesDto.class, title);
        System.out.println("Movies with title like %" + title + "%\n\t"
                + movies.toString());
    }

    private void filterMoviesByPrice(int price){
        MoviesDto movies = restTemplate.getForObject(moviesURL + "/price/{price}", MoviesDto.class, price);
        System.out.println("Movies with price like %" + price + "%\n\t"
                + movies.toString());
    }

    private void filterMoviesByRating(int rating){
        MoviesDto movies = restTemplate.getForObject(moviesURL + "/rating/{rating}", MoviesDto.class, rating);
        System.out.println("Movies with rating like %" + rating + "%\n\t"
                + movies.toString());
    }

    private void filterRentalsByClient(Long id){
        RentalsDto rentals = restTemplate.getForObject(rentalsURL + "/clientId/{id}", RentalsDto.class, id);
        System.out.println("Rentals of client with id: " + id + "\n\t"
                + rentals.toString());
    }

    private void filterRentalsByMovies(Long id){
        RentalsDto rentals = restTemplate.getForObject(rentalsURL + "/movieId/{id}", RentalsDto.class, id);
        System.out.println("Rentals of movie with id: " + id + "\n\t"
                + rentals.toString());
    }

    private void addClient(){
        System.out.println("FirstName:");
        String name = scanner.nextLine();
        System.out.println("SecondName:");
        String secondName = scanner.nextLine();
        System.out.println("Job:");
        String job = scanner.nextLine();
        System.out.println("Age:");
        int age = scanner.nextInt();
        ClientDto savedClient = restTemplate.postForObject(
                clientsURL,
                new ClientDto(name,secondName,job,age),
                ClientDto.class);
        assert savedClient != null;
        System.out.println("saved: " + savedClient.toString());
    }

    private void addMovie(){
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Description:");
        String description = scanner.nextLine();
        System.out.println("Rating:");
        int rating = scanner.nextInt();
        System.out.println("Price:");
        int price = scanner.nextInt();
        MovieDto savedMovie = restTemplate.postForObject(
                moviesURL,
                new MovieDto(title, description, rating, price),
                MovieDto.class);
        assert savedMovie != null;
        System.out.println("saved: " + savedMovie.toString());
    }

    private void addRental(){
        System.out.println("Client ID:");
        Long clientID = scanner.nextLong();
        System.out.println("Movie ID:");
        Long movieID = scanner.nextLong();
        RentalDto savedRental = restTemplate.postForObject(
                rentalsURL,
                new RentalDto(clientID, movieID),
                RentalDto.class);
        assert savedRental != null;
        System.out.println("saved: " + savedRental.toString());
    }
}