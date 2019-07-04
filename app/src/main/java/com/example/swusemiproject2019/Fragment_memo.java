package com.example.swusemiproject2019;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.MyMemo;

import java.util.ArrayList;
import java.util.List;

public class Fragment_memo extends Fragment {
    public static final int SAVE = 1001;
    public Fragment_memo() {} // Default Constructor
    ListView listView;
    List<MyMemo> memos = new ArrayList<>();
    ListAdapter adapter;
    Button btnCreateMemo, btn_modify, btn_detail, btn_remove;

    Database db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);
        listView = (ListView) view.findViewById(R.id.list_memo);
        btnCreateMemo = view.findViewById(R.id.btnCreateMemo);

        /*memos.add(new MyMemo(R.drawable.ic_launcher_background, "포도", "신선한 포도"));*/

        memos = Database.getInstance(getContext()).loadMemos();

        adapter = new ListAdapter(memos, getActivity());
        listView.setAdapter(adapter);

        btnCreateMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateMemoActivity.class);
                startActivityForResult(intent, SAVE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SAVE) {
            memos = Database.getInstance(getContext()).loadMemos();
            adapter.setItems(memos);
            adapter.notifyDataSetChanged();
        }
    }

    class ListAdapter extends BaseAdapter {
        List<MyMemo> memos;
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter(List<MyMemo> memos, Context context) {
            this.memos = memos;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public void setItems(List<MyMemo> memos) {
            this.memos = memos;
        }

        @Override
        public int getCount() {
            return memos.size();
        }

        @Override
        public Object getItem(int i) {
            return memos.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.view_memo, null);

            ImageView memoImg = view.findViewById(R.id.memoImg);
            TextView txtvMemo = view.findViewById(R.id.txtvMemo);
            TextView txtvDate = view.findViewById(R.id.txtvDate);
            db = Database.getInstance(getActivity());

            MyMemo memo = memos.get(i);

            memoImg.setImageResource(memo.getImgId());
            txtvMemo.setText(memo.getMemo());
            txtvDate.setText(memo.getDate());

            btn_modify = view.findViewById(R.id.btn_modify);
            btn_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), "수정..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), TabMemoActivity.class);
                    startActivity(intent);
                }
            });
            btn_detail = view.findViewById(R.id.btn_detail);
            btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), "상세..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), TabMemoActivity.class);
                    startActivity(intent);
                }
            });
            btn_remove = view.findViewById(R.id.btn_remove);
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), "삭제..", Toast.LENGTH_SHORT).show();
                    db.removeMemo(i);
                    Toast.makeText(getContext(), "삭제", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
