package tdp.com.quanlynhahang.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tdp.com.quanlynhahang.DanhmucActivity;
import tdp.com.quanlynhahang.model.Danhmuc;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="danhmuc_manager";
    private static final String TABLE_NAME="danhmuc_monan";
    private static final String ID="id";
    private static final String NGUYENLIEU="nguyenlieu";
    private static final String TENMONAN="tenmonan";
    private static final String DONGIA="dongia";
    private static int VERSION = 1;
    private Context context;

    private String SQLQuery ="CREATE TABLE "+TABLE_NAME+" ("+
            ID +" integer primary key, "+
            NGUYENLIEU +" TEXT, "+
            TENMONAN +" TEXT, "+
            DONGIA +" TEXT) ";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
//    Thêm món
    public void addDanhmuc(Danhmuc danhmuc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NGUYENLIEU,danhmuc.getmNguyenLieu());
        values.put(TENMONAN,danhmuc.getmTenMonAn());
        values.put(DONGIA,danhmuc.getmDonGia());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
//    Lấy dữ liêu
    public List<Danhmuc> getallDanhmuc(){
        List<Danhmuc> ListDanhmuc = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Danhmuc danhmuc = new Danhmuc();
                danhmuc.setmID(cursor.getInt(0));
                danhmuc.setmNguyenLieu(cursor.getString(1));
                danhmuc.setmTenMonAn(cursor.getString(2));
                danhmuc.setmDonGia(cursor.getString(3));
                ListDanhmuc.add(danhmuc);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ListDanhmuc;
    }
    public int updateDanhmuc (Danhmuc danhmuc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TENMONAN, danhmuc.getmTenMonAn());
        contentValues.put(NGUYENLIEU, danhmuc.getmNguyenLieu());
        contentValues.put(DONGIA, danhmuc.getmDonGia());
        int number = db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(danhmuc.getmID())});
        return number;
    }
    public int deleteDanhmuc (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
    }
}
