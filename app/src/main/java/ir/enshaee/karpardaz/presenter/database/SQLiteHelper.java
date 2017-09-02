package ir.enshaee.karpardaz.presenter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ir.enshaee.karpardaz.model.Hazine;


@SuppressWarnings("unused")
public class SQLiteHelper extends SQLiteOpenHelper {

      public static final String TAG = SQLiteHelper.class.getSimpleName();
      
      private SQLiteDatabase mDb;
      
      private static final int DATABASE_VERSION=1;
      private static final String DATABASE_NAME = "karpardaz";
      
      private static final String TABLE_CREATION = "("
              + SQLKeys.COLUMN_ID + " INTEGER PRIMARY KEY, "
              + SQLKeys.COLUMN_DATE + " TEXT, "
              + SQLKeys.COLUMN_SUBJECT + " TEXT, "
              + SQLKeys.COLUMN_QUANTITY + " TEXT"
              + ");";
      private static final String CREATE_TABLE_TOJIBI = "CREATE TABLE " + SQLKeys.TABLE_TOJIBI + TABLE_CREATION;
      private static final String CREATE_TABLE_TANKHAH = "CREATE TABLE " + SQLKeys.TABLE_TANKHAH + TABLE_CREATION;
      
      
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
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TANKHAH);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TOJIBI);
            onCreate(db);
      }
      
      
      /**
       * Gets All Records
       * @return {@link Hazine} List of Data
       * */
      public List<Hazine> getAllTojibi(){
            mDb = getReadableDatabase();
            
            List<Hazine> hazineList = new ArrayList<>();
            Cursor c = mDb.query(SQLKeys.TABLE_TOJIBI, null, null, null, null, null, SQLKeys.COLUMN_DATE + " ASC");
            if (c != null){
                  do{
                        Hazine hazine = new Hazine();
                        hazine.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                        hazine.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                        hazine.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                        hazineList.add(hazine);
                  }while (c.moveToNext());
                  c.close();
            }
            mDb.close();
            return hazineList;
      }
      
      /**
       * Gets Records by Value
       * @param date record Date
       * @return HazineList {@link Hazine}
       * */
      public List<Hazine> getTojibiHazineByDate (String date){
            mDb = getReadableDatabase();
            Hazine hazine = new Hazine();
            Cursor c = mDb.query(SQLKeys.TABLE_TOJIBI,
                    null,
                    SQLKeys.COLUMN_DATE + " = ?",
                    new String[]{date},
                    null, null,
                    SQLKeys.COLUMN_DATE + " ASC");
            List<Hazine> hazineList = new ArrayList<>();
            
            if(c != null){
                  do {
                        Hazine h = new Hazine();
                        h.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                        h.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                        h.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                        hazineList.add(h);
                  }while(c.moveToNext());
                  c.close();
            }
            mDb.close();
            return hazineList;
      }
      
      
      public long insertTojibiHazine(Hazine hazine){
            mDb = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
            values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
            values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());
            long id = mDb.insert(SQLKeys.TABLE_TOJIBI, null, values);
            
            mDb.close();
            return id;
      }
      
      public int updateTojibiHazine(int id, Hazine hazine){
            mDb = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
            values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
            values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());
            
            int retId = mDb.update(SQLKeys.TABLE_TOJIBI, values, SQLKeys.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
            mDb.close();
            return retId;
      }
      
      public int deleteTojibiHazine(int id){
            mDb = getWritableDatabase();
            
            int retId = mDb.delete(SQLKeys.TABLE_TOJIBI,
                    SQLKeys.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)});
            mDb.close();
            return retId;
      }
      
      
      /**
       * Gets Records by Value
       * @param date record Date
       * @return HazineList {@link Hazine}
       * */
      public List<Hazine> getTankhahHazineByDate (String date){
            mDb = getReadableDatabase();
            Hazine hazine = new Hazine();
            Cursor c = mDb.query(SQLKeys.TABLE_TANKHAH,
                    null,
                    SQLKeys.COLUMN_DATE + " = ?",
                    new String[]{date},
                    null, null,
                    SQLKeys.COLUMN_DATE + " ASC");
            List<Hazine> hazineList = new ArrayList<>();
            
            if(c != null){
                  do {
                        Hazine h = new Hazine();
                        h.setDate(c.getString(c.getColumnIndex(SQLKeys.COLUMN_DATE)));
                        h.setCost(c.getString(c.getColumnIndex(SQLKeys.COLUMN_QUANTITY)));
                        h.setSubject(c.getString(c.getColumnIndex(SQLKeys.COLUMN_SUBJECT)));
                        hazineList.add(h);
                  }while(c.moveToNext());
                  c.close();
            }
            mDb.close();
            return hazineList;
      }
      
      
      public long insertTankhahHazine(Hazine hazine){
            mDb = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
            values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
            values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());
            long id = mDb.insert(SQLKeys.TABLE_TANKHAH, null, values);
            
            mDb.close();
            return id;
      }
      
      public int updateTankhahHazine(int id, Hazine hazine){
            mDb = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLKeys.COLUMN_DATE, hazine.getDate());
            values.put(SQLKeys.COLUMN_SUBJECT, hazine.getSubject());
            values.put(SQLKeys.COLUMN_QUANTITY, hazine.getCost());
            
            int retId = mDb.update(SQLKeys.TABLE_TANKHAH, values, SQLKeys.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
            mDb.close();
            return retId;
      }
      
      public int deleteTankhahHazine(int id){
            mDb = getWritableDatabase();
            
            int retId = mDb.delete(SQLKeys.TABLE_TANKHAH,
                    SQLKeys.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)});
            mDb.close();
            return retId;
      }
      
      
      
      
      
}
