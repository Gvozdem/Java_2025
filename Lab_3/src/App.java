import java.util.*;

class Cinema {
    String name;
    List<Hall> halls;

    Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    void addHall(Hall hall) {
        halls.add(hall);
    }
}

class Hall {
    String name;
    Seat[][] seats;
    List<Session> sessions;

    Hall(String name, int rows, int columns) {
        this.name = name;
        this.seats = new Seat[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = new Seat();
            }
        }
        this.sessions = new ArrayList<>();
    }

    void addSession(Session session) {
        sessions.add(session);
    }

    void printSeatingPlan() {
        System.out.println("\nSeating plan for hall: " + name);
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j].isBooked ? "X " : "O ");
            }
            System.out.println();
        }
    }
}

class Seat {
    boolean isBooked;

    void book() {
        isBooked = true;
    }
}

class Session {
    String movie;
    String time;
    int duration;
    Hall hall;

    Session(String movie, String time, int duration, Hall hall) {
        this.movie = movie;
        this.time = time;
        this.duration = duration;
        this.hall = hall;
    }
}

class TicketSystem {
    List<Cinema> cinemas;

    TicketSystem() {
        cinemas = new ArrayList<>();
    }

    void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    void listCinemas() {
        if (cinemas.isEmpty()) {
            System.out.println("No cinemas available.");
            return;
        }
        System.out.println("\nAvailable cinemas:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }
    }

    void findNextAvailableSession(String movie) {
        String closestTime = null;
        Cinema closestCinema = null;
        Hall closestHall = null;

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.halls) {
                for (Session session : hall.sessions) {
                    if (session.movie.equals(movie) && hasAvailableSeats(hall)) {
                        if (closestTime == null || isEarlier(session.time, closestTime)) {
                            closestTime = session.time;
                            closestCinema = cinema;
                            closestHall = hall;
                        }
                    }
                }
            }
        }

        if (closestTime != null) {
            System.out.println("Next available session of " + movie + " is at " + closestTime +
                    " in " + closestCinema.name + " - Hall " + closestHall.name);
        } else {
            System.out.println("No available sessions for " + movie + " with free seats.");
        }
    }

    private boolean hasAvailableSeats(Hall hall) {
        for (int i = 0; i < hall.seats.length; i++) {
            for (int j = 0; j < hall.seats[i].length; j++) {
                if (!hall.seats[i][j].isBooked) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isEarlier(String time1, String time2) {
        String[] parts1 = time1.split(":");
        String[] parts2 = time2.split(":");
        int hours1 = Integer.parseInt(parts1[0]);
        int minutes1 = Integer.parseInt(parts1[1]);
        int hours2 = Integer.parseInt(parts2[0]);
        int minutes2 = Integer.parseInt(parts2[1]);

        return hours1 < hours2 || (hours1 == hours2 && minutes1 < minutes2);
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketSystem ticketSystem = new TicketSystem();

        // ЛОГИН И ПАРОЛЬ
        String adminLogin = "admin";
        String adminPassword = "password";

        boolean applicationRunning = true;

        while (applicationRunning) {
            // Выбор роли
            System.out.println("Choose your role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("0. Exit");
            int roleChoice = getValidInteger(scanner);

            if (roleChoice == 1) {
                // Режим администратора
                boolean validCredentials = false;
                while (!validCredentials) {
                    System.out.print("\nEnter admin login (admin): ");
                    String login = scanner.nextLine();
                    System.out.print("Enter admin password (password): ");
                    String password = scanner.nextLine();

                    if (login.equals(adminLogin) && password.equals(adminPassword)) {
                        validCredentials = true;
                    } else {
                        System.out.println("Invalid login or password. Try again.");
                    }
                }

                boolean running = true;

                while (running) {
                    System.out.println("\nChoose an action:");
                    System.out.println("1. Add Cinema");
                    System.out.println("2. Add Hall to Cinema");
                    System.out.println("3. Create Movie Session");
                    System.out.println("9. Change User");
                    System.out.println("0. Exit");

                    int choice = getValidInteger(scanner);

                    switch (choice) {
                        case 1:
                            // Добавление кинотеатра
                            System.out.print("\nEnter cinema name: ");
                            String cinemaName = scanner.nextLine();
                            Cinema cinema = new Cinema(cinemaName);
                            ticketSystem.addCinema(cinema);
                            System.out.println("Cinema added successfully!");
                            break;

                        case 2:
                            // Добавление зала в кинотеатр
                            if (ticketSystem.cinemas.isEmpty()) {
                                System.out.println("No cinemas available. Please add a cinema first.");
                            } else {
                                ticketSystem.listCinemas();
                                System.out.print("Enter the number of the cinema to add hall: ");
                                int cinemaIndex = getValidInteger(scanner) - 1;
                                if (cinemaIndex >= 0 && cinemaIndex < ticketSystem.cinemas.size()) {
                                    Cinema existingCinema = ticketSystem.cinemas.get(cinemaIndex);
                                    System.out.print("Enter hall name: ");
                                    String hallName = scanner.nextLine();

                                    int rows;
                                    while (true) {
                                        System.out.print("Enter number of rows: ");
                                        rows = getValidInteger(scanner);
                                        if (rows > 0) {
                                            break;
                                        } else {
                                            System.out.println("Please enter a positive integer for rows.");
                                        }
                                    }
                                    int columns;
                                    while (true) {
                                        System.out.print("Enter number of columns: ");
                                        columns = getValidInteger(scanner);
                                        if (columns > 0) {
                                            break;
                                        } else {
                                            System.out.println("Please enter a positive integer for columns.");
                                        }
                                    }

                                    Hall hall = new Hall(hallName, rows, columns);
                                    existingCinema.addHall(hall);
                                    System.out.println("Hall added successfully!");
                                } else {
                                    System.out.println("Invalid cinema selection!");
                                }
                            }
                            break;

                        case 3:
                            // Создание сеанса фильма
                            if (ticketSystem.cinemas.isEmpty()) {
                                System.out.println("No cinemas available. Please add a cinema first.");
                            } else {
                                ticketSystem.listCinemas();
                                System.out.print("Enter the number of the cinema for session: ");
                                int sessionCinemaIndex = getValidInteger(scanner) - 1;
                                if (sessionCinemaIndex >= 0 && sessionCinemaIndex < ticketSystem.cinemas.size()) {
                                    Cinema cinemaForSessionObj = ticketSystem.cinemas.get(sessionCinemaIndex);
                                    if (cinemaForSessionObj.halls.isEmpty()) {
                                        System.out.println("No halls available in this cinema. Please add a hall first.");
                                    } else {
                                        System.out.println("Available halls:");
                                        for (int i = 0; i < cinemaForSessionObj.halls.size(); i++) {
                                            System.out.println((i + 1) + ". " + cinemaForSessionObj.halls.get(i).name);
                                        }
                                        System.out.print("Enter hall number for session: ");
                                        int hallForSessionIndex = getValidInteger(scanner) - 1;
                                        if (hallForSessionIndex >= 0 && hallForSessionIndex < cinemaForSessionObj.halls.size()) {
                                            Hall hallForSession = cinemaForSessionObj.halls.get(hallForSessionIndex);
                                            System.out.print("Enter movie name for session: ");
                                            String movieName = scanner.nextLine();
                                            
                                            String time;
                                            while (true) {
                                                System.out.print("Enter session time (HH:MM): ");
                                                time = scanner.nextLine();
                                                if (time.matches("([01]\\d|2[0-3]):[0-5]\\d")) {
                                                    break;
                                                } else {
                                                    System.out.println("Invalid time format. Please enter time in HH:MM format.");
                                                }
                                            }

                                            System.out.print("Enter duration (minutes): ");
                                            int duration = scanner.nextInt();
                                            Session session = new Session(movieName, time, duration, hallForSession);
                                            hallForSession.addSession(session);
                                            System.out.println("Session created successfully!");
                                        } else {
                                            System.out.println("Invalid hall selection!");
                                        }
                                    }
                                } else {
                                    System.out.println("Invalid cinema selection!");
                                }
                            }
                            break;

                        case 9:
                            // Смена пользователя
                            running = false;
                            System.out.println("Switching user...\n");
                            break;

                        case 0:
                            // Выход из приложения
                            running = false;
                            applicationRunning = false;
                            System.out.println("Exiting the application...");
                            break;

                        default:
                            System.out.println("Invalid choice! Please try again.\n");
                    }
                }
            } else if (roleChoice == 2) {
                // Режим пользователя
                boolean userRunning = true;

                while (userRunning) {
                    System.out.println("\nChoose an action:");
                    System.out.println("1. Book Ticket");
                    System.out.println("2. Find Next Available Session");
                    System.out.println("9. Change User");
                    System.out.println("0. Exit");

                    int userChoice = getValidInteger(scanner);

                    switch (userChoice) {
                        case 1:
                            // Бронирование билета
                            if (ticketSystem.cinemas.isEmpty()) {
                                System.out.println("No cinemas available for booking tickets.");
                            } else {
                                // Выбор фильма
                                Set<String> movies = new HashSet<>();
                                for (Cinema cinema : ticketSystem.cinemas) {
                                    for (Hall hall : cinema.halls) {
                                        for (Session session : hall.sessions) {
                                            movies.add(session.movie);
                                        }
                                    }
                                }

                                if (movies.isEmpty()) {
                                    System.out.println("No movies available for booking.");
                                    break;
                                }

                                System.out.println("\nAvailable movies:");
                                List<String> movieList = new ArrayList<>(movies);
                                for (int i = 0; i < movieList.size(); i++) {
                                    System.out.println((i + 1) + ". " + movieList.get(i));
                                }
                                System.out.print("Select a movie (number): ");
                                int movieChoice = getValidInteger(scanner) - 1;

                                if (movieChoice >= 0 && movieChoice < movieList.size()) {
                                    String selectedMovie = movieList.get(movieChoice);

                                    // Получение кинотеатров с доступными сеансами для выбранного фильма
                                    List<Cinema> availableCinemas = new ArrayList<>();
                                    for (Cinema cinema : ticketSystem.cinemas) {
                                        for (Hall hall : cinema.halls) {
                                            for (Session session : hall.sessions) {
                                                if (session.movie.equals(selectedMovie)) {
                                                    availableCinemas.add(cinema);
                                                    break; // Выход из цикла, если фильм найден
                                                }
                                            }
                                        }
                                    }

                                    if (availableCinemas.isEmpty()) {
                                        System.out.println("No cinemas available for this movie.");
                                        break;
                                    }

                                    System.out.println("Available cinemas for " + selectedMovie + ":");
                                    for (int i = 0; i < availableCinemas.size(); i++) {
                                        System.out.println((i + 1) + ". " + availableCinemas.get(i).name);
                                    }
                                    System.out.print("Select a cinema (number): ");
                                    int cinemaChoice = getValidInteger(scanner) - 1;

                                    if (cinemaChoice >= 0 && cinemaChoice < availableCinemas.size()) {
                                        Cinema selectedCinema = availableCinemas.get(cinemaChoice);
                                        List<Hall> availableHalls = new ArrayList<>();

                                        // Показать доступные залы для выбранного фильма
                                        for (Hall hall : selectedCinema.halls) {
                                            for (Session session : hall.sessions) {
                                                if (session.movie.equals(selectedMovie)) {
                                                    availableHalls.add(hall);
                                                }
                                            }
                                        }

                                        if (availableHalls.isEmpty()) {
                                            System.out.println("No sessions available for this movie in the selected cinema.");
                                            break;
                                        }

                                        System.out.println("Available halls for " + selectedMovie + ":");
                                        for (int i = 0; i < availableHalls.size(); i++) {
                                            System.out.println((i + 1) + ". " + availableHalls.get(i).name);
                                        }
                                        System.out.print("Select a hall (number): ");
                                        int hallChoice = getValidInteger(scanner) - 1;

                                        if (hallChoice >= 0 && hallChoice < availableHalls.size()) {
                                            Hall selectedHall = availableHalls.get(hallChoice);
                                            boolean hallRunning = true;

                                            while (hallRunning) {
                                                System.out.println("\nChoose an action:");
                                                System.out.println("1. View Seating Plan");
                                                System.out.println("2. Book a Seat");
                                                System.out.println("9. Go Back");
                                                int hallAction = getValidInteger(scanner);

                                                switch (hallAction) {
                                                    case 1:
                                                        // Просмотр схемы зала
                                                        selectedHall.printSeatingPlan();
                                                        break;

                                                    case 2:
                                                        // Бронирование места
                                                        System.out.print("Enter row number: ");
                                                        int row = getValidInteger(scanner) - 1;
                                                        System.out.print("Enter column number: ");
                                                        int column = getValidInteger(scanner) - 1;

                                                        if (row >= 0 && row < selectedHall.seats.length &&
                                                            column >= 0 && column < selectedHall.seats[row].length) {
                                                            if (!selectedHall.seats[row][column].isBooked) {
                                                                selectedHall.seats[row][column].book();
                                                                System.out.println("Seat booked successfully!");
                                                            } else {
                                                                System.out.println("This seat is already booked.");
                                                            }
                                                        } else {
                                                            System.out.println("Invalid seat selection.");
                                                        }
                                                        break;

                                                    case 9:
                                                        // Вернуться назад
                                                        hallRunning = false;
                                                        break;

                                                    default:
                                                        System.out.println("Invalid choice! Please try again.");
                                                }
                                            }
                                        } else {
                                            System.out.println("Invalid hall selection!");
                                        }
                                    } else {
                                        System.out.println("Invalid cinema selection!");
                                    }
                                } else {
                                    System.out.println("Invalid movie selection!");
                                }
                            }
                            break;

                        case 2:
                            // Найти ближайший сеанс (c 00:00)
                            System.out.print("Enter the movie name to find the next available session: ");
                            String movieToFind = scanner.nextLine();
                            ticketSystem.findNextAvailableSession(movieToFind);
                            break;

                        case 9:
                            // Смена пользователя
                            userRunning = false;
                            System.out.println("Switching user...");
                            break;

                        case 0:
                            // Выход из приложения
                            userRunning = false;
                            applicationRunning = false;
                            System.out.println("Exiting the application...");
                            break;

                        default:
                            System.out.println("Invalid choice! Please try again.");
                    }
                }
            } else if (roleChoice == 0) {
                applicationRunning = false;
                System.out.println("Exiting the application...");
            } else {
                System.out.println("Invalid role selection.");
            }
        }

        scanner.close();
    }

    // Метод для проверки корректности ввода числа
    private static int getValidInteger(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}