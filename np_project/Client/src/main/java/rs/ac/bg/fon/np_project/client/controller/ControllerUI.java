/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.client.controller;

import rs.ac.bg.fon.np_project.client.communication.Communication;
import rs.ac.bg.fon.np_project.client.thread.ServerStoppedListener;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Operations;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Request;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Response;
import rs.ac.bg.fon.np_project.commonlibrary.communication.ResponseType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;

/**
 *
 * @author Simona
 */
public class ControllerUI {

    private static ControllerUI instance;
    private Worker currentUser;
    private ServerStoppedListener serverStoppedListener;

    public void setServerStoppedListener(ServerStoppedListener serverStoppedListener) {
        this.serverStoppedListener = serverStoppedListener;
    }

    private ControllerUI() {

    }

    public void finish() {
        if (serverStoppedListener != null) {
            serverStoppedListener.serverStopped();
        }
    }

    public static ControllerUI getInstance() {
        if (instance == null) {
            instance = new ControllerUI();
        }
        return instance;
    }

    public Worker login(String user, String pass) throws SQLException, Exception {
        Worker r = new Worker();
        r.setUsername(user);
        r.setPassword(pass);
        Request req = new Request(Operations.LOGIN, r);
        Response response = Communication.getInstance().login(req);
        if (response == null || !response.getResponseType().equals(ResponseType.SUCCESS)) {
            throw response.getException();
        }
        return (Worker) response.getResult();

    }

    public Worker getCurrentUser() {
        return this.currentUser;
    }

    public List<UserCategory> getUserCategories() throws Exception {
        Request request = new Request(Operations.GET_USER_CATEGORIES, null);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<UserCategory>) response.getResult();
    }

    public void addUser(User user) throws SQLException, Exception {
        Request request = new Request(Operations.ADD_USER, user);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public List<User> getUsers() throws Exception {
        Request request = new Request(Operations.GET_USERS, null);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw new Exception();
        }
        return (List<User>) response.getResult();

    }

    public UserCard getUserCardById(long id) throws Exception {

        return null;
    }

    public void deleteUser(User user) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.DELETE_USER);
        request.setArgument(user);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public List<User> getUsersByUsersCard(String cardNumber) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.GET_USERS_BY_USER_CARD);

        request.setArgument(cardNumber);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<User>) response.getResult();

    }

    public void updateUser(User oldUser, User newUser) throws SQLException, Exception {
        List<User> usersForUpdating = new ArrayList<>();
        usersForUpdating.add(oldUser);
        usersForUpdating.add(newUser);

        Request request = new Request(Operations.UPDATE_USER, usersForUpdating);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw new Exception();
        }

    }

    public List<Publisher> getPublishers() throws Exception {
        Request request = new Request(Operations.GET_PUBLISHERS, null);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Publisher>) response.getResult();
    }

    public void addGame(Game game) throws Exception {
        Request request = new Request(Operations.ADD_GAME, game);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {

        } else {
            throw response.getException();
        }

    }

    public GameCategory[] getGameCategories() throws Exception {
        Request request = new Request(Operations.GET_GAME_CATEGORIES, null);
        Response response;
        response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (GameCategory[]) response.getResult();

    }

    public List<Game> getGames() throws Exception {
        Request request = new Request(Operations.GET_GAMES, null);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Game>) response.getResult();
    }

    public List<Game> getGamesByQuery(String query) throws Exception {
        Request request = new Request(Operations.GET_GAMES_BY_QUERY, query);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Game>) response.getResult();
    }

    public Long getGameCategoryId(GameCategory category) throws SQLException, Exception {
        Request request = new Request(Operations.GET_GAME_CATEGORIY_ID, category);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (Long) response.getResult();
    }

    public void deleteGame(Game game) throws SQLException, Exception {
        Request request = new Request(Operations.DELETE_GAME, game);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public void rentGame(User u, Game g) throws Exception {
        List<Object> usergame = new ArrayList<>();
        usergame.add(u);
        usergame.add(g);
        Request request = new Request();
        request.setArgument(usergame);
        request.setOperation(Operations.RENT_GAME);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public List<Rent> getUserRents(User u) throws Exception {
        Request r = new Request();
        List<Rent> userRents = new ArrayList<>();
        r.setOperation(Operations.GET_USER_RENTS);
        r.setArgument(u);
        Response resp = Communication.getInstance().sendRequest(r);
        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            throw resp.getException();
        }
        userRents = (List<Rent>) resp.getResult();
        return userRents;
    }

    public void restoreGame(Rent rental) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.RESTORE_GAME);
        request.setArgument(rental);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();

        }
    }

    public List<Rent> getRentList() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.GET_RENTALS);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Rent>) response.getResult();

    }

    public void logout(Worker user) {
        try {
            Communication.getInstance().logout(user);
        } catch (Exception ex) {
            Logger.getLogger(ControllerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Rent> getAllUserRents(User u) throws Exception {
         Request r = new Request();
        List<Rent> userRents = new ArrayList<>();
        r.setOperation(Operations.GET_ALL_USER_RENTS);
        r.setArgument(u);
        Response resp = Communication.getInstance().sendRequest(r);
        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            throw resp.getException();
        }
        userRents = (List<Rent>) resp.getResult();
        System.out.println("KOLIKO JE VRACENO:"+userRents.size());
        return userRents;
            }

    public List<User> getUsersByName(String name) throws Exception {
         Request request = new Request();
        request.setOperation(Operations.GET_USERS_BY_NAME);

        request.setArgument(name);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<User>) response.getResult();
        }

}
