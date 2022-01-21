package net.kisangan.totalfitness;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.kisangan.totalfitness.data.LoginDataSource;
import net.kisangan.totalfitness.data.LoginRepository;
import net.kisangan.totalfitness.data.model.LoggedInUser;
import net.kisangan.totalfitness.databinding.ActivityMainBinding;
import net.kisangan.totalfitness.ui.login.LoginActivity;

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