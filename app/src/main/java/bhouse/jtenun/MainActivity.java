package bhouse.jtenun;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import bhouse.jtenun.adapters.RecyclerViewDataAdapter;
import bhouse.jtenun.models.SectionDataModel;
import bhouse.jtenun.models.SingleItemModel;

public class MainActivity extends FragmentActivity {
    //Defining Variables
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.bintangmaratur,R.drawable.sibolang,R.drawable.sadum,R.drawable.ragihotangi};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SearchView searchView;

    ArrayList<SectionDataModel> allSampleData;


    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    //requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
    init();

        allSampleData = new ArrayList<SectionDataModel>();
        createDummyData();

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);

    // Initializing Toolbar and setting it as the actionbar
    toolbar = (Toolbar) findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);

//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //       tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        //       tabLayout.addTab(tabLayout.newTab().setText("Home"));
        //      tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        //     tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        //    tabLayout.addTab(tabLayout.newTab().setText("More"));
        //   tabLayout.addTab(tabLayout.newTab().setText("About"));
        //   tabLayout.addTab(tabLayout.newTab().setText("Help"));
        //   tabLayout.addTab(tabLayout.newTab().setText("Friends"));

//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
///            public void onTabSelected(TabLayout.Tab tab) {
//                tab.getPosition();
//                tab.getText();
//            }

//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {

//            }

//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {

//            }
//        });

    //Initializing NavigationView
    navigationView = (NavigationView) findViewById(R.id.navigation_view);

    //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

        // This method will trigger on item Click of navigation menu
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {


            //Checking if the item is in checked state or not, if not make it in checked state
            if(menuItem.isChecked()) menuItem.setChecked(false);
            else menuItem.setChecked(true);
            //search View
            searchView=(SearchView) findViewById(R.id.searchfield);
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
            switch (menuItem.getItemId()){


                //Replacing the main content with BerandaFragment Which is our Inbox View;
                case R.id.beranda:
                    Toast.makeText(getApplicationContext(),"Beranda",Toast.LENGTH_SHORT).show();
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

                case R.id.filosofi:
                    Toast.makeText(getApplicationContext(),"Filosofi Selected",Toast.LENGTH_SHORT).show();
                    FilosofiFragment flsfifragment = new FilosofiFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionfilosofi = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionfilosofi.replace(R.id.frame,flsfifragment);
                    fragmentTransactionfilosofi.commit();
                    drawerLayout.closeDrawers();
                    return true;
//                    case R.id.spam:
//                        Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();
//                        return true;
                case R.id.daerah:
                    Toast.makeText(getApplicationContext(),"Daerah Selected",Toast.LENGTH_SHORT).show();
                    DaerahFragment drhfragment = new DaerahFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactiondaerah = getSupportFragmentManager().beginTransaction();
                    fragmentTransactiondaerah.replace(R.id.frame,drhfragment);
                    fragmentTransactiondaerah.commit();
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.drawer);
                    drawerLayout.closeDrawers();
                    return true;
                case R.id.war:
                    Toast.makeText(getApplicationContext(),"Warna Selected",Toast.LENGTH_SHORT).show();
                    WarnaFragment wrnfragment = new WarnaFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionwarna = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionwarna.replace(R.id.frame, wrnfragment);
                    fragmentTransactionwarna.commit();
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.drawer);
                    drawerLayout.closeDrawers();
                    return true;
                case R.id.pat:
                    Toast.makeText(getApplicationContext(),"Corak Selected",Toast.LENGTH_SHORT).show();
                    CorakFragment ptrnfragment = new CorakFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionptrn = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionptrn.replace(R.id.frame,ptrnfragment);
                    fragmentTransactionptrn.commit();
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.drawer);
                    drawerLayout.closeDrawers();
                    return true;
                case R.id.kegu:
                    Toast.makeText(getApplicationContext(),"Kegunaan Selected",Toast.LENGTH_SHORT).show();
                    KegunaanFragment gnfragment = new KegunaanFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactiongn = getSupportFragmentManager().beginTransaction();
                    fragmentTransactiongn.replace(R.id.frame,gnfragment);
                    fragmentTransactiongn.commit();
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
                    Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    return true;

            }
        }
    });

    // Initializing Drawer Layout and ActionBarToggle
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

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
    private void init() {


        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);



        NUM_PAGES =IMAGES.length;



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    public void createDummyData() {
        for (int i = 1; i <= 3; i++) {

            SectionDataModel dm = new SectionDataModel();

            if (i == 1) {
                dm.setHeaderTitle("Motif Terbaru");
            }
            if (i == 2) {
                dm.setHeaderTitle("Motif Terlaris");
            }
            if (i == 3) {
                dm.setHeaderTitle("Daftar Tenun");
            }

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
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
}
