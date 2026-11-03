package services;

import java.util.UUID;

import models.Order;
import models.Reservation;
import repo.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepo;
    private final ReservationService reservationService;
    private final ExpiryScheduler expiryScheduler;


    public OrderService(OrderRepository orderRepo, services.ReservationService reservationService,
            services.ExpiryScheduler expiryScheduler) {
        this.orderRepo = orderRepo;
        this.reservationService = reservationService;
        this.expiryScheduler = expiryScheduler;
    }

    public String initiateOrder(String productId,int quantity,long reservationTtlMs){
        String orderId = UUID.randomUUID().toString();
        Reservation reservation= null;

        try{
            reservation = reservationService.createReservation(orderId, productId, quantity, reservationTtlMs);
            Order order = new Order(orderId, productId, quantity);
            order.markReserved(reservation.getReservationId());
            orderRepo.save(order);

            expiryScheduler.scheduleExpiry(reservation.getReservationId(), reservation.getExpiresAt());
            return orderId;
        }catch(Exception e){
            if(reservation!=null){
                reservationService.cancelReservation(reservation.getReservationId());
            }
            throw e;
        }
    }

    public boolean cancelOrder(String orderId){
        Order order=orderRepo.get(orderId);
        boolean success = reservationService.cancelReservation(order.getReservationId());

        if(success){
            order.markCancelled();
        }
        return success;
    }

    public boolean confirmOrder(String orderId){
        Order order = orderRepo.get(orderId);
        boolean success= reservationService.confirmReservation(order.getReservationId());

        if(success){
            order.markConfirmed();
        }
        return success;
    }

    public void markOrderExpired(String orderId){
        Order order= orderRepo.get(orderId);
        order.markExpired();
    }

    public String getOrder(String orderId){
        return orderRepo.get(orderId).toString();
    }
}
