package com.example.swusemiproject2019;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swusemiproject2019.database.Database;
import com.example.swusemiproject2019.model.MemberModel;

public class RegActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName(); // 클래스명 획득

    EditText editId, editName, editPwd, editCheckPwd;
    ImageView imgReg;
    Button btnPic, btnReg;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        editId = findViewById(R.id.infoId);
        editName = findViewById(R.id.editName);
        editPwd = findViewById(R.id.editPwd);
        editCheckPwd = findViewById(R.id.editCheckPwd);

        imgReg = findViewById(R.id.imgReg);

        btnPic = findViewById(R.id.btnPic);
        btnReg = findViewById(R.id.btnReg);

        db = Database.getInstance(getApplicationContext());

        // 회원가입 버튼
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberModel checkMember = db.getMember(editId.getText().toString());
                if (checkMember != null) {
                    Toast.makeText(getApplication(),"존재하는 아이디입니다.", Toast.LENGTH_LONG).show();
                } else {
                    if(editPwd.getText().toString().equals(editCheckPwd.getText().toString())) {
                        MemberModel member = new MemberModel();
                        member.setId(editId.getText().toString());
                        member.setPwd(editPwd.getText().toString());
                        member.setName(editName.getText().toString());
                        member.setProfile(imgReg.getTextDirection());
                        Database.getInstance(getApplicationContext()).setMember(member);

                        db.setMember(member);

                        Toast.makeText(getApplication(), "회원 가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "비밀 번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
