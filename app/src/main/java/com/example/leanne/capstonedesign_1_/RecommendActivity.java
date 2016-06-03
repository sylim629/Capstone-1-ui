package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chloe on 5/11/2016.
 *
 */
public class RecommendActivity extends Activity {

    private TextView rec_company_1, rec_company_2, rec_company_3, rec_duty_1, rec_duty_2, rec_duty_3, rec_rankings_id1, rec_rankings_id2, rec_rankings_id3, rec_rankings_major1, rec_rankings_major2, rec_rankings_major3, rec_rankings_wish_duty1, rec_rankings_wish_duty2, rec_rankings_wish_duty3, rec_rankings_certificates1, rec_rankings_certificates2, rec_rankings_certificates3, rec_rankings_toeicScore1, rec_rankings_toeicScore2, rec_rankings_toeicScore3;

    private List<ListViewItem> mItems;
    private String rankingResult;   // 여기에 서버에서 스트링 받기
    String comp1, comp2, comp3, duty1, duty2, duty3;

    private String[] idInfo = new String[3];
    private String[] majorInfo = new String[3];
    private String[] dutyInfo = new String[3];
    private String[] certifiInfo = new String[3];
    private String[] toeicInfo = new String[3];
    private String[] ageInfo = new String[3];
    private String[] wishCompTypeInfo = new String[3];
    private String[] wishCompInfo = new String[3];
    private String[] genderInfo = new String[3];
    private String[] uniInfo = new String[3];
    private String[] empInfo = new String[3];
    private String[] gpaInfo = new String[3];
    private String[] careerInfo = new String[3];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Resources resources = getResources();

        RequestMsgSender userInfoMsgSender = (RequestMsgSender) new RequestMsgSender().execute("14;");
        String rankingResult = null;

        try {
            rankingResult = userInfoMsgSender.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        rankingResult = "회사1;회사2;회사3;직무1;직무2;직무3;kwjel88;885;29;컴퓨터공학부;company_type1;웹기획∙웹마케팅∙PM;company_name1;gender1;univ1;정보처리기사;isEmp;3.9;4.5;|인턴:기관1:1개월|;iammeee;795;22;컴퓨터공학부;company_type2;통신∙모바일;company_name2;gender2;univ2;정보처리기사|정보보안기사;isEmp;3.5;4.5;|인턴:기관2:2개월|;qwerty101;835;27;컴퓨터공학부;company_type3;서버∙네트워크∙보안;company_name3;gender3;univ3;정보처리기사|정보보안기사;isEmp;3.85;4.5;|인턴:기관3:3개월|";
        // kwjel88;885;29;컴퓨터공학부;company_type1;웹기획∙웹마케팅∙PM;company_name1;gender1;univ1;정보처리기사;isEmp;3.9;4.5;|인턴:기관1:1개월|;iammeee;795;22;컴퓨터공학부;company_type2;통신∙모바일;company_name2;gender2;univ2;정보처리기사|정보보안기사;isEmp;3.5;4.5;|인턴:기관2:2개월|;qwerty101;835;27;컴퓨터공학부;company_type3;서버∙네트워크∙보안;company_name3;gender3;univ3;정보처리기사|정보보안기사;isEmp;3.85;4.5;|인턴:기관3:3개월|
        String[] tokens = rankingResult.split(";");
        for(int i = 0 ; i < tokens.length ; i++){
            if(tokens[i].equals("!")) tokens[i]="";
        }

        ArrayList<String> idFavs = new ArrayList<String>();
        idFavs = LoggedInUser.getLoggedinUser().getFav_ids();
        boolean[] isFavArray = new boolean[3];
        int k=0;
        for(int i=1; k<3; i+=14, k++) {
            String id = tokens[i];
            for(int l=0; l<idFavs.size(); l++) {
                if (id.equals(idFavs.get(l))) {    // LoggedInUser의 Fav ID와 비교해서 일치하면 isFav = true. 아니면 false
                    isFavArray[k] = true;
                    break;
                }else
                    isFavArray[k] = false;
            }
        }

        comp1 = tokens[0];
        comp2 = tokens[1];
        comp3 = tokens[2];
        duty1 = tokens[3];
        duty2 = tokens[4];
        duty3 = tokens[5];

        String strToDelete = comp1 + ";" + comp2 + ";" + comp3 + ";" + duty1 + ";" + duty2 + ";" + duty3 +  ";";
        rankingResult = rankingResult.replace(strToDelete, "");
        String[] cutTokens = rankingResult.split(";");

        int j = 0;
        for(int i = 0 ; j < 3 ; i+=14, j++ ) {
            String str_idInfo = new String("ID : ");
            String str_majorInfo = new String("전공 : ");
            String str_dutyInfo = new String("직무 : ");
            String str_certifiInfo = new String("자격증 : ");
            String str_toeicInfo = new String("토익 :  ");
            String str_ageInfo = new String("나이 : ");
            String str_wishCompTypeInfo = new String("희망 회사 종류: ");
            String str_wishCompInfo = new String("희망 회사: ");
            String str_genderInfo = new String("성별: ");
            String str_uniInfo = new String("대학: ");
            String str_empInfo = new String("취업여부: ");
            String str_gpaInfo = new String("학점: ");
            String str_careerInfo = new String("경력: ");
            //---------------
            // kwjel88;885;29;컴퓨터공학부;company_type1;웹기획∙웹마케팅∙PM;company_name1;gender1;univ1;정보처리기사;isEmp;3.9;4.5;|인턴:기관1:1개월|;iammeee;795;22;컴퓨터공학부;company_type2;통신∙모바일;company_name2;gender2;univ2;정보처리기사|정보보안기사;isEmp;3.5;4.5;|인턴:기관2:2개월|;qwerty101;835;27;컴퓨터공학부;company_type3;서버∙네트워크∙보안;company_name3;gender3;univ3;정보처리기사|정보보안기사;isEmp;3.85;4.5;|인턴:기관3:3개월|

            idInfo[j] = str_idInfo += cutTokens[i];
            majorInfo[j] = str_majorInfo += cutTokens[i + 3];
            dutyInfo[j] = str_dutyInfo += cutTokens[i + 5];
            toeicInfo[j] = str_toeicInfo += cutTokens[i + 1];
            str_certifiInfo = str_certifiInfo += cutTokens[i + 9];
            certifiInfo[j] = str_certifiInfo.replace("|", ",");
            ageInfo[j] = str_ageInfo += cutTokens[i + 2];
            wishCompTypeInfo[j] = str_wishCompTypeInfo += cutTokens[i + 4];
            wishCompInfo[j] = str_wishCompInfo += cutTokens[i + 6];
            genderInfo[j] = str_genderInfo += cutTokens[i + 7];
            uniInfo[j] = str_uniInfo += cutTokens[i + 8];
            empInfo[j] = str_empInfo += cutTokens[i + 10];
            gpaInfo[j] = str_gpaInfo += (cutTokens[i + 11] + "/" + cutTokens[i + 12]);
            careerInfo[j] = str_careerInfo += alterCareer(cutTokens[i + 13]);
        }

        setContentView(R.layout.activity_recommend);
        initView();
    }

    public String alterCareer(String input){
        String result = "";
        if( input != null ){
            input = input.substring(1, input.length()-1);
            String[] tokens = input.split(":",0);
            for(int j = 0 ; j < tokens.length ; j++ ){
                result += (tokens[j] + " ");
            }
        }
        return result;
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
        showInfo();
    }

    private void showInfo() {
        // 서버에서 받은 값들로 TextView에서 보여주기

        rec_company_1.setText(comp1);
        rec_company_2.setText(comp2);
        rec_company_3.setText(comp3);

        rec_duty_1.setText(duty1);
        rec_duty_2.setText(duty2);
        rec_duty_3.setText(duty3);

        rec_rankings_id1.setText(idInfo[0]);
        rec_rankings_id2.setText(idInfo[1]);
        rec_rankings_id3.setText(idInfo[2]);

        rec_rankings_major1.setText(majorInfo[0]);
        rec_rankings_major2.setText(majorInfo[1]);
        rec_rankings_major3.setText(majorInfo[2]);

        rec_rankings_wish_duty1.setText(dutyInfo[0]);
        rec_rankings_wish_duty2.setText(dutyInfo[1]);
        rec_rankings_wish_duty3.setText(dutyInfo[2]);

        rec_rankings_certificates1.setText(certifiInfo[0]);
        rec_rankings_certificates2.setText(certifiInfo[1]);
        rec_rankings_certificates3.setText(certifiInfo[2]);

        rec_rankings_toeicScore1.setText(toeicInfo[0]);
        rec_rankings_toeicScore2.setText(toeicInfo[1]);
        rec_rankings_toeicScore3.setText(toeicInfo[2]);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
