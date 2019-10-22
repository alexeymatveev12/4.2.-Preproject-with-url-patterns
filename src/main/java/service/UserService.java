package service;


import dao.UserDao;
import model.User;
import util.UserDaoFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

 //   private SessionFactory sessionFactory;

//    private UserService(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(new UserDaoFactory().getUserDao());
        }
        return userService;
    }


    public User getUserById(Long id) {
        User user = null;
        try {
            user = userDao.getUserByIdDao(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsersDao();
    }

    public boolean checkUserByName(String name) {

        try {
            userDao.checkUserByNameDao(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUserByLogin(String login) {

        try {
            return userDao.checkUserByLoginDao(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(User user) {
        // Проверка, существует ли клиент в сервисе. Добавить если нет
        if (user.getName() != null && user.getName().length() > 0
                && user.getLogin() != null && user.getLogin().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0)
     //   {
      //      if (checkUserByName(user.getName()) && (checkUserByLogin(user.getLogin()))) {
                userDao.addUserDao(user);
        //    }
        }



    public void deleteUserById(Long id) {

        try {
            userDao.deleteUserByIdDao(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        // Проверка, существует ли клиент в сервисе. Добавить если нет
        if (user.getName() != null && user.getName().length() > 0
                && user.getLogin() != null && user.getLogin().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {
            //     if (checkUserByName(user.getName())) {
            //         if (checkUserByLogin(user.getLogin())) {
            userDao.updateUserDao(user);
            //         }
            //     }
        }
    }

    public User isExist(String login, String password) {
        User user = null;
        user = userDao.isExist(login, password);
        return user;
    }


    /*
        public void cleanUp() throws DBException {
            UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.openSession());
            try {
                dao.dropTable();
            } catch (SQLException e) {
                throw new DBException(e);
            }
        }
        public void createTable() throws DBException{
            UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.openSession());
            try {
                dao.createTable();
            } catch (SQLException e) {
                throw new DBException(e);
            }
        }
    */
}