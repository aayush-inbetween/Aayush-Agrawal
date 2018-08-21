package com.aayush.pim.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aayush.pim.entity.Room;
import com.aayush.pim.repository.IRoomRepository;

@RestController
public class RoomController {
  
  @Autowired
  private IRoomRepository repository;
  
  @RequestMapping(value = "/rooms", method = RequestMethod.GET)
  List<Room> findAll(@RequestParam(required = false) String roomNumber)
  {
    List<Room> rooms = new ArrayList<>();
    if (null == roomNumber) {
      Iterable<Room> results = this.repository.findAll();
      results.forEach(room -> {
        rooms.add(room);
      });
    }
    else {
      Room room = this.repository.findByNumber(roomNumber);
      if (null != room) {
        rooms.add(room);
      }
    }
    return rooms;
  }
}
