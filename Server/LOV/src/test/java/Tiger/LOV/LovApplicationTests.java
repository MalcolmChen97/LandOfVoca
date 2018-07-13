package Tiger.LOV;

import Tiger.LOV.Model.User;
import Tiger.LOV.Service.UserService;
import Tiger.LOV.Service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LovApplicationTests {
	@Autowired
	UserService dao;

	@Test
	public void contextLoads() {

	}

}
