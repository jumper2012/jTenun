package bhouse.jtenun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import com.afollestad.materialdialogs.util.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import bhouse.jtenun.models.SectionDataModel;

///baklabalbslabskabskabsak
public class MainActivity extends FragmentActivity {
    //Defining Variables

    //private final Context context_color;

    private int primaryPreselect;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SearchView searchView;
    private PopupWindow pw;
    Button Close;
    Button Create;
    private GridLayoutManager lLayout;

    private Context lappet;

    ArrayList<SectionDataModel> allSampleData;


    private TabLayout tabLayout;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        primaryPreselect = DialogUtils.resolveColor(this, R.attr.colorPrimary);
        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(MainActivity.this, 3);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);

//        Create = (Button) findViewById(R.id.create_popup);
//        Create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//// TODO Auto-generated method stub
//                showPopup();
//            }
//        });
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
                                                                         /*int[][] subColors = new int[][]{
                                                                                 new int[]{Color.parseColor("#EF5350"), Color.parseColor("#F44336"), Color.parseColor("#E53935")},
                                                                                 new int[]{Color.parseColor("#EC407A"), Color.parseColor("#E91E63"), Color.parseColor("#D81B60")},
                                                                                 new int[]{Color.parseColor("#AB47BC"), Color.parseColor("#9C27B0"), Color.parseColor("#8E24AA")},
                                                                                 new int[]{Color.parseColor("#7E57C2"), Color.parseColor("#673AB7"), Color.parseColor("#5E35B1")},
                                                                                 new int[]{Color.parseColor("#5C6BC0"), Color.parseColor("#3F51B5"), Color.parseColor("#3949AB")},
                                                                                 new int[]{Color.parseColor("#42A5F5"), Color.parseColor("#2196F3"), Color.parseColor("#1E88E5")}
                                                                         };

                                                                         new ColorChooserDialog.Builder(MainActivity.this)
                                                                                 .titleSub(R.string.kategori_string)
                                                                                 .preselect(primaryPreselect)
                                                                                 .customColors(R.array.custom_colors, subColors)
                                                                                 .show();*/

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


    private List<ItemObject> getAllItemList() {

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("Motif 1", R.drawable.potonganbintangmaratur0));
        allItems.add(new ItemObject("Motif 2", R.drawable.potonganbintangmaratur1));
        allItems.add(new ItemObject("Motif 3", R.drawable.potonganragiidup));
        allItems.add(new ItemObject("Motif 4", R.drawable.potongansadum1));
        allItems.add(new ItemObject("Motif 5", R.drawable.potongansibolang0));
        allItems.add(new ItemObject("Motif 6", R.drawable.potongansibolang1));
        allItems.add(new ItemObject("Motif 7", R.drawable.potonganragiidup0));
        allItems.add(new ItemObject("Motif 8", R.drawable.potonganragiidup1));
        allItems.add(new ItemObject("Motif 9", R.drawable.potongansadum0));
        allItems.add(new ItemObject("Motif 10", R.drawable.potongansadum1));
        allItems.add(new ItemObject("Motif 11", R.drawable.potongansibolang0));
        allItems.add(new ItemObject("Motif 12", R.drawable.potongansibolang1));
        allItems.add(new ItemObject("Motif 13", R.drawable.potonganbintangmaratur1));
        allItems.add(new ItemObject("Motif 14", R.drawable.potonganragiidup));
        allItems.add(new ItemObject("Motif 15", R.drawable.potongansadum1));
        allItems.add(new ItemObject("Motif 16", R.drawable.potongansibolang0));


        return allItems;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

    private void showPopup() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popup_1));
            pw = new PopupWindow(layout, 800,
                    700, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            Close = (Button) layout.findViewById(R.id.close_popup);
            Close.setOnClickListener(cancel_button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };

}
