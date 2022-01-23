package net.kisangan.totalfitness;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.kisangan.totalfitness.data.LoginDataSource;
import net.kisangan.totalfitness.data.LoginRepository;
import net.kisangan.totalfitness.databinding.ActivityMainBinding;
import net.kisangan.totalfitness.util.GetLoggedInUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginRepository instance;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar myToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        instance = LoginRepository.getInstance(new LoginDataSource());
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar_nav_menu, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.profile_menu_item) {
            navController.navigate(R.id.action_navigation_home_to_physicalHealthFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<Void> mGetUser = registerForActivityResult(new GetLoggedInUser(),
        new ActivityResultCallback<String>() {
            @Override
            public void onActivityResult(String user) {
                // Handle the returned logged user
                System.out.println("User: "+user +" Logged in");
            }
    });

    @Override
    public void onResume() {
        super.onResume();
        if (!instance.isLoggedIn()) {
          mGetUser.launch(null);
        }
    }
}