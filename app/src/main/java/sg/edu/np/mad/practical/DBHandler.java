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

    public ArrayList<User> getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> userArrayList = new ArrayList<User>();
        String query = "SELECT * FROM User";

        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext())
        {
            String name = c.getString(c.getColumnIndex("Username"));


            try {
                User user = new User();

            }
            catch (Exception e) {

            }
        }

        return new ArrayList<User> ();
    }
}
