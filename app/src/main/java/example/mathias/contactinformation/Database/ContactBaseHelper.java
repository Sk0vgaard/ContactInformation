package example.mathias.contactinformation.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import example.mathias.contactinformation.Database.ContactDBSchema.ContactTable;
/**
 * Created by Mathias on 26/03/2018.
 */

public class ContactBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "contactBase.db";

    public ContactBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ContactTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ContactTable.Cols.UUID + ", " +
                ContactTable.Cols.NAME + ", " +
                ContactTable.Cols.ADDRESS + ", " +
                ContactTable.Cols.LOCATION + ", " +
                ContactTable.Cols.PHONE + ", " +
                ContactTable.Cols.MAIL + ", " +
                ContactTable.Cols.WEBSITE + ", " +
                ContactTable.Cols.BIRTHDAY + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}