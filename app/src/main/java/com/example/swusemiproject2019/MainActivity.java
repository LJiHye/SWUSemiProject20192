package com.example.swusemiproject2019;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.MemberModel;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName(); // 클래스명 획득
    public static final int VIEW_DETAIL = 100;
    public static final int VIEW_SAVE = 101;

    Button btnLogin, btnReg;
    EditText txtId, txtPwd;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Database.getInstance(getApplicationContext());

        txtId = findViewById(R.id.txtId);
        txtPwd = findViewById(R.id.txtPwd);

        // 로그인 버튼
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                // ID, PW 체크
                if(txtId != null && txtPwd != null) {
                    boolean check = db.checkMember(txtId.getText().toString(), txtPwd.getText().toString());
                    Log.e(TAG, "checkMember=" + check);

                    if (check) {
                        Intent intent = new Intent(getApplicationContext(), TabActivity.class);
                        intent.putExtra("ID", txtId.getText().toString());
                        intent.putExtra("PW", txtPwd.getText().toString());

                        MemberModel member = db.getMember(txtId.getText().toString());
                        if(member != null) {
                            intent.putExtra("MEMBER", member);
                        }
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                        startActivityForResult(intent, VIEW_DETAIL);
                    }
                }
                if(txtId.getText().toString().isEmpty() || txtPwd.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 회원가입 버튼
        btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(intent);
            }
        });

        // 탭2 테스트
        Button btnTab2Test = findViewById(R.id.btnTab2Test);
        btnTab2Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TabMemoActivity.class);
                startActivity(intent);
            }
        });

        // 탭3 테스트
        Button btnTab3Test = findViewById(R.id.btnTab3Test);
        btnTab3Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateMemoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case VIEW_DETAIL:
                break;
            case VIEW_SAVE:
                // TODO
                break;
        }
    }
}
