package com.mrjoke.wechat.dao.impl;

import com.mrjoke.wechat.dao.TokenDao;
import com.mrjoke.wechat.domain.Token;

import java.sql.*;

import static com.mrjoke.wechat.utils.Constants.TOKEN_TABLE;

public class TokenDaoJdbcImpl implements TokenDao {
    private Connection conn;

    public TokenDaoJdbcImpl() {
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/wechat?useSSL=false";
            conn = DriverManager.getConnection(url,"root","root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Token token) {
        String insert = "INSERT INTO " + TOKEN_TABLE + " (token,expires) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setString(1,token.getToken());
            preparedStatement.setLong(2,token.getExpires());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Token token) {
        String update = "UPDATE " + TOKEN_TABLE + " SET token = '" + token.getToken() + "',expires = " + token.getExpires()
                + " WHERE id = " + token.getId();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        String delete = "DELETE FROM " + TOKEN_TABLE + " WHERE id = " + id;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Token selectById(int id) {
        return null;
    }

    @Override
    public Token selectFirst() {
        String select = "SELECT * FROM " + TOKEN_TABLE + " LIMIT 1 offset 0";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            if (resultSet.next()){
                Token token = new Token();
                token.setId(resultSet.getInt(1));
                token.setToken(resultSet.getString(2));
                token.setExpires(resultSet.getLong(3));
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count() {
        String select = "SELECT COUNT(*) FROM " + TOKEN_TABLE ;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
