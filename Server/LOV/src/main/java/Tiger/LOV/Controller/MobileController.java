package Tiger.LOV.Controller;

import Tiger.LOV.Exception.UserNotFoundException;
import Tiger.LOV.Model.User;
import Tiger.LOV.Service.ResponseMsg;
import Tiger.LOV.Service.UserService;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping
public class MobileController {
  @Autowired
  private UserService userService;

  @GetMapping(path = "/user")
  public List<User> retrieveAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping(path = "/user/{id}")
  public User retrieveUser(@PathVariable long id){
    User user = userService.findUserById(id);
    if(user == null){
      throw new UserNotFoundException("id-" + id);
    }
    return user;
  }

  @PostMapping(path = "/user/register")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    try {
      userService.saveUser(user);
      // CREATED
      // /user/4
      URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(user.getId())
          .toUri();

      return ResponseEntity.created(location).build();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping(path = "/user/login")
  public ResponseEntity<Object> login(@RequestBody User user) {
    User thisUser = userService.auth(user.getUsername(), user.getPassword());
    System.out.println(user.getPassword());
    return null;
  }

  @DeleteMapping(path = "/user/{id}")
  public void deleteUser(@PathVariable long id){
    if(userService.findUserById(id) == null)
      throw new UserNotFoundException("There is no user with id:" + id);

    userService.deleteUserByid(id);
  }
}
