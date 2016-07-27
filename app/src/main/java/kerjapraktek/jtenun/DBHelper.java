package kerjapraktek.jtenun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by JERRY on 6/28/2016.
 */
public class DBHelper extends SQLiteOpenHelper {private static final String DATABASE_NAME = "jtenun.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //query tabel tenun
        String tabel_tenun = "create table tenun(id_tenun integer primary key, nama_tenun text null, asal_tenun text null, deskripsi_tenun text null, sejarah_tenun text null, kegunaan_tenun text null);";
        Log.d("Data", "onCreate: " + tabel_tenun);
        //query tabel jenis_tenun
        String tabel_jenis_tenun = "create table jenis_tenun(id_jenis_tenun integer primary key, id_tenun integer, nama_jenis_tenun text null, warna_dominan text null, deskripsi text null);";
        Log.d("Data", "onCreate: " + tabel_jenis_tenun);
        //query potongan jenis_tenun
        String tabel_potongan_jenis_tenun = "create table potongan_jenis_tenun(id_potongan integer primary key, id_jenis_tenun integer, nama_potongan_jenis_tenun text null);";
        Log.d("Data", "onCreate: " + tabel_potongan_jenis_tenun);
        //query hasil generate potongan
        String tabel_hasil_generate_potongan  = "create table hasil_generate(id_hasil_generate primary key, id_potongan integer, rating integer null);";
        Log.d("Data", "onCreate: " + tabel_hasil_generate_potongan);


        String insert_data_tenun = "INSERT INTO tenun (id_tenun, nama_tenun, asal_tenun, deskripsi_tenun, sejarah_tenun, kegunaan_tenun) VALUES('1', 'Ulos', 'Sumatera Utara', 'blblablabla', 'blablablabla', 'blalblalbalba lagi');";
        db.execSQL(tabel_tenun);
        db.execSQL(insert_data_tenun);
        db.execSQL(tabel_jenis_tenun);
        db.execSQL(tabel_potongan_jenis_tenun);
        db.execSQL(tabel_hasil_generate_potongan);
        String ulos[] = {"Bintang Maratur","Harungguan","Sibolang", "Ragi Idup","RagiHotang I","RagiHotang II","Sadum","Sitoluhunto"};
        int counter_ulos;
        for(counter_ulos = 0;counter_ulos<=7;counter_ulos++ )
        {
            int counter_potongan_ulos;
            String insert_data_jenis_tenun = "INSERT INTO jenis_tenun (id_jenis_tenun, id_tenun, nama_jenis_tenun, warna_dominan, deskripsi) VALUES('"+counter_ulos+"', '1', '"+ulos[counter_ulos]+"', 'terserah', 'blablablabla');";
            db.execSQL(insert_data_jenis_tenun);
            for(counter_potongan_ulos=0;counter_potongan_ulos<2;counter_potongan_ulos++)
            {
                String insert_data_potongan_tenun = "INSERT INTO potongan_jenis_tenun (id_jenis_tenun, nama_potongan_jenis_tenun) VALUES('"+counter_ulos+"', 'potongan "+ulos[counter_ulos]+" "+counter_potongan_ulos+"');";
                db.execSQL(insert_data_potongan_tenun);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}
