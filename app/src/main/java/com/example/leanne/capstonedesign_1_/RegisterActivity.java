package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by imsuyeon on 16. 4. 4..
 * 회원가입 페이지
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText editTextId, editTextPassword, editTextConfirmPassword, editTextName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextId = (EditText) findViewById(R.id.editText_id);
        editTextPassword = (EditText) findViewById(R.id.editText_pw);
        editTextConfirmPassword = (EditText) findViewById(R.id.editText_pw_confirm);

        Button buttonRegister = (Button) findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(this);
        Button buttonCheckID = (Button) findViewById(R.id.button_check_id);
        buttonCheckID.setOnClickListener(this);

        /* focus change - detect invalid characters */
        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            String strToCheck;
            boolean checkResult;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    strToCheck = editTextName.getText().toString();
                    checkResult = checkString(strToCheck);
                    // if there is invalid character in string
                    if(checkResult) {
                        showInvalidCharToast();
                        editTextName.setText("");
                    }
                }
            }
        });
        editTextId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            boolean checkResult;
            String strToCheck;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    strToCheck = editTextId.getText().toString();
                    checkResult = checkString(strToCheck);
                    // if there is invalid character in string
                    if(checkResult) {
                        showInvalidCharToast();
                        editTextId.setText("");
                    }
                }
            }
        });
        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            String strToCheck;
            boolean checkResult;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    strToCheck = editTextPassword.getText().toString();
                    checkResult = checkString(strToCheck);
                    // if there is invalid character in string
                    if(checkResult) {
                        showInvalidCharToast();
                        editTextPassword.setText("");
                    }
                }
            }
        });
        editTextConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            boolean checkResult;
            String strToCheck;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    strToCheck = editTextConfirmPassword.getText().toString();
                    checkResult = checkString(strToCheck);
                    // if there is invalid character in string
                    if(checkResult) {
                        showInvalidCharToast();
                        editTextConfirmPassword.setText("");
                    }
                }
            }
        });
    }

    /* checks to see if given string contains an invalid character --> ;, :, \, | */
    private boolean checkString(String str) {
        if(str.contains(";") | str.contains(":") | str.contains("\\") | str.contains("|")) {
            return true;
        }
        else return false;

    }

    /* shows toast message if string contains an invalid character */
    private void showInvalidCharToast () {
        Context context = getApplicationContext();
        CharSequence text = ":, ;, \\, |는 입력하실 수 없습니다.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check_id:
                // 중복확인 버튼을 누른 경우 해야 할 것
                break;
            case R.id.button_register:
                String inputID = editTextId.getText().toString();
                String inputPW = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String inputName = editTextName.getText().toString();

                // 특수문자 예외처리
                if(inputID.contains(";") || inputID.contains(":") || inputID.contains("\\") || inputID.contains("|") ||
                        inputPW.contains(";") || inputPW.contains(":") || inputPW.contains("\\") || inputPW.contains("|") ||
                        confirmPassword.contains(";") || confirmPassword.contains(":") || confirmPassword.contains("\\") || confirmPassword.contains("|") ||
                        inputName.contains(";") || inputName.contains(":") || inputName.contains("\\") || inputName.contains("|") ) {
                    Toast.makeText(this, ";,:,\\,| 입력은 불가능합니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
                // end 특수문자 예외처리

                if (inputID.equals("") || inputPW.equals("") || confirmPassword.equals("")
                        || inputName.equals("")) {
                    Toast.makeText(getApplicationContext(), "입력되지 않은 부분이 있습니다.", Toast.LENGTH_LONG).show();
                } else {
                    if (!inputPW.equals(confirmPassword)) {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        LoggedInUser.getLoggedinUser().setUserName(inputName);
                        LoggedInUser.getLoggedinUser().setId(inputID);
                        LoggedInUser.getLoggedinUser().setPassWd(inputPW);
                        Toast.makeText(getApplicationContext(), "계정만들기 성공!", Toast.LENGTH_LONG).show();
                        Intent intentExtraInfo = new Intent(RegisterActivity.this, ExtraInfoActivity.class);
                        startActivity(intentExtraInfo);
                        overridePendingTransition(R.anim.animation_enter_right2left, R.anim.animation_leave_right2left);
                        this.finish();
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
