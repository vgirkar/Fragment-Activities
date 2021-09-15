package com.example.group1_1inclass_04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements AccountManager {

    DataServices.Account acc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendLoginFragment();
    }

    public void setAcc(@Nullable DataServices.Account acc){
        this.acc = acc;
    }

    public DataServices.Account getAcc() {
        return acc;
    }

    public void sendLoginFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, LoginFragment.newInstance())
                .commit();
    }

    public void sendAccountFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new AccountFragment())
                .commit();
    }

    public void sendRegisterFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new RegisterFragment())
                .commit();
    }

    public void sendUpdateFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, UpdateFragment.newInstance(acc))
                .addToBackStack(null)
                .commit();
    }

    public void goBack(){
        getSupportFragmentManager().popBackStack();
    }

}

interface AccountManager{

    void setAcc(@Nullable DataServices.Account acc);

    void sendAccountFragment();

    void sendUpdateFragment();

    void sendLoginFragment();

    void sendRegisterFragment();

    void goBack();

    DataServices.Account getAcc();

}