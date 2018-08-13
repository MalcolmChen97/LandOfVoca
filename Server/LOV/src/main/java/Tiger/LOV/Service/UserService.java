package Tiger.LOV.Service;

import Tiger.LOV.Model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public User findUserByUserName(String userName);
    public User findUserByNickName(String nickName);
    public void saveUser(User user);
    public User auth(String userName, String password);
}
