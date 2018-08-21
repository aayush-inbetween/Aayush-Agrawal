package com.aayush.pim.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aayush.pim.entity.Room;

@Repository
public interface IRoomRepository extends CrudRepository<Room, Long> {
  
  public Room findByNumber(String number);
}
