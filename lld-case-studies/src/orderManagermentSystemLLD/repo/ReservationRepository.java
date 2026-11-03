package repo;

import java.util.concurrent.ConcurrentHashMap;

import models.Reservation;

public class ReservationRepository {
    ConcurrentHashMap<String, Reservation> reservationMap;

    public ReservationRepository() {
        this.reservationMap = new ConcurrentHashMap<>();
    }

    public void save(Reservation reservation) {
        reservationMap.put(reservation.getReservationId(), reservation);
    }


    public Reservation get(String reservationId) {
        return reservationMap.get(reservationId);
    }
}
