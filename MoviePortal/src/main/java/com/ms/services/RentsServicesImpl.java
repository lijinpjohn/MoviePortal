package com.ms.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ms.dao.RentsDao;
import com.ms.model.Rent;
import com.ms.model.User;

public class RentsServicesImpl implements RentsServices {

	private RentsDao rentsDao;

	@Override
	public void rentMovie(Rent rents, User user) throws Exception {
		// assumes that registered user will have value 'REG' in the 'user_type'
		// assumes that regular user will have value 'NOR' in the 'user_type'
		if ("REG" == user.getUsertype()) {
			// calculating expiry date for registered users
			Calendar cal = Calendar.getInstance();
			cal.setTime(rents.getRentDate());
			cal.add(Calendar.DATE, 7);
			rents.setExpDate(cal.getTime());

			// calculating 20% discount registered user
			// assumes that actual price returned while browsing movies will be
			// passed to this method through JSON
			Integer discountamount = (Integer.parseInt(rents.getActualPrice())) * 80 / 100;
			rents.setAmountPaid(discountamount.toString());
		} else if ("NOR" == user.getUsertype()) {
			// calculating expiry date for registered users
			Calendar cal = Calendar.getInstance();
			cal.setTime(rents.getRentDate());
			cal.add(Calendar.DATE, 3);
			rents.setExpDate(cal.getTime());
			rents.setAmountPaid(rents.getActualPrice());
		}

		rentsDao.addRents(rents);
	}

	@Override
	public List<Rent> getRentsByEmail(String email) throws Exception {
		return rentsDao.getRentsByEmail(email);
	}

	@Autowired
	public void setRentsDao(RentsDao rentsDao) {
		this.rentsDao = rentsDao;
	}

}
