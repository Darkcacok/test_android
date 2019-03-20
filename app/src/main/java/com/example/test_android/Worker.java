package com.example.test_android;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Worker implements Serializable{

    private String f_name;
    private String l_name;
    private String birthday;
    private String avatr_url;
    private ArrayList<Specialty> specialty =  new ArrayList<Specialty>();


    public Worker(String f_name, String l_name, String birthday, String avatr_url, Specialty specialty)
    {
        fullName(f_name, l_name);
        this.birthday = birthday;
        this.avatr_url = avatr_url;
        this.specialty.add(specialty);
    }

    public Worker(String f_name, String l_name, String birthday, Specialty specialty)
    {
        fullName(f_name, l_name);
        this.birthday = birthday;
        this.avatr_url = null;
        this.specialty.add(specialty);
    }

    public Worker(String f_name, String l_name, String birthday, ArrayList<Specialty> specialty)
    {
        fullName(f_name, l_name);
        this.birthday = birthday;
        this.avatr_url = null;
        this.specialty = specialty;
    }

    private void fullName(String f_name, String l_name){
        this.f_name = format(f_name);
        this.l_name = format(l_name);
    }

    public String getFirstName() {
        return f_name;
    }

    public String getLastName() {
        return l_name;
    }

    public String getAge() {
        Date birth = getDate();
        if(birth == null)
            return "";

        Date date = new Date();
        return String.valueOf(date.getYear() - birth.getYear());
    }

    public String getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy г.");

        Date birth = getDate();
        if(birth == null)
            return "-";

        return sdf.format(birth);
    }


    public String getAvatr_url() {
        return avatr_url;
    }

    public boolean isSpecialty(Specialty specialty) {
        for(int i = 0; i < this.specialty.size(); i++)
            if(this.specialty.get(i).getSpecialty_id() == specialty.getSpecialty_id())
                return true;

        return false;
    }

    private String format(String str){
        StringBuilder builder = new StringBuilder(str);

        for(int i = 0; i < 1 && i < str.length(); i++)
            if(Character.isLetter(str.codePointAt(i)))
                builder.setCharAt(i, Character.toUpperCase(str.charAt(i)));

        for(int i = 1; i < str.length(); i++)
            if(Character.isLetter(str.charAt(i)))
                builder.setCharAt(i, Character.toLowerCase(str.charAt(i)));

        return builder.toString();
    }

    public String getSpecialty() {
        String spec = new String();
        for(int i = 0; i < this.specialty.size(); ++i) {
            if(!spec.isEmpty())
                spec+=", ";
            spec += this.specialty.get(i).getName();
        }

        return spec;
    }

    public ArrayList<Specialty> getArraySpecialty(){
        return specialty;
    }

    public Date getDate()
    {
        if(birthday == null || birthday.equals(""))
            return null;


        String[] formatStrings = new String[]{new String("MM-dd-yyyy"), new String("yyyy-MM-dd")};

        String p1 = "\\d{2}-\\d{2}-\\d{4}";
        String p2 = "\\d{4}-\\d{2}-\\d{2}";

        if(this.birthday.matches(p1))
            try {
                return new SimpleDateFormat(formatStrings[0]).parse(birthday);
            }catch (ParseException e){}
         else if(this.birthday.matches(p2))
            try {
                return new SimpleDateFormat(formatStrings[1]).parse(birthday);
            }catch (ParseException e){}

       /* for(int i = 0; i < formatStrings.length; i++ )
        {
            try{
                return new SimpleDateFormat(formatStrings[i]).parse(birthday);
            }catch (ParseException e){}
        }
*/
        return null;
    }
}
