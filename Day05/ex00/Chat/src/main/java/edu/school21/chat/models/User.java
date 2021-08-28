package edu.school21.chat.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> ownerRooms;
    private List<Chatroom> roomsPresented;

    public User(){
    }

    public User(Long newId, String newLogin, String newPassword, List<Chatroom> newOwnerRooms, List<Chatroom> newRoomsPresented){
        id = newId;
        login = newLogin;
        password = newPassword;
        ownerRooms = newOwnerRooms;
        roomsPresented = newRoomsPresented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getOwnerRooms() {
        return ownerRooms;
    }

    public void setOwnerRooms(List<Chatroom> ownerRooms) {
        this.ownerRooms = ownerRooms;
    }

    public List<Chatroom> getRoomsPresented() {
        return roomsPresented;
    }

    public void setRoomsPresented(List<Chatroom> roomsPresented) {
        this.roomsPresented = roomsPresented;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                Objects.equals(ownerRooms, user.ownerRooms) &&
                Objects.equals(roomsPresented, user.roomsPresented);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, ownerRooms, roomsPresented);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", ownerRooms=" + ownerRooms +
                ", roomsPresented=" + roomsPresented +
                '}';
    }
}
