package com.example.group1_1inclass_04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    AccountManager am;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        getActivity().setTitle("Registered Account");
        EditText txt_name = view.findViewById(R.id.editTextName2);
        EditText txt_email = view.findViewById(R.id.editTextEmail2);
        EditText txt_pass = view.findViewById(R.id.editTextTextPassword);
        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_name.getText().toString().isEmpty() || txt_email.getText().toString().isEmpty() || txt_pass.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Empty", Toast.LENGTH_LONG).show();
                    return;
                }
                DataServices.Account acc = DataServices.register(txt_name.getText().toString(), txt_email.getText().toString(), txt_pass.getText().toString());
                if(acc == null){
                    Toast.makeText(getActivity(), "invalid Register", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                am.setAcc(acc);
                am.sendAccountFragment();
            }
        });
        view.findViewById(R.id.textViewCancel2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.sendLoginFragment();
            }
        });
        return view;
    }
}