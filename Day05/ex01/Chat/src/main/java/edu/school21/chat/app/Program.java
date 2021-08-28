package edu.school21.chat.app;

import edu.school21.chat.models.DataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        DataSource ds = new DataSource();
        MessagesRepositoryJdbcImpl repo = new MessagesRepositoryJdbcImpl(ds);
        Scanner scanner = new Scanner(System.in);
        Long id = null;

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
