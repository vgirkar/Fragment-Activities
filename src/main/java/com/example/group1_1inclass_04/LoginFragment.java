package com.example.group1_1inclass_04;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class LoginFragment extends Fragment {

    AccountManager am;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AccountManager){
            am = (AccountManager)context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getActivity().setTitle("Login");
        EditText txt_email = view.findViewById(R.id.editTextEmail);
        EditText txt_pass = view.findViewById(R.id.editTextPassword);
        txt_email.setText("");
        txt_pass.setText("");
        view.findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_email.getText().toString().isEmpty() || txt_pass.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Empty", Toast.LENGTH_LONG).show();
                    return;
                }
                DataServices.Account acc = DataServices.login(txt_email.getText().toString(), txt_pass.getText().toString());
                if(acc == null){
                    Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_LONG).show();
                    return;
                }
                am.setAcc(acc);
                am.sendAccountFragment();
            }
        });
        view.findViewById(R.id.textViewcreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.sendRegisterFragment();
            }
        });

        return view;
    }

}
