package com.example.leanne.capstonedesign_1_;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Chloe on 4/12/2016.
 * 개인정보 수정 탭 화면
 */
public class EditMyInfoActivity extends AppCompatActivity
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    DisplayMetrics displaymetrics = new DisplayMetrics();
    int screenWidth, screenHeight;
    private Button buttonMale;
    private Button buttonFemale;
    static boolean isFemaleClicked, isMaleClicked;

    private int year, month, day;
    private TextView textViewBirthday;

    private PopupWindow popupWindowUni;
    TextView textViewUniSearch;
    static String selectedUni;
    private PopupWindow popupWindowCert;
    TextView textViewAddCert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myinfo);

        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        initView();
    }

    public void initView() {
        Button saveEditMyInfo = (Button) findViewById(R.id.button_my_info_save);
        saveEditMyInfo.setOnClickListener(this);

        buttonMale = (Button) findViewById(R.id.button_male);
        buttonFemale = (Button) findViewById(R.id.button_female);
        isMaleClicked = false;
        isFemaleClicked = false;

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        textViewBirthday = (TextView) findViewById(R.id.text_birthday);
        textViewBirthday.setOnClickListener(this);
        textViewUniSearch = (TextView) findViewById(R.id.uni_extra_input);
        textViewUniSearch.setOnClickListener(this);
        textViewAddCert = (TextView) findViewById(R.id.certif_input);
        textViewAddCert.setOnClickListener(this);

        Spinner spinnerMajor = (Spinner) findViewById(R.id.spinner_major);
        ArrayAdapter adapterMajor = ArrayAdapter.createFromResource(this, R.array.majors,
                android.R.layout.simple_spinner_item);
        adapterMajor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(adapterMajor);

        Spinner spinnerCompType = (Spinner) findViewById(R.id.spinner_company_type);
        ArrayAdapter adapterCompType = ArrayAdapter.createFromResource(this, R.array.company_types,
                android.R.layout.simple_spinner_item);
        adapterCompType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompType.setAdapter(adapterCompType);

        Spinner spinnerCompDuty = (Spinner) findViewById(R.id.spinner_company_duty);
        ArrayAdapter adapterCompDuty = ArrayAdapter.createFromResource(this, R.array.company_duties,
                android.R.layout.simple_spinner_item);
        adapterCompDuty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompDuty.setAdapter(adapterCompDuty);

        updateDisplay();
    }

    // attach to an onclick handler to show the date picker
    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int setYear, int setMonth, int setDay) {
        year = setYear;
        month = setMonth;
        day = setDay;
        updateDisplay();
    }

    private void updateDisplay() {
        this.textViewBirthday.setText(
                new StringBuilder().append(year).append(".").append(month + 1).append(".").append(day));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uni_extra_input:
                LayoutInflater inflaterUni = (LayoutInflater) EditMyInfoActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layoutUni = inflaterUni.inflate(R.layout.activity_popup_university,
                        (ViewGroup) findViewById(R.id.popup_element));
                popupWindowUni = new PopupWindow(layoutUni, screenWidth / 4 * 3, screenHeight / 4 * 3);
                popupWindowUni.showAtLocation(layoutUni, Gravity.CENTER, 0, 0);
                popupWindowUni.setFocusable(true);
                popupWindowUni.update();

                EditText editTextSearch = (EditText) layoutUni.findViewById(R.id.uni_name);
                String inputString = editTextSearch.getText().toString();
                ArrayList<String> arrayListResults = new ArrayList<>();
                Button buttonSearch = (Button) layoutUni.findViewById(R.id.button_search_uni);
                buttonSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // inputString 값을 대학디비에서 찾는다
                        // 찾은 결과들은 arrayListResults에 저장
                    }
                });
                // 일단 테스트를 위해 중앙대학교로 지정
                arrayListResults.add("중앙대학교");
                final ListView listView = (ListView) layoutUni.findViewById(R.id.list);
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_single_choice);
                listView.setAdapter(adapter);
                for (int i = 0; i < arrayListResults.size(); i++) {
                    adapter.add(arrayListResults.get(i));
                }
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedUni= parent.getItemAtPosition(position).toString();
                        Log.d("TAG", selectedUni);
                    }
                });
                Button buttonSaveUni = (Button) layoutUni.findViewById(R.id.button_choose_uni);
                buttonSaveUni.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textViewUniSearch.setText(selectedUni);
                        popupWindowUni.dismiss();
                    }
                });
                Button buttonCloseUniPopup = (Button) layoutUni.findViewById(R.id.button_cancel_uni);
                buttonCloseUniPopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindowUni.dismiss();
                    }
                });
                break;
            case R.id.button_male:
                if (buttonMale.getBackground() == buttonFemale.getBackground()) {
                    buttonMale.setBackgroundResource(R.drawable.button_border_after);
                    buttonMale.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mint));
                    isMaleClicked = true;
                } else if (buttonMale.getBackground() != buttonFemale.getBackground()) {
                    buttonMale.setBackgroundResource(R.drawable.button_border_after);
                    buttonMale.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mint));
                    isMaleClicked = true;
                    buttonFemale.setBackgroundResource(R.drawable.button_boarder_before);
                    buttonFemale.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_gray));
                    isFemaleClicked = false;
                }
                break;
            case R.id.button_female:
                if (buttonMale.getBackground() == buttonFemale.getBackground()) {
                    buttonFemale.setBackgroundResource(R.drawable.button_border_after);
                    buttonFemale.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mint));
                    isFemaleClicked = true;
                } else if (buttonMale.getBackground() != buttonFemale.getBackground()) {
                    buttonFemale.setBackgroundResource(R.drawable.button_border_after);
                    buttonFemale.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mint));
                    isFemaleClicked = true;
                    buttonMale.setBackgroundResource(R.drawable.button_boarder_before);
                    buttonMale.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_gray));
                    isMaleClicked = false;
                }
                break;
            case R.id.text_birthday:
//                showDialog(DATE_DIALOG_ID);
                showDatePickerDialog();
                break;
            case R.id.certif_input:
                LayoutInflater inflaterCert = (LayoutInflater) EditMyInfoActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layoutCert = inflaterCert.inflate(R.layout.activity_popup_certificate,
                        (ViewGroup) findViewById(R.id.popup_element));
                popupWindowCert = new PopupWindow(layoutCert, RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                popupWindowCert.showAtLocation(layoutCert, Gravity.CENTER, 0, 0);
                popupWindowCert.setFocusable(true);
                popupWindowCert.update();

                Button buttonCloseCertPopup = (Button) layoutCert.findViewById(R.id.button_cancel_cert);
                buttonCloseCertPopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindowCert.dismiss();
                    }
                });
                break;
            case R.id.button_my_info_save:
                // save info on DB
                // if DB save success
                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                // if DB save failure
                // Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT.show());
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
