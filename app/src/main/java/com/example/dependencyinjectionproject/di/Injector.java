package com.example.dependencyinjectionproject.di;

import com.example.dependencyinjectionproject.MyApplication;
import com.example.dependencyinjectionproject.di.components.ApplicationComponent;
import com.example.dependencyinjectionproject.di.components.DaggerApplicationComponent;
import com.example.dependencyinjectionproject.di.components.DaggerMainComponent;
import com.example.dependencyinjectionproject.di.components.MainComponent;
import com.example.dependencyinjectionproject.di.modules.ApplicationModule;
import com.example.dependencyinjectionproject.di.modules.MainModule;
import com.example.dependencyinjectionproject.di.modules.NetworkModule;
import com.example.dependencyinjectionproject.di.modules.RepositoryModule;
import com.example.dependencyinjectionproject.view.MainActivity;

public enum Injector {
  INSTANCE;

  ApplicationComponent applicationComponent;
  MainComponent mainComponent;

  Injector() {
  }

  public void initApplicationComponent(MyApplication myApplication) {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(myApplication))
        .networkModule(new NetworkModule())
        .repositoryModule(new RepositoryModule())
        .build();
    }
  }

  public void initMainComponent(MainActivity mainActivity) {
    if (mainComponent == null) {
      mainComponent = DaggerMainComponent.builder()
        .mainModule(new MainModule(mainActivity))
        .applicationComponent(applicationComponent)
        .build();
    }
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public void releaseApplicationComponent() {
    applicationComponent = null;
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }

  public void releaseMainComponent() {
    mainComponent = null;
  }
}
