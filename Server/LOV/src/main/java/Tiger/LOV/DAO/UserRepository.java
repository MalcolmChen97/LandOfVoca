package Tiger.LOV.DAO;

        import Tiger.LOV.Model.User;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.RepositoryDefinition;
        import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername(String userName);
    User findByNickname(String nickName);
}
