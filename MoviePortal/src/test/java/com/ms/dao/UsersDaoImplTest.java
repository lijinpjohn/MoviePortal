package com.ms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ms.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UsersDaoImplTest {

	// object under test
	private UsersDaoImpl dao;

	// mock objects
	@Mock
	SessionFactory mockSessionFactory;
	@Mock
	Session mockSession;
	@Mock
	Transaction tx;

	@Before
	public void setup() {

		dao = new UsersDaoImpl();
		// dao.session = mockSession;
		dao.sessionFactory = mockSessionFactory;
	}

	@Test
	public void testGetUserById() throws Exception {
		final String email = "email@abc.com";
		User user = new User();
		user.setEmail("email@abc.com");
		user.setfName("name");
		user.setlName("john");
		user.setUsertype("REG");

		Mockito.when(mockSessionFactory.openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.beginTransaction()).thenReturn(tx);
		Mockito.when(mockSession.load(User.class, "email@abc.com")).thenReturn(user);
		User actualUser = dao.getUserById(email);

		Assert.assertNotNull(actualUser);
		Assert.assertEquals(user, actualUser);
	}

}
