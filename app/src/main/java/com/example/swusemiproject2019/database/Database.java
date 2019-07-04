package com.example.swusemiproject2019.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.swusemiproject2019.model.MemberModel;
import com.example.swusemiproject2019.model.MyMemo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    static Database inst;
    private static SharedPreferences sf;

    private static List<MyMemo> memos = null; // 원본 데이터

    public final static String TBL_MEMO = "MEMO";

    private Database() {}

    public static Database getInstance(Context context) {
        if (memos == null) {
            memos = new ArrayList<>();
        }

        if (sf == null) {
            sf = context.getSharedPreferences("Memo", Activity.MODE_PRIVATE);
        }
        if (inst == null) {
            inst = new Database();
        }

        return inst;
    }

    // 회원 저장
    public void setMember(MemberModel member) {
        String memberString = new GsonBuilder().serializeNulls().create().toJson(member);
        Log.d("Database", "memberString = " + memberString);

        // 저장
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(member.getId(), memberString);

        editor.commit();
    }

    // 회원 조회
    public MemberModel getMember(String id) {
        MemberModel member = null; // 객체 선언

        // id를 이용해 회원정보 획득
        String memberString = sf.getString(id, "");
        if(memberString != null && memberString.length() > 0) {
            member = new Gson().fromJson(memberString, MemberModel.class);
        } else {
            Log.e("Database", "member null");
        }
        return member;
    }

    // 회원의 비밀번호 체크
    public boolean checkMember(String id, String pwd) {
        boolean isTrue = false;

        if(id.isEmpty() || pwd.isEmpty()) {
            return isTrue;
        }
        MemberModel member = getMember(id);

        if(member == null) {
            return isTrue;
        }

        if(member.getPwd().equals(pwd)) {
            isTrue = true;
        }
        return isTrue;
    }


    // memo 선두에 추가
    public void addMemo(MyMemo memo) { // i객체를 받아서 memos에 추가
        memos.add(0, memo); // (memo) : 리스트의 뒤에 붙음.. (0, memo) : 최근 것이 위에 뜸
    }

    // memo 획득 (index에 해당하는)
    public MyMemo getMemo(int index) {
        return memos.get(index);
    }

    // memo 변경 (index번 메모의 내용 변경)
    public void setMemo(int index, MyMemo memo) {
        memos.set(index, memo); // 덮어 씌움
    }

    // memo 삭제
    public void removeMemo(int index) {
        memos.remove(index);
    }

    // memo를 SharedPreferences에 저장
    public void saveMemos() {
        String memoString = new GsonBuilder().serializeNulls().create().toJson(memos);

        // 저장
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(TBL_MEMO, memoString);
        editor.commit();
    }

    // SharedPreferences에서 memos 획득
    public List<MyMemo> loadMemos() {
        String memoString = sf.getString(TBL_MEMO, "");

        if(!memoString.isEmpty()) {
            MyMemo[] memoArray = new Gson().fromJson(memoString, MyMemo[].class);

            memos = new ArrayList<>(Arrays.asList(memoArray));
        }
        return memos;
    }
}
