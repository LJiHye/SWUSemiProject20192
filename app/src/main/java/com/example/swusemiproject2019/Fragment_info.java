package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swusemiproject2019.model.MemberModel;

public class Fragment_info extends Fragment {
    MemberModel member;

    public Fragment_info() {} // Default Constructor

    public Fragment_info(MemberModel member) {
        this.member = member;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        TextView infoId, infoPwd, infoName;
        ImageView infoImg;

        infoId = view.findViewById(R.id.infoId);
        infoPwd = view.findViewById(R.id.infoPwd);
        infoName = view.findViewById(R.id.infoName);
        infoImg = view.findViewById(R.id.infoImg);

        infoId.setText(member.getId());
        infoPwd.setText(member.getPwd());
        //infoImg.setImageResource(member.getProfile());
        infoName.setText(member.getName());

        return view;
    }
}
