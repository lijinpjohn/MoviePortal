package com.ms.dao;

import java.util.List;

import com.ms.model.Rent;

public interface RentsDao {

	List<Rent> getRentsByEmail(String email) throws Exception;

	boolean addRents(Rent rents) throws Exception;

}
