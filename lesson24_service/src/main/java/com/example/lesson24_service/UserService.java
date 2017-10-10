package com.example.lesson24_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.lesson24_service.adil.IUserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/12/14.
 */

public class UserService extends Service {
    private List<User> users;

    @Override
    public void onCreate() {
        super.onCreate();
        users = new ArrayList<>();
        User u = new User();
        u.setAge(23);
        u.setId(1);
        u.setName("aaaa");
        u.setPhone("111111111111");
        users.add(u);

         u = new User();
        u.setAge(23);
        u.setId(2);
        u.setName("bbbb");
        u.setPhone("22222222222222");
        users.add(u);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    class UserManager implements IUserManager{
        @Override
        public int count() throws RemoteException {
            return 0;
        }

        @Override
        public void addUser(User user) throws RemoteException {

        }

        @Override
        public User loadUser(int id) throws RemoteException {
            return null;
        }

        @Override
        public List<User> getUsers() throws RemoteException {
            return null;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }
}
