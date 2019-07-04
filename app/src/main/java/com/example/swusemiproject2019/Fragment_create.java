package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.MemberModel;
import com.example.swusemiproject2019.model.MyMemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_create extends Fragment {
    MemberModel member;
    MyMemo memo;
    Button btnCreateMemo;
    EditText txtMemo;

    public Fragment_create() {} // Default Constructor

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        txtMemo = view.findViewById(R.id.txtMemo);
        btnCreateMemo = view.findViewById(R.id.btnCreateMemo);

        btnCreateMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memo = new MyMemo();
                memo.setMemo(txtMemo.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy.MM.dd");
                Date currentTime = new Date();
                String dTime = sdf.format(currentTime);
                memo.setDate(dTime);
                memo.setImgId(R.drawable.ic_launcher_background);

                Database db = Database.getInstance(getActivity());
                db.addMemo(memo);
                db.saveMemos();
                getActivity().finish();
                Toast.makeText(getContext(), "저장 완료", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}