package com.ms.services;

import java.util.List;

import com.ms.model.Rent;
import com.ms.model.User;

public interface RentsServices {

	void rentMovie(Rent rents, User user) throws Exception;

	List<Rent> getRentsByEmail(String email) throws Exception;
}
