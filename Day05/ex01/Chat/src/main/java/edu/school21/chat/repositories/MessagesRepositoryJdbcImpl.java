package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long Id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            connection = ds.getConnection();
            ps = connection.prepareStatement(String.format("SELECT * FROM public.message WHERE id = %d", Id));
            rs = ps.executeQuery();
            rs.next();

            Long id = rs.getLong(1);
            Long author = rs.getLong(2);
            Long chatroomM = rs.getLong(3);
            String text = rs.getString(4);
            LocalDateTime date = null;
            try {
                date = rs.getTimestamp(5).toLocalDateTime();
            } catch (NullPointerException ex) {
            }

            ps = connection.prepareStatement(String.format("SELECT * FROM public.user WHERE id = %d", author));
            rs = ps.executeQuery();
            rs.next();

            Long userId = rs.getLong(1);
            String userLogin = rs.getString(2);
            String userPassword = rs.getString(3);

            ps = connection.prepareStatement(String.format("SELECT * FROM public.chatroom WHERE id = %d", chatroomM));
            rs = ps.executeQuery();
            rs.next();

            Long chatroomId = rs.getLong(1);
            String chatroomName = rs.getString(2);

            User user = new User(
                    userId,
                    userLogin,
                    userPassword,
                    null,
                    null
            );

            Chatroom chatroom = new Chatroom(
                    chatroomId,
                    chatroomName,
                    null,
                    null
            );

            return Optional.of(new Message(
                    id,
                    user,
                    chatroom,
                    text,
                    date
            ));

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }


        }
        return null;
    }
}
