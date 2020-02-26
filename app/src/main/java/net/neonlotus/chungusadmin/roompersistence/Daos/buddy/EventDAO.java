package net.neonlotus.chungusadmin.roompersistence.Daos.buddy;

import net.neonlotus.chungusadmin.roompersistence.Models.data_diagram.DBEvent;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EventDAO {

    @Insert()
    void insertAllEvents(List<DBEvent> events);

    @Insert()
    void insertSomeEvents(DBEvent... events);

    @Update()
    void updateEvent(DBEvent event);

    @Query("UPDATE events_table SET isRegistered = :registered WHERE eventId = :eid")
    int updateEventRegister(String eid, boolean registered);

    @Query("SELECT*FROM events_table")
    List<DBEvent> fetchAllEvents();

    @Query("DELETE FROM events_table WHERE eventId= :id")
    void deleteEventByID(String id);

    @Query("SELECT*FROM events_table where eventId=:id")
    DBEvent fetchEventById(String id);


    //Get event with it's address data by event id
//    @Query("SELECT * FROM events_table left join address_table on addressId = address_id where eventId=:id")
//    DBEventWithAddress fetchEventAddressForID(String id);

    //For future... if we ever need to sort on distance+date in event list; but will probably need some reworking
//    @Query("SELECT * FROM events_table left join address_table on addressId = address_id")
//    List<DBEventWithAddress> fetchEventAddresses();

//    @Query("SELECT * FROM events_table left join address_table on addressId = address_id where eventId=:id")
//    LiveData<DBEventWithAddress> fetchEventAddressesLive(String id);

    //Event + poi from specific ID
//    @Query("SELECT * FROM events_table left join event_poi_table on event_id = eventId where eventId=:id")
//    DBEventWithPOI fetchEventWithPois(String id);



    @Query("DELETE FROM events_table where eventId in (:ids)")
    void nukeTable(List<String> ids);
    /*
    SELECT column-names
    FROM table-name1 LEFT JOIN table-name2
    ON column-name1 = column-name2
    WHERE condition
     */
}
