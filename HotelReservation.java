import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }
}

class Reservation {
    private int reservationNumber;
    private String userName;
    private Room room;

    public Reservation(int reservationNumber, String userName, Room room) {
        this.reservationNumber = reservationNumber;
        this.userName = userName;
        this.room = room;
        room.bookRoom();
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public String getUserName() {
        return userName;
    }

    public Room getRoom() {
        return room;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationNumber;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationNumber = 1;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equals(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(String userName, Room room) {
        Reservation reservation = new Reservation(nextReservationNumber++, userName, room);
        reservations.add(reservation);
        return reservation;
    }

    public Reservation findReservation(int reservationNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationNumber() == reservationNumber) {
                return reservation;
            }
        }
        return null;
    }
}

public class HotelReservation {
    public static void main(String[] args) {
        // Create rooms and add them to the hotel
        Hotel hotel = new Hotel();
        hotel.addRoom(new Room(101, "Standard"));
        hotel.addRoom(new Room(102, "Standard"));
        hotel.addRoom(new Room(201, "Deluxe"));
        hotel.addRoom(new Room(202, "Deluxe"));

        //  user actions
        List<Room> availableRooms = hotel.getAvailableRooms("Standard");
        if (!availableRooms.isEmpty()) {
            Room selectedRoom = availableRooms.get(0);
            System.out.println("Room " + selectedRoom.getRoomNumber() + " is available for reservation.");
            Reservation reservation = hotel.makeReservation("John Doe", selectedRoom);
            System.out.println("Reservation successful. Reservation number: " + reservation.getReservationNumber());
        } else {
            System.out.println("No standard rooms available.");
        }

        // viewing booking details
        Reservation foundReservation = hotel.findReservation(1);
        if (foundReservation != null) {
            System.out.println("Booking details:");
            System.out.println("Reservation number: " + foundReservation.getReservationNumber());
            System.out.println("Guest name: " + foundReservation.getUserName());
            System.out.println("Room number: " + foundReservation.getRoom().getRoomNumber());
        } else {
            System.out.println("Reservation not found.");
        }
    }
}