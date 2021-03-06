package sg.edu.np.mad.practical;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c) {
        super(c, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE User (Username TEXT, Description TEXT, Id INTEGER, Followed " +
                "TEXT)";
        // Can create column variable here and assign ID, so getcolumnindex is easier
        // Find db, if not exist, will create one
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Take care all versions of DB here (eg. version 3 vs version 10)
        // With every upgrade, shouldn't drop table to delete all data

        db.execSQL("DROP TABLE IF EXISTS User"); // Not recommended
        onCreate(db); // Call onCreate mtd to make a database
    }

    public void insertUser(User userObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO User VALUES(\"" + userObject.Name + "\", \"" +
                userObject.Description + "\", \"" +
                userObject.Id + "\", \"" + userObject.Followed + "\")");

        db.close(); // When done, close the database connection
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> userArrayList = new ArrayList<User>();
        String query = "SELECT * FROM User";

        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext())
        {
            // From one row to the other
            // Get all data in one row
            User user = new User();

            user.Name = c.getString(c.getColumnIndexOrThrow("Username"));
            user.Description = c.getString(c.getColumnIndexOrThrow("Description"));
            user.Id = c.getInt(c.getColumnIndexOrThrow("Id"));
            user.Followed = Boolean.parseBoolean(
                    c.getString(c.getColumnIndexOrThrow( "Followed")));

            userArrayList.add(user);
        }

        c.close();
        db.close();
        return userArrayList;
    }

    public void updateUser(User user) {

        // Find user object in DB and update the value of "Followed" column
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE User SET Followed = \""+ user.Followed +"\" " +
                "WHERE Id = \""+ user.Id +"\"";

        // Copy & paste UPDATE User SET Followed = "user.Followed" WHERE Id = "user.Id"

        db.execSQL(query);
        db.close();
    }
}
