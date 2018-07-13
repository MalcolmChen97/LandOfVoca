package Tiger.LOV.Service;

import Tiger.LOV.Model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
