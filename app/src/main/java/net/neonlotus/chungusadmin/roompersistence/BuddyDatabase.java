package net.neonlotus.chungusadmin.roompersistence;

import android.content.Context;

import net.neonlotus.chungusadmin.roompersistence.Daos.buddy.EventDAO;
import net.neonlotus.chungusadmin.roompersistence.Models.data_diagram.DBEvent;
import net.neonlotus.chungusadmin.tools.Constants;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {DBEvent.class}, version = 1,
        exportSchema = true)
public abstract class BuddyDatabase extends RoomDatabase {


    private static volatile BuddyDatabase INSTANCE;

    public static BuddyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BuddyDatabase.class) {
                if (INSTANCE == null) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                BuddyDatabase.class, Constants.BUDDY_DB_NAME)
                                //.addMigrations(MIGRATION_1_2) todo
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
        }
        return INSTANCE;
    }

    //to insert top-level events
    public abstract EventDAO eventDAO();


    //Should probably use this for updates, but just going with destructive for now
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE events_table "
                    + " ADD COLUMN isStaff BOOLEAN");
        }
    };

}