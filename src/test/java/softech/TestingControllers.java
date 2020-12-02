package softech;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import hu.elte.softech.controller.UserController;
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.UserRepository;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class TestingControllers {

    @Autowired
    private UserController userC;

    @Autowired
    private UserRepository userR;

//    @Mock
//    private UserRepository userR;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }

	@Test
    public void testGetUserById() {
        User u = new User();
        u.setId(303L);
        //when(userR.findById(303L).get()).thenReturn(u);

        User user = userC.getOneUserByUserId(303L);

        //verify(userR).findById(303l).get();
        assertThat(user).isNotNull();
        //Assert.assertEquals(303L, user.getId().longValue());
    }

	@Test
	public void test1() {
		User testu = new User();
		testu.setEmail("test@");
		testu.setPassword("123");
		testu.setUsername("testu");
		testu.setRole("tester");
		Long testid = userR.save(testu).getId();
		System.out.println(testid);

		assertThat(userC.getOneUserByUsername("testu")).isNotNull();
	}

}
