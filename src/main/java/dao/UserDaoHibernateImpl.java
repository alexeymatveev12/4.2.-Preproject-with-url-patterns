package dao;


import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

   // private Session session;

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //1-й получить список всех пользователей
    @Override
    public List<User> getAllUsersDao() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allUsers;

    }


    //2-й получить пользователя по ID
    @Override
    public User getUserByIdDao(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id = :userId");
        List<User> userList = query.setParameter("userId", id).list();
        User user = userList.get(0);

        transaction.commit();
        session.close();
        return user;
    }

    //3-й проверить есть ли зарегистрированный пользователь с искомым именем
    @Override
    public boolean checkUserByNameDao(String name) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name = :userName");
        List<User> userList = query.setParameter("userName", name).list();

        transaction.commit();
        session.close();
        if (userList.size() > 0) {
            return false;
        } else return true;
    }

    //4-й проверить есть ли зарегистрированный пользователь с искомым логином
    @Override
    public boolean checkUserByLoginDao(String login) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where login = :userLogin");
        List<User> userList = query.setParameter("userLogin", login).list();
        transaction.commit();
        session.close();
        if (userList.size() > 0) {
            return false;
        } else return true;
    }

    //5-й создать и добавить в базу нового пользователя
    @Override
    public void addUserDao(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    //6-й обновить и записать в базу новые данные пользователя
   // @Override
    public void updateUserDao2(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();

    }

    @Override
    public void updateUserDao(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE User SET name=:name, login=:login, password=:password, role=:role WHERE id=:id")
                .setParameter("name", user.getName())
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .setParameter("role", user.getRole())
                .setParameter("id", user.getId())
                .executeUpdate();

        transaction.commit();
    }

    //7-й удалить пользователя через ID
    @Override
    public void deleteUserByIdDao(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        //      try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :userId");
            query.setParameter("userId", id);
            query.executeUpdate();
            // session.delete(car);
            transaction.commit();
            session.close();
 //       } catch (SQLException e) {
 //           e.printStackTrace();

        }

    //8-й проверить есть ли зарегистрированный пользователь
    // с искомым login и password
    @Override
    public User isExist(String login, String password){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where login = :userLogin");
        List<User> userList = query.setParameter("userLogin", login).list();

        User userExist = null;
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                userExist = user;
            }
        }
        transaction.commit();
        session.close();
        return userExist;
    }




///////////////////////////////////////////////////////////
/*


    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE if NOT EXISTS users (id bigint auto_increment, name varchar(256), login varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE if EXISTS users");
        stmt.close();
    }



*/

}
