package com.demo.connexissample.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase   ;

import com.demo.connexissample.model.MasterPojo;
import com.demo.connexissample.model.ResponseList;

/**
 * Created by samrat on 25/1/19.
 */

@Database(entities = {MasterPojo.class, ResponseList.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();

}
