package com.example.group1_1inclass_04;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment {

    private static final String ACCOUNT = "Account";

    private DataServices.Account account;

    AccountManager am;

    public static UpdateFragment newInstance(DataServices.Account acc) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ACCOUNT, acc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            account = (DataServices.Account) getArguments().getSerializable(ACCOUNT);
        }
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
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        getActivity().setTitle("Update");
        TextView txt_email = view.findViewById(R.id.textViewgetEmail);
        txt_email.setText(account.getEmail());
        EditText txt_name = view.findViewById(R.id.editTextName4);
        txt_name.setText(account.getName());
        EditText txt_pass = view.findViewById(R.id.editTextTextPassword2);
        txt_pass.setText(account.getPassword());
        view.findViewById(R.id.buttonsubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_name.getText().toString().isEmpty() || txt_pass.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                    return;
                }
                DataServices.Account acc = DataServices.update(am.getAcc(), txt_name.getText().toString(), txt_pass.getText().toString());
                if(acc == null){
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                am.setAcc(acc);
                am.goBack();
            }
        });
        view.findViewById(R.id.textViewCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.goBack();
            }
        });
        return view;
    }
}