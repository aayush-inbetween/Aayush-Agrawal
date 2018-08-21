package com.aayush.pim.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aayush.pim.entity.Reservation;

@Repository
public interface IReservationRepository extends CrudRepository<Reservation, Long> {
 
  public List<Reservation> findByDate(Date date);

}