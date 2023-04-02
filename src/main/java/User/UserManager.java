package User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private String filename;

    public UserManager(String filename) {
        this.filename = filename;
        this.users = new ArrayList<>();
        loadUsers();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                saveUsers();
                return;
            }
        }
    }


    public User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }
    public void deleteUser(User user) {
        users.remove(user);
        saveUsers();
    }

    public boolean userExists(User passedUser){
        for (User user : users) {
            if (user.getUsername().equals(passedUser.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void saveUsers() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();

        } catch (IOException e) {
        }
    }
    public void loadUsers() {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}