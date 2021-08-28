package edu.school21.chat.app;

import edu.school21.chat.exception.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.DataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;



public class Program {

    public static void main(String[] args) {
        DataSource ds = new DataSource();
        MessagesRepositoryJdbcImpl repo = new MessagesRepositoryJdbcImpl(ds);
        Scanner scanner = new Scanner(System.in);
        Long id = null;

        User creator = new User(1L, "cat", "passcat", new ArrayList(), new ArrayList());
        Chatroom room = new Chatroom(1L, "chat1", creator, new ArrayList());
        Message message = new Message(null, creator, room, "I am cat", LocalDateTime.now());

        try {
            repo.save(message);
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ex.getClass().getSimpleName() + " " + ex.getMessage());
            System.exit(-1);
        }
        System.out.println("Id of saved message:");
        System.out.println(message.getId());

        while(true){
            System.out.println("Enter a message ID");
            System.out.print("-> ");
            try{
                id = scanner.nextLong();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            if (id == 42){
                break;
            }
            try{
                System.out.println(repo.findById(id).get());
            } catch (Exception e) {
                System.out.println("Message with id " + id + " not found");
//                e.printStackTrace();
            }
        }
        ds.close();
    }
}
