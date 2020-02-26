package net.neonlotus.chungusadmin.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import net.neonlotus.chungusadmin.ChungusApp;
import net.neonlotus.chungusadmin.R;
import net.neonlotus.chungusadmin.tools.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private Context context;
    private Gson gson;
    private ChungusApp mApplication;


    //public MutableLiveData<Integer> hamburgerHelpState;
    //private List<String> lastVisitedFragments = new ArrayList<>();

    //bind views

    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;


    //https://developer.android.com/training/location/geofencing


    private NavController nc;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Main Activity created");
        setContentView(R.layout.activity_main);

        context = this;
        gson = new Gson();
        mApplication = (ChungusApp) getApplicationContext();

        nc = Navigation.findNavController(this, R.id.nav_host_fragment);
        navigationView = findViewById(R.id.nav_view);


        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);

        NavigationUI.setupWithNavController(navigationView, nc);

        nc.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Utils.hideKeyboard(MainActivity.this);


            }
        });

        View closeDrawer = navigationView.findViewById(R.id.nh);
        //This seems to be handled in the fragments - so won't both copying all logic here
        closeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawers();
                Log.d(TAG, "activity close drawer");
            }
        });

    }


    private boolean navigateTo(int dest) {
        try {
            mDrawerLayout.closeDrawers();
        } catch (Exception e) {
            Log.d(TAG, "Drawer close failed " + e.getLocalizedMessage());
        }

        String current = nc.getCurrentDestination().getLabel().toString();
        String destination = Utils.findFragmentTagByID(dest);

        return !current.equalsIgnoreCase(destination);
    }

    public void navigate(int i, Bundle b) {
        if (navigateTo(i)) {
            if (b != null)
                nc.navigate(i, b);
            else
                nc.navigate(i);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy"); //swipe from recent
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Main Activity resumed");


        mApplication = (ChungusApp) getApplicationContext();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onSupportNavigateUp() {
        return nc.navigateUp();
    }

    //click?


    @Override
    public void onBackPressed() {
        String currentLabel = nc.getCurrentDestination().getLabel().toString();
    }
}