package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by imsuyeon on 16. 4. 2..
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private BackPressCloseHandler backPressCloseHandler;
    private Button buttonLogin;
    private TextView textViewRegister, textViewFindPW;
    private EditText editTextID, editTextPW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        backPressCloseHandler = new BackPressCloseHandler(this);
        buttonLogin = (Button)findViewById(R.id.button_login);
        textViewRegister = (TextView)findViewById(R.id.textView_register);
        textViewFindPW = (TextView)findViewById(R.id.textView_find_pw);
        editTextID = (EditText)findViewById(R.id.editText_id);
        editTextPW = (EditText)findViewById(R.id.editText_pw);
        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
        textViewFindPW.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                String id = editTextID.getText().toString();
                String pw = editTextPW.getText().toString();

                // temp
                String dbID = "root";
                String dbPW = "root";
                // end temp

                if (id.equals("") || pw.equals("")) {
                    Toast.makeText(this, "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (id.equals(dbID) && pw.equals(dbPW)) {
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    Intent intentHome = new Intent(this, HomeActivity.class);
                    startActivity(intentHome);
                    this.overridePendingTransition(R.anim.animation_enter_right2left,
                            R.anim.animation_leave_right2left);
                    editTextID.setText("");
                    editTextPW.setText("");
                } else {
                    Toast.makeText(this, "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    editTextID.setText("");
                    editTextPW.setText("");
                }
                break;
            case R.id.textView_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                this.overridePendingTransition(R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
            case R.id.textView_find_pw:
                Intent intentFindPW = new Intent(this, FindPasswordActivity.class);
                startActivity(intentFindPW);
                this.overridePendingTransition(R.anim.animation_enter_right2left, R.anim.animation_leave_right2left);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();}
}
