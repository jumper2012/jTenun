package bhouse.jtenun;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SearchView searchView;
    private GridLayoutManager lLayout;

    bhouse.jtenun.adapters.GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<bhouse.jtenun.ImageModel> data = new ArrayList<>();

    public static String IMGS[] = {
            "https://lh3.googleusercontent.com/orI_qbPuZNn8O5MHKi4zK5nskmymzdirezMIIIXy3fmThR6CAKls3p3f96n4hpY_1UG1OaM=s85",
            "https://lh3.googleusercontent.com/4iBKxtcsSetR_nCfbsIsqde1QpQz3dQ0KBffCGFLf8Hdy2vjDfb0Cg5nazWKDy7ddjBc=s85",
            "https://lh3.googleusercontent.com/A1oP_mkCP5_rqrTITC0A9btLrgXFvPT1-oJV6JgkIVsg83fZXxq1_wDwMJywU_bOQygdnA=s85",
            "https://lh3.googleusercontent.com/VdWhSAbku_JTuwqsOaqLD4GCZLsIEkWXZCO7IJheNwsLFctK6EXpGXki962R6ya2N0kE8A=s85",
            "https://lh3.googleusercontent.com/ADgDRpMDp6t8WFaKlJyBoU_dDQxxouwY2l6G062iOOC5hCWf4iT5WaRat4_KCWVk3HSVGA=s85",
            "https://lh3.googleusercontent.com/zbwnFlN3q0bMct3YHdcJPYmJg0g7G-aw5ZjACJIeHGv-3TRWsiwyYT3eaH760u7W8uLu2g=s85",
            "https://lh3.googleusercontent.com/14OgqeQuCVx9DOa17D0bB68i_RG7eD9DM8VoA3DJNuOBZtmlEE4tZunlA9i4kTlEEsEFh4M=s85",
            "https://lh3.googleusercontent.com/-l-WoLomhcLxR0mMZHb5_NIHp6s4R8cZ012OaVfZsRqtz1GM-GCFqocdweowS5FCuVoJQk4=s85",
            "https://lh3.googleusercontent.com/4iBKxtcsSetR_nCfbsIsqde1QpQz3dQ0KBffCGFLf8Hdy2vjDfb0Cg5nazWKDy7ddjBc=s85",
            "https://lh3.googleusercontent.com/zbwnFlN3q0bMct3YHdcJPYmJg0g7G-aw5ZjACJIeHGv-3TRWsiwyYT3eaH760u7W8uLu2g=s85"
    };


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < IMGS.length; i++) {
//  Adding images & title to POJO class and storing in Array (our data)
            bhouse.jtenun.ImageModel imageModel = new bhouse.jtenun.ImageModel();
            imageModel.setName("Image " + i);
            imageModel.setUrl(IMGS[i]);
            data.add(imageModel);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true); // Helps improve performance
        mAdapter = new bhouse.jtenun.adapters.GalleryAdapter(MainActivity.this, data);
        mRecyclerView.setAdapter(mAdapter);


        //Onclick Listener
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", data);
                        intent.putExtra("pos", position);
                        startActivity(intent);

                    }
                }));
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                             // This method will trigger on item Click of navigation menu
                                                             @Override
                                                             public boolean onNavigationItemSelected(MenuItem menuItem) {

                                                                 //Checking if the item is in checked state or not, if not make it in checked state
                                                                 if (menuItem.isChecked())
                                                                     menuItem.setChecked(false);
                                                                 else menuItem.setChecked(true);
                                                                 //search View
                                                                 searchView = (SearchView) findViewById(R.id.searchfield);
                                                                 searchView.setQueryHint("Search View");

                                                                 searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                                                                     @Override
                                                                     public boolean onQueryTextSubmit(String query) {
                                                                         Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                                                                         return false;
                                                                     }

                                                                     @Override
                                                                     public boolean onQueryTextChange(String newText) {
                                                                         Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                                                                         return false;
                                                                     }
                                                                 });

                                                                 //Closing drawer on item click


                                                                 //Check to see which item was being clicked and perform appropriate action
                                                                 switch (menuItem.getItemId()) {


                                                                     //Replacing the main content with BerandaFragment Which is our Inbox View;
                                                                     case R.id.beranda:
                                                                         Toast.makeText(getApplicationContext(), "Beranda", Toast.LENGTH_SHORT).show();
                                                                         Intent beranda = new Intent(MainActivity.this, MainActivity.class);
                                                                         startActivity(beranda);
                                                                         return true;

                                                                     // For rest of the options we just show a toast on click

                                                                     case R.id.daftarTenun:
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.sub_menu_daftar_tenun);
                                                                         return true;


                                                                     case R.id.kategori:
                                                                         Toast.makeText(getApplicationContext(), "Kategori Selected", Toast.LENGTH_SHORT).show();
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.sub_menu_drawer);
                                                                         return true;

                                                                     case R.id.daerah:
                                                                         Toast.makeText(getApplicationContext(), "Daerah Selected", Toast.LENGTH_SHORT).show();
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.drawer);
                                                                         drawerLayout.closeDrawers();
                                                                         return true;
                                                                     case R.id.war:
                                                                         Toast.makeText(getApplicationContext(), "Warna Selected", Toast.LENGTH_SHORT).show();
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.drawer);
                                                                         drawerLayout.closeDrawers();
                                                                         return true;
                                                                     case R.id.pat:
                                                                         Toast.makeText(getApplicationContext(), "Corak Selected", Toast.LENGTH_SHORT).show();
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.drawer);
                                                                         drawerLayout.closeDrawers();
                                                                         return true;
                                                                     case R.id.kegu:
                                                                         Toast.makeText(getApplicationContext(), "Kegunaan Selected", Toast.LENGTH_SHORT).show();
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.drawer);
                                                                         drawerLayout.closeDrawers();
                                                                         return true;
                                                                     case R.id.back_to_main:
                                                                         navigationView.getMenu().clear();
                                                                         navigationView.inflateMenu(R.menu.drawer);
                                                                         return true;

                                                                     //daftar Tenun
                                                                     case R.id.sasirangan:
                                                                         Toast.makeText(getApplicationContext(), "Sasirangan", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun);
                                                                         return true;
                                                                     case R.id.ulos:
                                                                         Toast.makeText(getApplicationContext(), "ulos", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun1 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun1);
                                                                         //drawerLayout.closeDrawers();
                                                                         return true;
                                                                     case R.id.sarungbugis:
                                                                         Toast.makeText(getApplicationContext(), "sarungbugis", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun2 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun2);
                                                                         return true;
                                                                     case R.id.lurik:
                                                                         Toast.makeText(getApplicationContext(), "lurik", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun3 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun3);
                                                                         return true;
                                                                     case R.id.songketlombok:
                                                                         Toast.makeText(getApplicationContext(), "songketlombok", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun4 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun4);
                                                                         return true;
                                                                     case R.id.tapis:
                                                                         Toast.makeText(getApplicationContext(), "tapis", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun5 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun5);
                                                                         return true;
                                                                     case R.id.gringsing:
                                                                         Toast.makeText(getApplicationContext(), "gringsing", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun6 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun6);
                                                                         return true;
                                                                     case R.id.tenundayak:
                                                                         Toast.makeText(getApplicationContext(), "tenundayak", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun7 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun7);
                                                                         return true;
                                                                     case R.id.besurek:
                                                                         Toast.makeText(getApplicationContext(), "besurek", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun8 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun8);
                                                                         return true;
                                                                     case R.id.songketpalembang:
                                                                         Toast.makeText(getApplicationContext(), "songketpalembang", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun9 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun9);
                                                                         return true;
                                                                     case R.id.tenunulapdoyo:
                                                                         Toast.makeText(getApplicationContext(), "tenunulapdoyo", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun10 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun10);
                                                                         return true;
                                                                     case R.id.poleng:
                                                                         Toast.makeText(getApplicationContext(), "poleng", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun11 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun11);
                                                                         return true;
                                                                     case R.id.jumputan:
                                                                         Toast.makeText(getApplicationContext(), "jumputan", Toast.LENGTH_SHORT).show();
                                                                         Intent jenis_tenun12 = new Intent(MainActivity.this, JenisTenunActivity.class);
                                                                         startActivity(jenis_tenun12);
                                                                         return true;
                                                                     default:
                                                                         Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                                                                         drawerLayout.closeDrawers();
                                                                         return true;

                                                                 }
                                                             }
                                                         }

        );

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout)

                findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}