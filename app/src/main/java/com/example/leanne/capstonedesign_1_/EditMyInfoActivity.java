package com.example.leanne.capstonedesign_1_;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

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
    static String selectedCert = "";

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

    private void addFirstCertificate() {
        LayoutInflater inflater = (LayoutInflater) EditMyInfoActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupLayout = inflater.inflate(R.layout.activity_popup_certificate,
                (ViewGroup) findViewById(R.id.popup_element));
        popupWindowCert = new PopupWindow(popupLayout, screenWidth / 4 * 3, screenHeight / 4 * 3);
        popupWindowCert.showAtLocation(popupLayout, Gravity.CENTER, 0, 0);
        popupWindowCert.setFocusable(true);
        popupWindowCert.update();

//        EditText editTextSearchCert = (EditText) layoutCert.findViewById(R.id.cert_name);
//        String inputCert = editTextSearchCert.getText().toString();
        ArrayList<String> arrayListCerts = new ArrayList<>();
        Button buttonSearchCert = (Button) popupLayout.findViewById(R.id.button_search_cert);
        buttonSearchCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inputCert 값을 자격증디비에서 찾는다
                // 찾은 결과들은 arrayListCerts에 저장
            }
        });
        // 일단 테스트를 위해 정보처리기사로 지정
        arrayListCerts.add("정보처리기사");
        final ListView listCerts = (ListView) popupLayout.findViewById(R.id.list_cert);
        final ArrayAdapter<String> adapterCert = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice);
        listCerts.setAdapter(adapterCert);
        for (int i = 0; i < arrayListCerts.size(); i++) {
            adapterCert.add(arrayListCerts.get(i));
        }
        listCerts.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listCerts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCert = parent.getItemAtPosition(position).toString();
                Log.d("TAG", selectedCert);
            }
        });
        Button buttonSaveCert = (Button) popupLayout.findViewById(R.id.button_save_cert);
        buttonSaveCert.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (Objects.equals(selectedCert, "")) {
                    Toast.makeText(popupLayout.getContext(), "자격증을 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.contents_layout);
                    TextView newInputText = new TextView(EditMyInfoActivity.this);
                    newInputText.setWidth(textViewAddCert.getWidth());
                    newInputText.setHeight(textViewAddCert.getHeight());
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule((RelativeLayout.ALIGN_PARENT_RIGHT));
                    params.addRule(RelativeLayout.BELOW, R.id.certif_input);
                    params.setMargins(0, 0, 0, 100);
                    newInputText.setBackgroundColor(Color.WHITE);
                    newInputText.setClickable(true);
                    newInputText.setHint(R.string.add);
                    newInputText.setPadding(30, 20, 20, 20);
                    newInputText.setHintTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_gray));
                    newInputText.setTextSize(20.f);
                    baseLayout.addView(newInputText, params);

                    LinearLayout fillerLayout = (LinearLayout) findViewById(R.id.filler_layout);
                    RelativeLayout.LayoutParams fillerParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    fillerParams.addRule(RelativeLayout.BELOW, newInputText.getId());
                    fillerLayout.setLayoutParams(fillerParams);

                    textViewAddCert.setText(selectedCert);
                    popupWindowCert.dismiss();
                }
            }
        });

        Button buttonCloseCertPopup = (Button) popupLayout.findViewById(R.id.button_cancel_cert);
        buttonCloseCertPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCert = "";
                popupWindowCert.dismiss();
            }
        });
    }

    private void addNextCertificate() {
        LayoutInflater inflater = (LayoutInflater) EditMyInfoActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupLayout = inflater.inflate(R.layout.activity_popup_certificate,
                (ViewGroup) findViewById(R.id.popup_element));
        popupWindowCert = new PopupWindow(popupLayout, screenWidth / 4 * 3, screenHeight / 4 * 3);
        popupWindowCert.showAtLocation(popupLayout, Gravity.CENTER, 0, 0);
        popupWindowCert.setFocusable(true);
        popupWindowCert.update();

//        EditText editTextSearchCert = (EditText) layoutCert.findViewById(R.id.cert_name);
//        String inputCert = editTextSearchCert.getText().toString();
        ArrayList<String> arrayListCerts = new ArrayList<>();
        Button buttonSearchCert = (Button) popupLayout.findViewById(R.id.button_search_cert);
        buttonSearchCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inputCert 값을 자격증디비에서 찾는다
                // 찾은 결과들은 arrayListCerts에 저장
            }
        });
        // 일단 테스트를 위해 정보처리기사로 지정
        arrayListCerts.add("정보처리기사");
        final ListView listCerts = (ListView) popupLayout.findViewById(R.id.list_cert);
        final ArrayAdapter<String> adapterCert = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice);
        listCerts.setAdapter(adapterCert);
        for (int i = 0; i < arrayListCerts.size(); i++) {
            adapterCert.add(arrayListCerts.get(i));
        }
        listCerts.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listCerts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCert = parent.getItemAtPosition(position).toString();
                Log.d("TAG", selectedCert);
            }
        });
        Button buttonSaveCert = (Button) popupLayout.findViewById(R.id.button_save_cert);
        buttonSaveCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewAddCert.setText(selectedCert);

                popupWindowCert.dismiss();
            }
        });

        Button buttonCloseCertPopup = (Button) popupLayout.findViewById(R.id.button_cancel_cert);
        buttonCloseCertPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowCert.dismiss();
            }
        });
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

//                EditText editTextSearchUni = (EditText) layoutUni.findViewById(R.id.uni_name);
//                String inputUni = editTextSearchUni.getText().toString();
                ArrayList<String> arrayListUni = new ArrayList<>();
                Button buttonSearchUni = (Button) layoutUni.findViewById(R.id.button_search_uni);
                buttonSearchUni.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // inputUni 값을 대학디비에서 찾는다
                        // 찾은 결과들은 arrayListUni에 저장
                    }
                });
                // 일단 테스트를 위해 중앙대학교로 지정
                arrayListUni.add("중앙대학교");
                final ListView listView = (ListView) layoutUni.findViewById(R.id.list_uni);
                final ArrayAdapter<String> adapterUni = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_single_choice);
                listView.setAdapter(adapterUni);
                for (int i = 0; i < arrayListUni.size(); i++) {
                    adapterUni.add(arrayListUni.get(i));
                }
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedUni = parent.getItemAtPosition(position).toString();
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
                addFirstCertificate();
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
