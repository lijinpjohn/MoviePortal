package com.ms.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ms.dao.UsersDao;
import com.ms.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UsersServicesImplTest {

	// object under test
	private UsersServicesImpl service;

	// mock objects
	@Mock
	private UsersDao mockUsersDao;

	@Before
	public void setup() {

		service = new UsersServicesImpl();
		service.setUsersDao(mockUsersDao);
	}

	@Test
	public void testGetUserById() throws Exception {

		final String userEmail = "junituser@gm.com";
		User user = new User();
		user.setEmail("email@abc.com");
		user.setfName("name");
		user.setlName("john");
		user.setUsertype("REG");

		Mockito.when(mockUsersDao.getUserById(userEmail)).thenReturn(user);
		User actualuser = service.getUserById(userEmail);

		Assert.assertNotNull("user must not be NULL", actualuser);
		Assert.assertEquals(user, actualuser);
	}

}
