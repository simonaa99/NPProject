/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.threads;

import rs.ac.bg.fon.np_project.commonlibrary.communication.Operations;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Receiver;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Request;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Response;
import rs.ac.bg.fon.np_project.commonlibrary.communication.ResponseType;
import rs.ac.bg.fon.np_project.commonlibrary.communication.Sender;
import rs.ac.bg.fon.np_project.server.controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;


/**
 *
 * @author Simona
 */
public class HandleClientThread extends Thread {

    private ServerThread server;
    private Socket socket;
    private Worker user;

    public Socket getSocket() {
        return socket;
    }

    public Worker getUser() {
        return user;
    }
    

    public HandleClientThread(ServerThread serverthread, Socket socket) {
        this.server = serverthread;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handleClientsRequest(socket);
        } catch (SocketException se) {
            System.out.println("Klijent prekinuo program.");
   
        } catch (Exception ex) {
            System.out.println("Klijent prekinuo program.");
        }

    }

    void stopCommunication() {
        try {
            Response response = new Response();
            response.setOperation(Operations.SERVER_STOPPED);
            new Sender(socket).send(response);
            sleep(1000);
            socket.close();

        } catch (IOException ex) {
            System.out.println("Error stopping communication - class Handle..");
        } catch (Exception ex) {
            System.out.println("Error stopping communication - class Handle..");
        }
    }

    public Response handleClientsRequest(Socket socket) throws Exception {
        Response response = null;
        while (true) {

            try {
                Request request = (Request) new Receiver(socket).receive();

                switch (request.getOperation()) {
                    
                    case Operations.LOGIN:
                        Worker user = (Worker) request.getArgument();
                        response = login(user);
                        break;
                    
                    case Operations.GET_USERS_BY_USER_CARD:
                        response = getUsersByUserCard((String) request.getArgument());
                        break;
                    
                    case Operations.GET_USERS_BY_NAME:
                        response = getUsersByName((String) request.getArgument());
                        break;
                    
                    case Operations.GET_USERS:

                        response = getUsers();
                        break;
                    
                    case Operations.DELETE_USER:

                        response = deleteUser(request);
                        break;
                    
                    case Operations.UPDATE_USER:
                        response = updateUser(request);
                        break;
                    
                    case Operations.GET_USER_CATEGORIES:
                        response = getUserCategories();
                        break;
                    
                    case Operations.ADD_USER:
                        response = addUser(request);
                        break;
                    
                    case Operations.GET_PUBLISHERS:
                        response = getPublishers();
                        break;
                    case Operations.ADD_GAME:
                        response = addGame(request);
                        break;
                    case Operations.GET_GAMES:
                        response = getGames();
                        break;
                    case Operations.GET_GAMES_BY_QUERY:
                        response = getGamesByQuery(request);
                        break;
                    case Operations.GET_GAME_CATEGORIES:
                        response = getGameCategories();
                        break;
                    case Operations.GET_GAME_CATEGORIY_ID:
                        response = getGameCategoryId(request);
                        break;
                    case Operations.DELETE_GAME:
                        response = deleteGame(request);
                        break;
                    case Operations.RENT_GAME:
                        response = rentGame(request);
                        break;
                    case Operations.GET_USER_RENTS:
                        response = getUserRents(request);
                        break;
                    case Operations.GET_ALL_USER_RENTS:
                        response = getAllUserRents(request);
                        break;
                    case Operations.RESTORE_GAME:
                        response = restoreGame(request);
                        break;
                    case Operations.GET_RENTALS:
                        response = getRentals(request);
                        break;
                    case Operations.LOGOUT:
                        logout(request);
                        server.logout(this);
                        
                        break;

                    default: ;
                }
                new Sender(socket).send(response);

            } catch (ClassNotFoundException ex) {
                System.out.println("Klijent je prekinuo program.");
                server.setUserLoggedIn(user, false);
            }

        }

    }

    private Response login(Worker user) {
        Response response = new Response();
        try {
            Worker dbUser = Controller.getInstance().login(user.getUsername(), user.getPassword());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(dbUser);
            response.setOperation(Operations.LOGIN);
            server.setUserLoggedIn(dbUser,true);
            this.user=dbUser;
        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception(ex.getMessage()));
            ex.printStackTrace();
            response.setOperation(Operations.LOGIN);

        }
        return response;
    }

    private Response getUsersByUserCard(String cardNumber) {
        Response response = new Response();
        try {
            List<User> users = Controller.getInstance().getUsersByUsersCard(cardNumber);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(users);
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getUsers() {
        Response response = new Response();
        try {
            List<User> users = Controller.getInstance().getUsers();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(users);
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response deleteUser(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteUser((User) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response updateUser(Request request) {
        User oldUser = ((List<User>) request.getArgument()).get(0);
        User newUser = ((List<User>) request.getArgument()).get(1);
        Response response = new Response();
        try {

            Controller.getInstance().updateUser(oldUser, newUser);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getUserCategories() {
        Response response = new Response();
        try {
            List<UserCategory> userCategories = Controller.getInstance().getUserCategories();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(userCategories);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response addUser(Request request) {
        User user = (User) request.getArgument();
        Response response = new Response();
        try {
            Controller.getInstance().addUser(user);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response addGame(Request request) {
        Game game = (Game) request.getArgument();
        Response response = new Response();
        try {
            Controller.getInstance().addGame(game);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getGames() {
        Response response = new Response();
        try {
            List<Game> games = Controller.getInstance().getGames();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(games);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getGameCategories() {
        Response response = new Response();
        try {
            GameCategory[] gameCategories = Controller.getInstance().getGameCategories();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(gameCategories);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getGameCategoryId(Request request) {
        Response response = new Response();
        try {
            Long kategorijaId = Controller.getInstance().getGameCategoryId(((GameCategory) request.getArgument()).toString());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(kategorijaId);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getPublishers() {
        Response response = new Response();
        try {
            List<Publisher> publishers = Controller.getInstance().getPublishers();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(publishers);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getGamesByQuery(Request request) {
        String query = (String) request.getArgument();
        Response response = new Response();
        try {
            List<Game> games = Controller.getInstance().getGamesByQuery(query);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(games);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response deleteGame(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteGame((Game) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response rentGame(Request request) {
        Response response = new Response();
        User u = (User) ((List<Object>) request.getArgument()).get(0);
        Game g = (Game) ((List<Object>) request.getArgument()).get(1);
        try {
            Controller.getInstance().rentGame(u, g);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response getUserRents(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();

        try {
            List<Rent> rents = Controller.getInstance().getUserRents(u);
            response.setResult(rents);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response restoreGame(Request request) {
        Response response = new Response();
        Rent rental = (Rent) request.getArgument();
        try {
            Controller.getInstance().restoreGame(rental);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getRentals(Request request) {
        Response response = new Response();

        try {
            List<Rent> rents = Controller.getInstance().getRentList();
            response.setResult(rents);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllUserRents(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();

        try {
            List<Rent> rents = Controller.getInstance().getAllUserRents(u);
            response.setResult(rents);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getUsersByName(String name) {
        Response response = new Response();
        try {
            List<User> users = Controller.getInstance().getUsersByName(name);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(users);
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
        }

    private void logout(Request request) {
        Worker user=(Worker) request.getArgument();
        Controller.getInstance().logout(user);
        server.setUserLoggedIn(user, false);
        }

}
