package Tiger.LOV.Service;

import Tiger.LOV.Model.User;

import java.util.List;

public interface UserService {
    public User findUserByEmail(String email);
    public User findUserByUserName(String userName);
    public User findUserByNickName(String nickName);
    public User findUserById(long id);
    public List<User> findAllUsers();
    public void saveUser(User user);
    public User auth(String userName, String password);
    public void deleteUserByid(long id);
}
