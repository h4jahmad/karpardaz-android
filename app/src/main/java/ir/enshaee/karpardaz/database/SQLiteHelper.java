package ir.enshaee.karpardaz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ir.enshaee.karpardaz.main.model.Hazine;

import static ir.enshaee.karpardaz.database.SQLKeys.TABLE_TANKHAH;
import static ir.enshaee.karpardaz.database.SQLKeys.TABLE_TOJIBI;


public class SQLiteHelper extends SQLiteOpenHelper {

    @SuppressWarnings("WeakerAccess")
    public static final String TAG = SQLiteHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "karpardaz";
    private static final String TABLE_CREATION = "("
            + SQLKeys.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SQLKeys.COLUMN_DATE + " TEXT, "
            + SQLKeys.COLUMN_SUBJECT + " TEXT, "
            + SQLKeys.COLUMN_QUANTITY + " TEXT"
            + ");";
    private static final String CREATE_TABLE_TOJIBI = "CREATE TABLE " + TABLE_TOJIBI + TABLE_CREATION;
    private static final String CREATE_TABLE_TANKHAH = "CREATE TABLE " + TABLE_TANKHAH + TABLE_CREATION;
    private SQLiteDatabase mDb;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TANKHAH);
        db.execSQL(CREATE_TABLE_TOJIBI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLKeys.TABLE_TANKHAH);
        db.execSQL("DROP TABLE IF EXISTS " + SQLKeys.TABLE_TOJIBI);
        onCreate(db);
    }


    /**
     * Gets All Records
     *
     * @return {@link Hazine} List of Data
     */
    public List<Hazine> getAllTojibi() {
        mDb = getReadableDatabase();

        List<Hazine> hazineList = new ArrayList<>();
        Cursor c = mDb.query(TABLE_TOJIBI, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Hazine hazine = new Hazine();
                hazine.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                hazine.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                hazine.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                hazineList.add(hazine);
            } while (c.moveToNext());
            c.close();
        }
        mDb.close();
        return hazineList;
    }

    /**
     * /**
     * Gets All Records
     *
     * @return {@link Hazine} List of Data
     */
    public List<Hazine> getAllTankhahs() {
        mDb = getReadableDatabase();

        List<Hazine> hazineList = new ArrayList<>();
        Cursor c = mDb.query(TABLE_TANKHAH, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Hazine hazine = new Hazine();
                hazine.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                hazine.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                hazine.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                hazineList.add(hazine);
            } while (c.moveToNext());
            c.close();
        }
        mDb.close();
        return hazineList;
    }

    /**
     * Gets Records by Value
     *
     * @param date record Date
     * @return HazineList {@link Hazine}
     */
    public List<Hazine> getTojibiHazineByDate(String date) {
        mDb = getReadableDatabase();
        Hazine hazine = new Hazine();
        Cursor c = mDb.query(TABLE_TOJIBI,
                null,
                SQLKeys.COLUMN_DATE + " = ?",
                new String[]{date},
                null, null,
                SQLKeys.COLUMN_DATE + " ASC");
        List<Hazine> hazineList = new ArrayList<>();

        if (c != null) {
            do {
                Hazine h = new Hazine();
                h.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                h.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                h.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                hazineList.add(h);
            } while (c.moveToNext());
            c.close();
        }
        mDb.close();
        return hazineList;
    }


    public long insertTojibiHazine(Hazine hazine) {
        mDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
        values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
        values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());
        long id = mDb.insert(TABLE_TOJIBI, null, values);

        mDb.close();
        return id;
    }

    public int updateTojibiHazine(Hazine hazine) {
        mDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
        values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
        values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());

        int retId = mDb.update(TABLE_TOJIBI, values, SQLKeys.COLUMN_ID + " = ?", new String[]{String.valueOf(hazine.getId())});
        mDb.close();
        return retId;
    }

    public int deleteTojibiHazine(int id) {
        mDb = getWritableDatabase();

        int retId = mDb.delete(TABLE_TOJIBI,
                SQLKeys.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        mDb.close();
        return retId;
    }


    /**
     * Gets Records by Value
     *
     * @param date record Date
     * @return HazineList {@link Hazine}
     */
    public List<Hazine> getTankhahHazineByDate(String date) {
        mDb = getReadableDatabase();
        Hazine hazine = new Hazine();
        Cursor c = mDb.query(TABLE_TANKHAH,
                null,
                SQLKeys.COLUMN_DATE + " = ?",
                new String[]{date},
                null, null,
                SQLKeys.COLUMN_DATE + " ASC");
        List<Hazine> hazineList = new ArrayList<>();

        if (c != null) {
            do {
                Hazine h = new Hazine();
                h.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                h.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                h.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                hazineList.add(h);
            } while (c.moveToNext());
            c.close();
        }
        mDb.close();
        return hazineList;
    }


    public long insertTankhahHazine(Hazine hazine) {
        mDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
        values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
        values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());
        long id = mDb.insert(TABLE_TANKHAH, null, values);

        mDb.close();
        return id;
    }

    public int updateTankhahHazine(Hazine hazine) {
        mDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
        values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
        values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());

        int retId = mDb.update(TABLE_TANKHAH, values, SQLKeys.COLUMN_ID + " = ?", new String[]{String.valueOf(hazine.getId())});
        mDb.close();
        return retId;
    }


    public int deleteTankhahHazine(int id) {
        mDb = getWritableDatabase();

        int retId = mDb.delete(TABLE_TANKHAH,
                SQLKeys.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        mDb.close();
        return retId;
    }


    /**
     * Gets Last Thirty Records based on Table Name
     */
    public List<Hazine> getLastThirtyRecords(String tableName) {
        mDb = getReadableDatabase();
        List<Hazine> hazineList = new ArrayList<>();

        Cursor countCursor = mDb.rawQuery("select * from " + tableName, null);
        if (countCursor != null) {
            while (countCursor.moveToNext()) {
                hazineList.add(new Hazine(
                        countCursor.getInt(countCursor.getColumnIndex(SQLKeys.COLUMN_ID)),
                        countCursor.getString(countCursor.getColumnIndex(SQLKeys.COLUMN_DATE)),
                        countCursor.getString(countCursor.getColumnIndex(SQLKeys.COLUMN_SUBJECT)),
                        countCursor.getString(countCursor.getColumnIndex(SQLKeys.COLUMN_QUANTITY))
                ));
            }
            countCursor.close();
        }
        mDb.close();
        return hazineList;
    }

    public void deleteAllData() {
        mDb = getWritableDatabase();
        mDb.rawQuery("drop table " + TABLE_TANKHAH, null);
        mDb.rawQuery("drop table " + TABLE_TOJIBI, null);
        Log.i(TAG, "deleteAllData: All Data Deleted");
    }

    public int getAllDataSum(String tableName) {
        mDb = getReadableDatabase();
        Cursor c = mDb.rawQuery("select sum(" + SQLKeys.COLUMN_QUANTITY + ")from" + tableName + "group by " + SQLKeys.COLUMN_ID, null);
        int data = 0;

        if (c.moveToFirst()) {
            data = c.getInt(0);
            c.close();
        }

        return data;
    }

}
