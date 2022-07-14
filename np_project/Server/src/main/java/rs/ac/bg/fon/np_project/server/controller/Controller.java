/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np_project.server.controller;

import rs.ac.bg.fon.np_project.server.db.DbConnectionFactory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Publisher;
import rs.ac.bg.fon.np_project.commonlibrary.model.Worker;
import rs.ac.bg.fon.np_project.commonlibrary.model.User;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCard;
import rs.ac.bg.fon.np_project.commonlibrary.model.UserCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.GameCategory;
import rs.ac.bg.fon.np_project.commonlibrary.model.Game;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.np_project.commonlibrary.model.Rent;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryGameCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryPublisher;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUser;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.np_project.server.repository.impl.RepositoryWorker;
import rs.ac.bg.fon.np_project.server.so.AbstractSO;
import rs.ac.bg.fon.np_project.server.so.game.AddGameSO;
import rs.ac.bg.fon.np_project.server.so.game.DeleteGameSO;
import rs.ac.bg.fon.np_project.server.so.game.GetAllGamesSO;
import rs.ac.bg.fon.np_project.server.so.game.GetGamesByQuerySO;
import rs.ac.bg.fon.np_project.server.so.gameCategory.GetGameCategoriesSO;
import rs.ac.bg.fon.np_project.server.so.gameCategory.GetGameCategoryIdSO;
import rs.ac.bg.fon.np_project.server.so.publisher.GetAllPublishersSO;
import rs.ac.bg.fon.np_project.server.so.rent.GetAllRentsSO;
import rs.ac.bg.fon.np_project.server.so.rent.GetAllUserRentsSO;
import rs.ac.bg.fon.np_project.server.so.rent.GetUserRentsSO;
import rs.ac.bg.fon.np_project.server.so.rent.RentGameSO;
import rs.ac.bg.fon.np_project.server.so.rent.RestoreGameSO;
import rs.ac.bg.fon.np_project.server.so.user.AddUserSO;
import rs.ac.bg.fon.np_project.server.so.user.DeleteUserSO;
import rs.ac.bg.fon.np_project.server.so.user.GetAllUsersSO;
import rs.ac.bg.fon.np_project.server.so.user.GetUsersByQuerySO;
import rs.ac.bg.fon.np_project.server.so.user.UpdateUserSO;
import rs.ac.bg.fon.np_project.server.so.userCard.DeleteUserCardSO;
import rs.ac.bg.fon.np_project.server.so.userCard.GetUserCardsByQuerySO;
import rs.ac.bg.fon.np_project.server.so.userCategory.GetAllUserCategoriesSO;
import rs.ac.bg.fon.np_project.server.so.worker.AddWorkerSO;
import rs.ac.bg.fon.np_project.server.so.worker.GetWorkersSO;
import rs.ac.bg.fon.np_project.server.so.worker.LogOutSO;
import rs.ac.bg.fon.np_project.server.so.worker.LoginSO;
import rs.ac.bg.fon.np_project.server.so.worker.RemoveWorkerSO;

/**
 *
 * @author Simona
 */
public class Controller {
    
    private static Controller instance;
    private Worker currentUser;
    private RepositoryWorker repositoryWorker;
    private RepositoryUserCategory repositoryUserCategories;
    private RepositoryUser repositoryUser;
    private RepositoryUserCard repositoryUserCard;
    private RepositoryPublisher repositoryPublisher;
    private RepositoryGameCategory repositoryGameCategory;
    
    private Controller() {
        repositoryWorker = new RepositoryWorker();
        repositoryUserCategories = new RepositoryUserCategory();
        repositoryUser = new RepositoryUser();
        repositoryUserCard = new RepositoryUserCard();
        repositoryPublisher = new RepositoryPublisher();
        repositoryGameCategory = new RepositoryGameCategory();
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public Worker login(String user, String pass) throws Exception {
        LoginSO loginSO = new LoginSO();
        try {
            Worker wor = new Worker();
            wor.setUsername(user);
            wor.setPassword(pass);
            currentUser = (Worker) loginSO.execute(wor);
            return currentUser;
            
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
            
        }
        
    }
    
    public Worker getCurrentUser() {
        return this.currentUser;
    }
    
    public List<UserCategory> getUserCategories() throws Exception {
        GetAllUserCategoriesSO getAllUserCategoriesSO = new GetAllUserCategoriesSO();
        List<UserCategory> categories;
        try {
            categories = (List<UserCategory>) getAllUserCategoriesSO.execute(null);
            return categories;
            
        } catch (SQLException ex) {
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw new Exception(ex.getMessage());
            
        }
    }
    
    public void addUser(User user) throws Exception {
        AddUserSO addUserSo = new AddUserSO();
        addUserSo.execute(user);
        
    }
    
    public List<User> getUsers() throws Exception {
        GetAllUsersSO getAllUsersSO = new GetAllUsersSO();
        try {
            return (List<User>) getAllUsersSO.execute(null);
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
        
    }
    
    public UserCard getUserCardById(long id) throws Exception {
        GetUserCardsByQuerySO getUserCardsByQuerySO = new GetUserCardsByQuerySO();
        try {
            String query = "SELECT * FROM clanskakarta WHERE id=" + id;
            List<UserCard> userCards = (List<UserCard>) getUserCardsByQuerySO.execute(query);
            DbConnectionFactory.getInstance().getConnection().commit();
            return userCards.get(0);
        } catch (Exception e) {
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void deleteUser(User user) throws Exception {
        DeleteUserSO deleteUserSO = new DeleteUserSO();
        try {
            deleteUserSO.execute(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void deleteUserCard(UserCard card) throws SQLException, Exception {
        DeleteUserCardSO deleteUserCardSO = new DeleteUserCardSO();
        try {
            deleteUserCardSO.execute(card);
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
    }
    
    public List<User> getUsersByUsersCard(String brojCk) throws Exception {
        GetUsersByQuerySO getUsersByQuerySO = new GetUsersByQuerySO();
        try {
            String query = "SELECT c.id as id, c.ime as ime, c.prezime as prezime, c.brojTelefona as bt, c.adresa as adresa, c.kategorijaId as kategorija, c.clanskaKartaId as ck FROM clan c INNER JOIN clanskakarta ck ON (c.clanskaKartaId=ck.id) WHERE ck.brojClanskeKarte='" + brojCk + "'";
            return (List<User>) getUsersByQuerySO.execute(query);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void updateUser(User oldUser, User newUser) throws Exception {
        List<User> usersForUpdate = new ArrayList<>();
        usersForUpdate.add(oldUser);
        usersForUpdate.add(newUser);
        UpdateUserSO updateUserSO = new UpdateUserSO();
        try {
            updateUserSO.execute(usersForUpdate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public List<Publisher> getPublishers() throws Exception {
        GetAllPublishersSO getAllPublishersSO = new GetAllPublishersSO();
        List<Publisher> publishers = new ArrayList<>();
        try {
            publishers = (List<Publisher>) getAllPublishersSO.execute(null);
            return publishers;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void addGame(Game game) throws Exception {
        AbstractSO addgameSO = new AddGameSO();
        addgameSO.execute(game);
    }
    
    public GameCategory[] getGameCategories() throws Exception {
        //return GameCategory.values();
        GameCategory[] gameCategories = null;
        AbstractSO getGameCategoriesSO = new GetGameCategoriesSO();
        gameCategories = (GameCategory[]) getGameCategoriesSO.execute(null);
        return gameCategories;
        
    }
    
    public List<Game> getGames() throws Exception {
        List<Game> games = new ArrayList<>();
        AbstractSO getAllGamesSO = new GetAllGamesSO();
        games = (List<Game>) getAllGamesSO.execute(null);
        return games;
    }
    
    public List<Game> getGamesByQuery(String query) throws Exception {
        GetGamesByQuerySO getGamesByQuerySO = new GetGamesByQuerySO();
        List<Game> games = (List<Game>) getGamesByQuerySO.execute(query);
        return games;
        
    }
    
    public Long getGameCategoryId(String categoryName) throws Exception {
        GetGameCategoryIdSO getGameCategoryIdSO = new GetGameCategoryIdSO();
        try {
            return (Long) getGameCategoryIdSO.execute(categoryName);
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void deleteGame(Game game) throws Exception {
        DeleteGameSO deleteGameSO = new DeleteGameSO();
        try {
            deleteGameSO.execute(game);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void rentGame(User u, Game g) throws Exception {
        RentGameSO rentGameSO = new RentGameSO();
        List<Object> parameters = new ArrayList<>();
        parameters.add(u);
        parameters.add(g);
        rentGameSO.execute(parameters);
        
    }
    
    public void restoreGame(Rent rental) throws Exception {
        RestoreGameSO restoreGameSO = new RestoreGameSO();
        
        try {
            restoreGameSO.execute(rental);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<Rent> getRentList() throws Exception {
        GetAllRentsSO getAllRentsSO = new GetAllRentsSO();
        try {
            List<Rent> userRents = (List<Rent>) getAllRentsSO.execute(null);
            return userRents;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<Rent> getUserRents(User u) throws Exception {
        GetUserRentsSO getUserRentsSO = new GetUserRentsSO();
        try {
            List<Rent> userRents = (List<Rent>) getUserRentsSO.execute(u);
            return userRents;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public List<Rent> getAllUserRents(User u) throws Exception {
        GetAllUserRentsSO getAllUserRentsSO = new GetAllUserRentsSO();
        try {
            List<Rent> userRents = (List<Rent>) getAllUserRentsSO.execute(u);
            System.out.println("DOSLO DO KONTROLERA: " + userRents.size());
            return userRents;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void addWorker(Worker worker) throws Exception {
        AddWorkerSO addWorkerSO = new AddWorkerSO();
        addWorkerSO.execute(worker);
    }
    
    public List<Worker> getWorkers() throws Exception {
        GetWorkersSO getWorkersSO = new GetWorkersSO();
        return (List<Worker>) getWorkersSO.execute(null);
    }
    
    public void removeWorker(Worker worker) throws Exception {
        RemoveWorkerSO removeWorkerSO = new RemoveWorkerSO();
        removeWorkerSO.execute(worker);
        
    }
    
    public List<User> getUsersByName(String name) throws Exception {
        GetAllUsersSO getAllUsersSO = new GetAllUsersSO();
        try {
            List<User> allUsers = (List<User>) getAllUsersSO.execute(null);
            List<User> queriedUsers = new ArrayList<>();
            for (User u : allUsers) {
                if (name.contains(" ")) {
                    if (name.toLowerCase().contains(u.getName().toLowerCase()) && (name.toLowerCase().contains(u.getLastName().toLowerCase())) || u.getLastName().toLowerCase().contains(name.toLowerCase().substring(name.indexOf(" ") + 1))) {
                        queriedUsers.add(u);
                    }
                } else {
                    if (name.toLowerCase().contains(u.getName().toLowerCase()) || u.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(u.getLastName().toLowerCase()) || u.getLastName().toLowerCase().contains(name.toLowerCase())) {
                        queriedUsers.add(u);
                    }
                }
                
            }
            return queriedUsers;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void logout(Worker user) {
        LogOutSO logoutSO = new LogOutSO();
        try {
            logoutSO.execute(user);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}
