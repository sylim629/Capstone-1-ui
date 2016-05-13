package com.example.leanne.capstonedesign_1_;


import android.app.Application;

import java.util.ArrayList;

/**
 * Created by dalaetm on 2016-04-08.
 */
public class LoggedInUser extends Application {
    private String id;
    private String user_name;
    private String passWd;
    private int toeic;
    private int age;
    private String major;
    private String com_type;
    private String duty;
    private String com_name;
    private boolean gender;
    private String univ;
    private String certifi;
    private boolean isEmp;

    private String search_major;
    private String search_com_type;
    private String search_duty;
    private String search_com_name;
    private boolean search_gender;
    private String search_univ;
    private int search_age;

    private ArrayList<String> fav_ids;      //일단 이 맴버변수는 필요하다!

    public LoggedInUser(){
        id = null;
        user_name = null;
        passWd = null;
        toeic = 0;
        age = 0;
        major = null;
        com_type = null;
        duty = null;
        com_name = null;
        gender = false;
        univ = null;
        certifi = null;
        isEmp = false;

        search_major = null;
        search_com_type = null;
        search_duty = null;
        search_com_name = null;
        search_gender = false;
        search_univ = null;
        search_age = 0;

        fav_ids = new ArrayList<String>();      //일단 이 맴버변수는 필요하다!
    }


    public String getId(){
        return id;
    }
    public String getUserName(){
        return user_name;
    }
    public String getPassWd(){
        return passWd;
    }
    public int getToeic(){
        return toeic;
    }
    public int getAge(){
        return age;
    }
    public String getMajor(){
        return major;
    }
    public String getCom_type(){
        return com_type;
    }
    public String getDuty(){
        return duty;
    }
    public String getCom_name(){
        return com_name;
    }
    public boolean getGender(){
        return gender;
    }
    public String getUniv(){
        return univ;
    }
    public String getCertifi(){
        return certifi;
    }
    public boolean getIsEmp(){
        return isEmp;
    }
    public String getSearch_Major(){
        return search_major;
    }
    public String getSearch_Com_type(){
        return search_com_type;
    }
    public String getSearch_Duty(){
        return search_duty;
    }
    public String getSearch_Com_name(){
        return search_com_name;
    }
    public boolean getSearch_Gender(){
        return search_gender;
    }
    public String getSearch_Univ(){
        return search_univ;
    }
    public int getSearch_Age(){
        return search_age;
    }
    public ArrayList<String> getFav_ids(){
        return fav_ids;
    }



    public void setId(String input){
        id = input;
    }
    public void setUserName(String input){
        user_name = input;
    }
    public void setPassWd(String input){
        passWd = input;
    }
    public void setToeic(int input){
        toeic = input;
    }
    public void setAge(int input){
        age = input;
    }
    public void setMajor(String input){
        major = input;
    }
    public void setCom_type(String input){
        com_type = input;
    }
    public void setDuty(String input){
        duty = input;
    }
    public void setCom_name(String input){
        com_name = input;
    }
    public void setGender(boolean input){
        gender = input;
    }
    public void setUniv(String input){
        univ = input;
    }
    public void setCertifi(String input){
        certifi = input;
    }
    public void setIsEmp(boolean input){
        isEmp = input;
    }
    public void setSearch_age(int input){
        search_age = input;
    }
    public void setSearch_major(String input){
        search_major = input;
    }
    public void setSearch_com_type(String input){
        search_com_type = input;
    }
    public void setSearch_duty(String input){
        search_duty = input;
    }
    public void setSearch_com_name(String input){
        search_com_name = input;
    }
    public void setSearch_gender(boolean input){
        search_gender = input;
    }
    public void setSearch_univ(String input){
        search_univ = input;
    }

    /*public void setFav_ids(String input){
        String[] tokens = input.split("|",0);
        for(int i = 0 ; i < tokens.length ; i++){
            fav_ids.add( new String(tokens[i]) );   //Fav아이디들을 arrayList에 저장
        }
    }*/

    public void setFav_ids(String input) {  // 원래는 위에 주석 처리된 함수였는데 그냥 하나씩 넣는걸로 바꿈.. 괜히 복잡한 것 같아서.. 아니면 내가 놓친 다른 이유가 있었는지??
        fav_ids.add(input);
    }

    public static LoggedInUser getLoggedinUser(){
        return loggedinUser;
    }

    private static LoggedInUser loggedinUser = new LoggedInUser();

    // 그냥 다 setFav_ids로 하면 안되나?? 이것도 내가 놓친게 있는지ㅠㅠ?
    /* adds new id to FavId ArrayList *//*
    public void addFav_id(String newFavId) {

    }*/

    /* deletes id from FavId ArrayList */
    public void deleteFav_id(String deleteFavId) {
        fav_ids.remove(deleteFavId);
    }

}