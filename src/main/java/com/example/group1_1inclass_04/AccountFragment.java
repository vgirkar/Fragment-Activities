package com.example.group1_1inclass_04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccountFragment extends Fragment {

    private static final String ACCOUNT = "Account";

    private DataServices.Account account;

    AccountManager am;

    public static AccountFragment newInstance(DataServices.Account account) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(ACCOUNT, account);
        fragment.setArguments(args);
        return fragment;
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
        if (getArguments() != null) {
            account = (DataServices.Account)getArguments().getSerializable(ACCOUNT);
        }
    }

    public void updateAccount(){
        this.account = am.getAcc();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        updateAccount();
        getActivity().setTitle("Account");
        TextView txt_name = view.findViewById(R.id.textViewName3);
        txt_name.setText(account.getName());

        view.findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.sendUpdateFragment();
            }
        });

        view.findViewById(R.id.buttonLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.setAcc(null);
                am.sendLoginFragment();
            }
        });
        return view;
    }
}