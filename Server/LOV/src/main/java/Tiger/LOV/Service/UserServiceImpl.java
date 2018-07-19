package Tiger.LOV.Service;

import Tiger.LOV.DAO.UserRepository;
import Tiger.LOV.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByNickName(String nickName) { return userRepository.findByNickname(nickName); }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String userName) {return userRepository.findByUsername(userName); }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User auth(String userName, String password) {
        User thisUser = findUserByUserName(userName);
        if(thisUser != null && thisUser.getPassword().equals(password)) {
            return thisUser;
        }else{
            //TODO::should throw exception here!
            return null;
        }
    }

  @Override
  public User findUserById(long id) {
    return userRepository.findById(id);
  }

  @Override
  public List<User> findAllUsers() {
      List<User> allUsers = new ArrayList<>();
      Iterator<User> userIterator = userRepository.findAll().iterator();
      while (userIterator.hasNext()){
        allUsers.add(userIterator.next());
      }
      return allUsers;
  }

  @Override
  public void deleteUserByid(long id) {
      userRepository.deleteById(id);
  }
}
