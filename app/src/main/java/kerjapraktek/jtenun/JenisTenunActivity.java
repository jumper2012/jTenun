package kerjapraktek.jtenun;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by JERRY on 7/17/2016.
 */
public class JenisTenunActivity extends Activity {
    private Menu menu;
    private boolean isListView;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TenunListAdapter mAdapter;
    DBHelper dbhelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent(); // gets the previously created intent
        String nama_tenun;
        if (myIntent.getStringExtra("nama_tenun") == null) {
            nama_tenun = myIntent.getStringExtra("nama_tenun_back");
        } else {
            nama_tenun = myIntent.getStringExtra("nama_tenun");
        }
        final String nama_tenun_final = nama_tenun;
        setTitle("Daftar Tenun " + nama_tenun_final);
        setContentView(R.layout.activity_jenis_tenun);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbhelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new TenunListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        TenunListAdapter.OnItemClickListener onItemClickListener = new TenunListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(JenisTenunActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JenisTenunActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_PARAM_ID, position);
                intent.putExtra("nama_tenun", nama_tenun_final);
                startActivity(intent);
            }
        };
        mAdapter.setOnItemClickListener(onItemClickListener);

        isListView = true;
    }

    private void setUpActionBar() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_grid, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        } else {
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (isListView) {
            mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Show as list");
            isListView = false;
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Show as grid");
            isListView = true;
        }
    }
}
