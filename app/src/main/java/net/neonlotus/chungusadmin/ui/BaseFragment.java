package net.neonlotus.chungusadmin.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import net.neonlotus.chungusadmin.ChungusApp;
import net.neonlotus.chungusadmin.R;
import net.neonlotus.chungusadmin.tools.Constants;
import net.neonlotus.chungusadmin.tools.Utils;

import java.text.DateFormat;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static net.neonlotus.chungusadmin.tools.Constants.BACK_ARROW;
import static net.neonlotus.chungusadmin.tools.Constants.HAMBURGER_MENU;
import static net.neonlotus.chungusadmin.tools.Constants.NO_BAR;
import static net.neonlotus.chungusadmin.tools.Constants.NO_BUTTONS;

public abstract class BaseFragment extends Fragment {
    private String TAG = getClass().getSimpleName();
    Context context;
    public Gson gson;


    private Unbinder unbinder;


    public ChungusApp mApplication;


    Toolbar toolbar;
    ImageView drawerOpen;
    ImageView navBackArrow;


    DrawerLayout d;
    public NavController nc;

    public static DateFormat df;
    public static DateFormat df2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        mApplication = (ChungusApp) context.getApplicationContext();
        gson = new Gson();

        df = Constants.df;
        df2 = Constants.df2;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = provideYourFragmentView(inflater, container, savedInstanceState);
        int i = setActionbarVisibility();

        unbinder = ButterKnife.bind(this, view);

        toolbar = getActivity().findViewById(R.id.toolbar);
        drawerOpen = toolbar.findViewById(R.id.ivDrawerOpen);
        navBackArrow = toolbar.findViewById(R.id.ivNavBackArrow);

        drawerOpen.setVisibility(View.GONE);
        navBackArrow.setVisibility(View.GONE);

        d = getActivity().findViewById(R.id.drawer_layout);
        d.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //makes it so you can't swipe the drawer open - blocks Login drawer

        nc = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, nc);

        View navHeader = navigationView.findViewById(R.id.nh);
        navHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "FRAGMENT CLOSE"); //clicking the whole header will close it ... can move to just X button if needed
                d.closeDrawers();
            }
        });


        drawerOpen.setOnClickListener(hamDrawerOpenClick);

        navBackArrow.setOnClickListener(hamBackArrowClick);


        adjustActionBar(i);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //Sept '19 - Issues temporarily removed ; brought back Jan '20
    View.OnClickListener hamIssuesClick = view -> {
        hamburgerClick();

        //navigate(R.id.incidentListFragment, null);
    };

    public abstract View provideYourFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract int setActionbarVisibility();

    public abstract String setFragmentTag();

    public void adjustActionBar(int i) {
        toolbar.setVisibility(View.VISIBLE);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        switch (i) {
            case NO_BUTTONS:
                drawerOpen.setVisibility(View.GONE);
                navBackArrow.setVisibility(View.GONE);
                break;
            case BACK_ARROW:
                drawerOpen.setVisibility(View.GONE);
                navBackArrow.setVisibility(View.VISIBLE);
                break;
            case HAMBURGER_MENU:
                drawerOpen.setVisibility(View.VISIBLE);
                navBackArrow.setVisibility(View.GONE);
                break;
            case NO_BAR: //basically just login and social selection
                toolbar.setVisibility(View.GONE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
        }
    }

    private void hamburgerClick() {
        d.closeDrawers();
        Utils.hideKeyboard(Objects.requireNonNull(getActivity()));
    }

    private boolean navigateTo(int dest) {
        d.closeDrawers();
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


    View.OnClickListener hamDrawerOpenClick = view -> {
        d.openDrawer(GravityCompat.START);
        Utils.hideKeyboard(Objects.requireNonNull(getActivity()));
    };

    View.OnClickListener hamBackArrowClick = view -> {
        hamburgerClick();

        getActivity().onBackPressed();
        Log.d(TAG, "fragment back arrow");
    };

    View.OnClickListener hamMapClick = view -> {
        hamburgerClick();
        nc.navigate(R.id.mainFragment);
    };



}