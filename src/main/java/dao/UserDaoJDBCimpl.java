package dao;


import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCimpl(Connection connection) {
        this.connection = connection;
    }


    //1-й получить список всех пользователей
    @Override
    public List<User> getAllUsersDao() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {

            ResultSet result = preStmt.executeQuery();
            while (result.next()) {
                long idUser = result.getLong(1);
                String nameUser = result.getString(2);
                String loginUser = result.getString(3);
                String passwordUser = result.getString(4);
                String role = result.getString(5);

                allUsers.add(new User(idUser, nameUser, loginUser, passwordUser, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }


    //2-й получить пользователя по ID
    @Override
    public User getUserByIdDao(long id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setLong(1, id);

            ResultSet result = preStmt.executeQuery();
            result.next();
            long idUser = result.getLong(1);
            String nameUser = result.getString(2);
            String loginUser = result.getString(3);
            String passwordUser = result.getString(4);
            String role = result.getString(5);

            user = new User(idUser, nameUser, loginUser, passwordUser, role);


        } catch (SQLException e) {
            e.printStackTrace();
            // return null;
        }
        return user;
    }


    public long getUserIdByName(String name) /*throws SQLException*/ {
        String sql = "SELECT * FROM users WHERE name = ?";
        long id = 0L;
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, name);

            ResultSet result = preStmt.executeQuery();
            result.next();
            id = result.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return id;
    }

    public User getUserByName(String name) /*throws SQLException*/ { // Добавил throws SQLException или НЕТ???

        User user = null;
        long id = 0L;
        String sql = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {

            preStmt.setString(1, name);
            ResultSet result = preStmt.executeQuery();
            result.next();
            long idUser = result.getLong(1);
            String nameUser = result.getString(2);
            String loginUser = result.getString(3);
            String passwordUser = result.getString(4);
            String role = result.getString(5);


            user = new User(idUser, nameUser, loginUser, passwordUser, role);


        } catch (SQLException e) {
            e.printStackTrace();
            // return null;
        }
        return user;
    }

    //3-й проверить есть ли зарегистрированный пользователь с искомым именем
    @Override
    public boolean checkUserByNameDao(String name) throws SQLException {
        User user = null;
        long id = 0L;
        String sql = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {

            preStmt.setString(1, name);
            ResultSet result = preStmt.executeQuery();
            result.next();
            long idUser = result.getLong(1);
            //        String nameUser = result.getString(2);
            //        String loginUser = result.getString(3);
            //        String passwordUser = result.getString(4);

            //          user = new User(idUser, nameUser, loginUser, passwordUser);
            if (idUser > 0) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // return null;
        }
        return true;
    }

    //4-й проверить есть ли зарегистрированный пользователь с искомым логином
    @Override
    public boolean checkUserByLoginDao(String login) throws SQLException {
        User user = null;
        long id = 0L;
        String sql = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {

            preStmt.setString(1, login);
            ResultSet result = preStmt.executeQuery();
            result.next();
            long idUser = result.getLong(1);
            //        String nameUser = result.getString(2);
            //        String loginUser = result.getString(3);
            //        String passwordUser = result.getString(4);

            //          user = new User(idUser, nameUser, loginUser, passwordUser);
            if (idUser > 0) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // return null;
        }
        return true;
    }


    //5-й создать и добавить в базу нового пользователя
    @Override
    public void addUserDao(User user) {
        String sql = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, user.getName());
            preStmt.setString(2, user.getLogin());
            preStmt.setString(3, user.getPassword());
            preStmt.setString(4, user.getRole());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //6-й обновить и записать в базу новые данные пользователя
    @Override
    public void updateUserDao(User user) {
        String sql = "UPDATE users SET name = ?, login = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {

            preStmt.setString(1, user.getName());
            preStmt.setString(2, user.getLogin());
            preStmt.setString(3, user.getPassword());
            preStmt.setString(4, user.getRole());
            preStmt.setLong(5, user.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // add deleteClient to BankClientDAO NAME
    public void deleteUserByName(String name) /*throws SQLException*/ {// void or boolean
        User user = getUserByName(name); // а проверка имени клиента??? в Сервисе?
        String sql = "DELETE FROM users WHERE name = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, user.getName());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //7-й удалить пользователя через ID
    @Override
    public void deleteUserByIdDao(Long id) /*throws SQLException*/ {// void or boolean
        //  User user = getUserByName(name); // а проверка имени клиента??? в Сервисе?
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setLong(1, id);

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //8-й проверить есть ли зарегистрированный пользователь
    // с искомым login и password
    @Override
    public User isExist(String login, String password) {

        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement("select * from users where login = ? and password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {

                long idUser = result.getLong(1);
                String nameUser = result.getString(2);
                String loginUser = result.getString(3);
                String passwordUser = result.getString(4);
                String role = result.getString(5);


                user = new User(idUser, nameUser, loginUser, passwordUser, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }



///////////////////////////////////////////////////////////


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




}
