package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    //1-й получить список всех пользователей
    List<User> getAllUsersDao();

    //2-й получить пользователя по ID
    User getUserByIdDao(long id) throws SQLException;

    //3-й проверить есть ли зарегистрированный пользователь с искомым именем
    boolean checkUserByNameDao(String name) throws SQLException;

    //4-й проверить есть ли зарегистрированный пользователь с искомым логином
     boolean checkUserByLoginDao(String login) throws SQLException;

    //5-й создать и добавить в базу нового пользователя
     void addUserDao(User user);

    //6-й обновить и записать в базу новые данные пользователя
     void updateUserDao(User user);

    //7-й удалить пользователя через ID
     void deleteUserByIdDao(Long id) throws SQLException;

    //8-й проверить есть ли зарегистрированный пользователь
    // с искомым login и password
    public User isExist(String login, String password);

}
