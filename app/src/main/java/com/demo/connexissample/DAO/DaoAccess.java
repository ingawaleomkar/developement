package com.demo.connexissample.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.demo.connexissample.model.MasterPojo;
import com.demo.connexissample.model.ResponseList;

import java.util.List;

/**
 * Created by samrat on 25/1/19.
 */

@Dao
public interface DaoAccess {

    @Insert
    void insertTask(List<ResponseList> masterPojo);

    @Query("SELECT * FROM ResponseList")
    LiveData<List<ResponseList>> fetchAllRecords();

    @Update
    void Uodate(MasterPojo masterPojo);

}
