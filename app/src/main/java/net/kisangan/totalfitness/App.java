package net.kisangan.totalfitness;

import android.app.Application;

public class App extends Application {
  // Instance of AppContainer that will be used by all the Activities of the app
    public AppContainer appContainer = new AppContainer(getApplicationContext());
}
