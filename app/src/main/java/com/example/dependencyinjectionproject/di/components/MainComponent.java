package com.example.dependencyinjectionproject.di.components;

import com.example.dependencyinjectionproject.di.modules.MainModule;
import com.example.dependencyinjectionproject.di.scopes.ScopeMain;
import com.example.dependencyinjectionproject.view.MainActivity;

import dagger.Component;

@ScopeMain
@Component(dependencies = ApplicationComponent.class, modules = {MainModule.class})
public interface MainComponent {

  void inject(MainActivity mainActivity);

}
