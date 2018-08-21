package com.aayush.pim.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aayush.pim.entity.Guest;

@Repository
public interface IGuestRepository extends PagingAndSortingRepository<Guest, Long> {

//  public Guest findOne(Long id);
}