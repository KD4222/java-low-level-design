package services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import models.Order;
import models.Reservation;
import repo.OrderRepository;

/**
 * ExpiryScheduler
 */
public class ExpiryScheduler {
    private final ScheduledExecutorService scheduler;
    private final ReservationService reservationService;
    private final OrderRepository orderRepository;

    public ExpiryScheduler(int threads,ReservationService reservationService,OrderRepository orderRepo){
        scheduler= Executors.newScheduledThreadPool(threads);
        this.reservationService= reservationService;
        orderRepository=orderRepo;
    }

    public void scheduleExpiry(String reservationId, long expiresAt){
        long delay=Math.max(0, expiresAt - System.currentTimeMillis());

        scheduler.schedule(()->{
            try{Reservation reservation = reservationService.getReservation(reservationId);
            boolean expired = reservationService.expireReservation(reservationId);
            if(expired){
                Order order= orderRepository.get(reservation.getOrderId());
                order.markExpired();
            }}catch(Exception e){
                //log
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void shutdown(){
        scheduler.shutdown();
    }

}
