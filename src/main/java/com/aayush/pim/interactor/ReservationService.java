package com.aayush.pim.interactor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aayush.pim.entity.Guest;
import com.aayush.pim.entity.Reservation;
import com.aayush.pim.entity.Room;
import com.aayush.pim.model.RoomReservationModel;
import com.aayush.pim.repository.IGuestRepository;
import com.aayush.pim.repository.IReservationRepository;
import com.aayush.pim.repository.IRoomRepository;

@Service
public class ReservationService {
  
  @Autowired
  private IRoomRepository        roomRepository;
  
  @Autowired
  private IGuestRepository       guestRepository;
  
  @Autowired
  private IReservationRepository reservationRepository;
  
  public List<RoomReservationModel> getRoomReservationsForDate(Date date)
  {
    Iterable<Room> rooms = roomRepository.findAll();
    Map<Long, RoomReservationModel> roomReservationMap = new HashMap<>();
    rooms.forEach(room->{
        RoomReservationModel roomReservation = new RoomReservationModel();
        roomReservation.setRoomId(room.getId());
        roomReservation.setRoomName(room.getName());
        roomReservation.setRoomNumber(room.getNumber());
        roomReservationMap.put(room.getId(), roomReservation);
    });
    Iterable<Reservation> reservations = reservationRepository.findByDate(new java.sql.Date(date.getTime()));

    if(null != reservations) {
        reservations.forEach(reservation -> {
            Guest guest = guestRepository.findById(reservation.getGuestId()).get();
            if(null != guest) {
                RoomReservationModel roomReservation = roomReservationMap.get(reservation.getId());
                roomReservation.setDate(date);
                roomReservation.setFirstName(guest.getFirstName());
                roomReservation.setLastName(guest.getLastName());
                roomReservation.setGuestId(guest.getId());
            }
        });
    }
    
    List<RoomReservationModel> roomReservations = new ArrayList<>();
    for(Long roomId:roomReservationMap.keySet()) {
        roomReservations.add(roomReservationMap.get(roomId));
    }
    return roomReservations;
  }
}
