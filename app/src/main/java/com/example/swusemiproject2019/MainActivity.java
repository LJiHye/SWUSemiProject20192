package com.example.swusemiproject2019;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.MemberModel;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName(); // 클래스명 획득

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
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
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
    }
}
