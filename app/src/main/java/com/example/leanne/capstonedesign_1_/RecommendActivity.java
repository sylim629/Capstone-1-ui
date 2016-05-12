package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Chloe on 5/11/2016.
 */
public class RecommendActivity extends Activity {

    private TextView rec_company_1, rec_company_2, rec_company_3, rec_duty_1, rec_duty_2, rec_duty_3, rec_rankings_id1, rec_rankings_id2, rec_rankings_id3, rec_rankings_major1, rec_rankings_major2, rec_rankings_major3, rec_rankings_wish_duty1, rec_rankings_wish_duty2, rec_rankings_wish_duty3, rec_rankings_certificates1, rec_rankings_certificates2, rec_rankings_certificates3, rec_rankings_toeicScore1, rec_rankings_toeicScore2, rec_rankings_toeicScore3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        initView();
    }

    private void initView() {
        rec_company_1 = (TextView) findViewById(R.id.rec_company_1);
        rec_company_2 = (TextView) findViewById(R.id.rec_company_2);
        rec_company_3 = (TextView) findViewById(R.id.rec_company_3);
        rec_duty_1 = (TextView) findViewById(R.id.rec_duty_1);
        rec_duty_2 = (TextView) findViewById(R.id.rec_duty_2);
        rec_duty_3 = (TextView) findViewById(R.id.rec_duty_3);
        rec_rankings_id1 = (TextView) findViewById(R.id.rec_rankings_id1);
        rec_rankings_id2 = (TextView) findViewById(R.id.rec_rankings_id2);
        rec_rankings_id3 = (TextView) findViewById(R.id.rec_rankings_id3);
        rec_rankings_major1 = (TextView) findViewById(R.id.rec_rankings_major1);
        rec_rankings_major2 = (TextView) findViewById(R.id.rec_rankings_major2);
        rec_rankings_major3 = (TextView) findViewById(R.id.rec_rankings_major3);
        rec_rankings_wish_duty1 = (TextView) findViewById(R.id.rec_rankings_wish_duty1);
        rec_rankings_wish_duty2 = (TextView) findViewById(R.id.rec_rankings_wish_duty2);
        rec_rankings_wish_duty3 = (TextView) findViewById(R.id.rec_rankings_wish_duty3);
        rec_rankings_certificates1 = (TextView) findViewById(R.id.rec_rankings_certificates1);
        rec_rankings_certificates2 = (TextView) findViewById(R.id.rec_rankings_certificates2);
        rec_rankings_certificates3 = (TextView) findViewById(R.id.rec_rankings_certificates3);
        rec_rankings_toeicScore1 = (TextView) findViewById(R.id.rec_rankings_toeicScore1);
        rec_rankings_toeicScore2 = (TextView) findViewById(R.id.rec_rankings_toeicScore2);
        rec_rankings_toeicScore3 = (TextView) findViewById(R.id.rec_rankings_toeicScore3);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
