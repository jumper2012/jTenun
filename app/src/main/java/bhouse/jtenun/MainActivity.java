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

import java.util.ArrayList;
import java.util.List;

import bhouse.jtenun.models.SectionDataModel;

///baklabalbslabskabskabsak
public class MainActivity extends FragmentActivity {
    //Defining Variables
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

    ArrayList<SectionDataModel> allSampleData;


    private TabLayout tabLayout;

    //glide
    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<ImageModel> data = new ArrayList<>();

    public static String IMGS[] = {
            "https://images.unsplash.com/photo-1444090542259-0af8fa96557e?q=80&fm=jpg&w=1080&fit=max&s=4b703b77b42e067f949d14581f35019b",
            "https://images.unsplash.com/photo-1439546743462-802cabef8e97?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1441155472722-d17942a2b76a?q=80&fm=jpg&w=1080&fit=max&s=80cb5dbcf01265bb81c5e8380e4f5cc1",
            "https://images.unsplash.com/photo-1437651025703-2858c944e3eb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1431538510849-b719825bf08b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1434873740857-1bc5653afda8?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1439396087961-98bc12c21176?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1433616174899-f847df236857?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1438480478735-3234e63615bb?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300",
            "https://images.unsplash.com/photo-1438027316524-6078d503224b?dpr=2&fit=crop&fm=jpg&h=725&q=50&w=1300"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    //requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
        for (int i = 0; i < IMGS.length; i++) {

            ImageModel imageModel = new ImageModel();
            imageModel.setName("Image " + i);
            imageModel.setUrl(IMGS[i]);
            data.add(imageModel);

        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new GalleryAdapter(MainActivity.this, data);
        mRecyclerView.setAdapter(mAdapter);

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







//        List<ItemObject> rowListItem = getAllItemList();
//        lLayout = new GridLayoutManager(MainActivity.this, 3);
//
//        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
//        rView.setHasFixedSize(true);
//        rView.setLayoutManager(lLayout);
//
//        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
//        rView.setAdapter(rcAdapter);

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


    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("A", R.drawable.one));
        allItems.add(new ItemObject("B", R.drawable.two));
        allItems.add(new ItemObject("C", R.drawable.three));
        allItems.add(new ItemObject("D", R.drawable.four));
        allItems.add(new ItemObject("E", R.drawable.five));
        allItems.add(new ItemObject("F", R.drawable.six));
        allItems.add(new ItemObject("G", R.drawable.seven));
        allItems.add(new ItemObject("H", R.drawable.eight));
        allItems.add(new ItemObject("I", R.drawable.one));
        allItems.add(new ItemObject("J", R.drawable.two));
        allItems.add(new ItemObject("K", R.drawable.three));
        allItems.add(new ItemObject("L", R.drawable.four));
        allItems.add(new ItemObject("M", R.drawable.five));
        allItems.add(new ItemObject("N", R.drawable.six));
        allItems.add(new ItemObject("O", R.drawable.seven));
        allItems.add(new ItemObject("P", R.drawable.eight));

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
