package bhouse.jtenun;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {


    public static final String EXTRA_PARAM_ID = "place_id";
    private ImageView mImageView;
    private TextView mTitle;
    private LinearLayout mTitleHolder;
    private ImageButton mViewDetailButton;
    private ImageButton mViewFilosofi;
    private LinearLayout mRevealView;
    private Tenun mTenun;
    int defaultColor;
    public static final String NAMA_GAMBAR = "";
    public static final String ID_MOTIF = "";
    final Context context = this;

    String[] daftar;
    ListView ListView01;
    protected Cursor cursor;

    DBHelper dbcenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jenis_tenun);

        mTenun = TenunData.placeList().get(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        mImageView = (ImageView) findViewById(R.id.placeImage);
        mTitle = (TextView) findViewById(R.id.textView);
        mTitleHolder = (LinearLayout) findViewById(R.id.placeNameHolder);

        mViewDetailButton = (ImageButton) findViewById(R.id.btn_view_detail);
        mViewFilosofi = (ImageButton) findViewById(R.id.btn_view_filosofi);

        mViewDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailgambar = new Intent(DetailActivity.this, DetailImageActivity.class);

                String nama_gambar = mTenun.name;
                detailgambar.putExtra(NAMA_GAMBAR, nama_gambar);
                startActivity(detailgambar);
            }
        });

        mViewFilosofi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("FILOSOFI");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                        .setCancelable(false)
                        .setPositiveButton("TUTUP", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
        //mViewDetailButton.setOnClickListener(this);


        defaultColor = getResources().getColor(R.color.primary_dark);
        loadPlace();
        getPhoto();
        dbcenter = new DBHelper(this);

        RefreshList();
    }


    private void loadPlace() {
        mTitle.setText(mTenun.name);
        mImageView.setImageResource(mTenun.getImageResourceId(this));
    }

    private void getPhoto() {
        Bitmap photo = BitmapFactory.decodeResource(getResources(), mTenun.getImageResourceId(this));
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM potongan_jenis_tenun WHERE nama_potongan_jenis_tenun LIKE '%" + mTenun.name + "%'", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

//    MotifJenisTenun potongan[]= new MotifJenisTenun[]{};
        final MotifJenisTenun[] potongan = new MotifJenisTenun[cursor.getCount()];
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            potongan[cc] = new MotifJenisTenun(cursor.getString(0), cursor.getString(1), cursor.getString(2).toString());
        }
        MotifJenisTenunAdapter adapter = new MotifJenisTenunAdapter(this,
                R.layout.listview_item_row, potongan);


        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(adapter);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int itemPosition = position;
///                String itemValue = (String) ListView01.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Position :" + potongan[itemPosition].nama_potongan_jenis_ulos, Toast.LENGTH_LONG)
                        .show();
                Intent detailmotifactivity = new Intent(DetailActivity.this, MainActivity.class);
                detailmotifactivity.putExtra(ID_MOTIF, potongan[itemPosition].id_potongan);
                startActivity(detailmotifactivity);
            }
        });

//    ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
    }
}
