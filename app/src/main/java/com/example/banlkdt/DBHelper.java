package com.example.banlkdt;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Banhang.db";
    private static final int DATABASE_VERSION = 1;
    //TABLE NSX
    private static final String TABLE_NAME = "nhasanxuat";
    private static final String COLUMN_NSX_ID = "mansx";
    private static final String COLUMN_NSX_TEN = "tennsx";
    private static final String COLUMN_NSX_DIACHI = "diachi";
    private static final String COLUMN_NSX_SDT = "sdt";
    //TABLE LK
    private static final String TABLE_NAME_1 = "linhkien";
    private static final String COLUMN_LK_ID = "malk";
    private static final String COLUMN_LK_IDNSX = "mansxlk";
    private static final String COLUMN_LK_TEN = "tenlk";
    private static final String COLUMN_LK_GIA = "gia";
    private static final String COLUMN_LK_SOLUONG = "sl";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_NSX_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NSX_TEN + " TEXT, " +
                        COLUMN_NSX_DIACHI + " TEXT,"+
                        COLUMN_NSX_SDT + " TEXT );";
        sqLiteDatabase.execSQL(query1);
        String query2 =
                "CREATE TABLE " + TABLE_NAME_1 +
                        " (" + COLUMN_LK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_LK_IDNSX + " INTEGER, " +
                        COLUMN_LK_TEN + " TEXT, " +
                        COLUMN_LK_GIA + " TEXT, " +
                        COLUMN_LK_SOLUONG + " TEXT );";
        sqLiteDatabase.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        onCreate(sqLiteDatabase);
    }

    void addNSX(String tennsx, String diachi, String sdt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NSX_TEN, tennsx);
        cv.put(COLUMN_NSX_DIACHI, diachi);
        cv.put(COLUMN_NSX_SDT, sdt);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllNSXData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor timKiemData(String s){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE tennsx LIKE '%"+s+"%' OR sdt LIKE '%"+s+"%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateNSXData(String row_id, String tennsx, String diachi, String sdt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NSX_TEN, tennsx);
        cv.put(COLUMN_NSX_DIACHI, diachi);
        cv.put(COLUMN_NSX_SDT, sdt);

        long result = db.update(TABLE_NAME, cv, "mansx=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed To Update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteNSXOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "mansx=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    // LK
    void addLK(String mansx, String tennlk, String gia, String sl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LK_IDNSX, mansx);
        cv.put(COLUMN_LK_TEN, tennlk);
        cv.put(COLUMN_LK_GIA, gia);
        cv.put(COLUMN_LK_SOLUONG, sl);

        long result = db.insert(TABLE_NAME_1, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllLKData(String row_id) {
        String query = "SELECT * FROM " + TABLE_NAME_1 + " WHERE mansxlk = " + row_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = null;
        if (db != null) {
            cursor1 = db.rawQuery(query, null);
        }
        return cursor1;
    }

    void updateLKData(String row_id, String mansxlk, String tennlk, String gia, String sl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LK_IDNSX, mansxlk);
        cv.put(COLUMN_LK_TEN, tennlk);
        cv.put(COLUMN_LK_GIA, gia);
        cv.put(COLUMN_LK_SOLUONG, sl);

        long result = db.update(TABLE_NAME_1, cv, "malk=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed To Update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteLKOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_1, "malk=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed To Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}

