package com.example.quintinhill.scheduled;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {
    Spinner sp1,sp2,sp3;
    CheckBox priority;
    Boolean important = false;
    String selectedClass, subjectID, subjectIDRatings;
    ArrayList<Pair<String,Boolean>> classes;//important, when finalized is pressed, this is the arra
                                            //y of classes and priorities
    ArrayAdapter<String> adp1,adp2,adp3;
    ArrayList<String> l1,l2;
    //double spinner to choose class
    int classCount;
    int pos;
    int cc;
    int desired = 3;//this is the value of classes student wants to take
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cc=0;

        //DROP DOWN BOXES



        l1=new ArrayList<String>();
        classes = new ArrayList<Pair<String,Boolean>>();
        l1.add("-Subject-");

            /* Read the Subjects.txt file */
            /* code for reading from a txt file used from
                 https://stackoverflow.com/questions/9544737/read-file-from-assets
            */
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("subjects.txt")));

            // do reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
               l1.add(line);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
                /* end of reading the subjects */

        sp1= (Spinner) findViewById(R.id.spinner1);
        sp2= (Spinner) findViewById(R.id.spinner2);

        adp1=new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line,l1);
        adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp1.setAdapter(adp1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                pos=arg2;
                add();

            }

            private void add() {
                classCount = 0;

                if (pos == 0) {
                    l2 = new ArrayList<String>();
                    l2.add("-Course-");

                    adp2 = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_dropdown_item_1line, l2);
                    adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    sp2.setAdapter(adp2);

                }
                else {
                        l2 = new ArrayList<String>();
                        subjectID = l1.get(pos) + ".txt";
                        subjectID = subjectID.toLowerCase();
                        subjectID = subjectID.replaceAll(" ","");

                        /* Read the class.txt file */
                         BufferedReader reader = null;
                         try {
                               reader = new BufferedReader(
                                new InputStreamReader(getAssets().open(subjectID)));

                              // do reading, usually loop until end of file reading
                              String line;
                              while ((line = reader.readLine()) != null) {
                                  ++classCount;
                                  if (classCount < 10) {
                                      l2.add(line.substring(3));
                                  }
                                  else{
                                      l2.add(line.substring(4));
                                  }
                               }
                           } catch (IOException e) {
                               //log the exception
                           } finally {
                             if (reader != null) {
                                 try {
                                     reader.close();
                                 } catch (IOException e) {
                                     //log the exception
                                 }
                             }
                             if (classCount==0) l2.add("No Classes in " + subjectID.substring(0,subjectID.length()-4).toUpperCase());


                             adp2 = new ArrayAdapter<String>(MainActivity.this,
                                     android.R.layout.simple_dropdown_item_1line, l2);
                             adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                             sp2.setAdapter(adp2);

                             select();
                         }

                }

            }

            private void select() {
                // TODO Auto-generated method stub

                sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        selectedClass = l2.get(arg2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //**************************************
        //********PRIORITY CHECK BOX************
        //**************************************

        priority = (CheckBox) findViewById(R.id.firstpri);
        priority.setText("Priority");
        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(important == true)
                    important = false;
                else
                    important = true;
            }
        });


        //****************************************
        //*******Desired class spinner************
        //****************************************



        sp3= (Spinner) findViewById(R.id.spinner3);
        adp3=new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.Desired));
        adp3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp3.setAdapter(adp3);
        String Temp = sp3.getSelectedItem().toString();
        if(Temp=="3")
            desired =3;
        else if(Temp=="4")
            desired = 4;
        else if (Temp == "5")
            desired = 5;


        //************************************
        //***********ADD button **************
        //************************************


        Button addButton = (Button) findViewById(R.id.add);
        addButton.setText("ADD");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;
                for(Pair<String ,Boolean> x: classes){
                    if(x.getString() ==selectedClass) {
                        check = true;
                        break;
                    }
                }
                if((!check)&&(selectedClass!="-Course-")&&classCount > 0&&(selectedClass!=null)) {
                    Toast.makeText(getApplicationContext(), "Adding " + selectedClass, Toast.LENGTH_SHORT).show();
                    Pair<String,Boolean> current_set = new Pair(selectedClass, important);
                    //checkbox
                    CheckBox priority = new CheckBox(getApplicationContext());
                    priority.setChecked(current_set.getBoolean());
                    priority.setText("Priority");
                    //text
                    TextView course = new TextView(getApplicationContext());
                    course.setText(selectedClass);
                    //table
                    final TableLayout ll= (TableLayout) findViewById(R.id.table);
                    //delete button
                    Button delete = new Button(getApplicationContext());
                    delete.setText("X");
                    //row
                    TableRow tr = new TableRow(getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    //layout
                    tr.setLayoutParams(lp);
                    //add to view
                    tr.addView(course);
                    tr.addView(priority);
                    tr.addView(delete);
                    ll.addView(tr);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final TableRow parent = (TableRow) v.getParent();
                            String name = parent.getVirtualChildAt(1).toString();
                            for(int a=0;a<classes.size();a++) {
                                if (classes.get(a).getString() == name) {
                                    classes.remove(a);
                                }
                            }
                            ll.removeView(parent);
                        }
                    });
                    classes.add(current_set);
                    cc++;
                }
            }
        });

        //**********************************
        //***********Optimize***************
        //**********************************
        Button optimize = (Button) findViewById(R.id.optimize);
        optimize.setText("OPTIMIZE");
        optimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desired>=cc)
                    //BUTTON DOES NOT WORK
                    Toast.makeText(getApplicationContext(),"ADD MORE COURSES",Toast.LENGTH_SHORT).show();
                //first calls addclass function for all the values in classes
                else {
                    Menu menu = new Menu();
                    for (int x=0; x<classes.size();x++) {
                        subjectIDRatings = classes.get(x).getString();
                        int num =0;
                        /*
                        for (int i = 0; i<subjectIDRatings.length(); x++){
                            char c = subjectIDRatings.charAt(i);
                            if (Character.isDigit(c) ) {
                                num = i;
                                i=100;
                            }
                        }

                        */
                        subjectIDRatings = subjectIDRatings.substring(0,num) + "rating.txt";
                        subjectIDRatings = subjectIDRatings.toLowerCase();
                        subjectIDRatings = subjectIDRatings.replaceAll(" ", "");

                        String all = "";
                        List<String> split;
                        String course = classes.get(x).getString();
                        String last = "";
                        String other = "";
                        String days = "";
                        String time = "";
                        String rating = "";
                        boolean prio = classes.get(x).getBoolean();
                        int p = 0;
                        if (prio) p = 1;

                        /*

                        // Read ratings
                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(
                                    new InputStreamReader(getAssets().open(subjectIDRatings)));

                            // do reading, usually loop until end of file reading
                            String line;
                            while ((line = reader.readLine()) != null) {

                                all = line;
                                split = Arrays.asList(all.split(","));
                                course = split.get(0);
                                if (course.toLowerCase().equals(course.toLowerCase() )) {
                                    last = split.get(1);
                                    other = split.get(2);
                                    days = split.get(3);
                                    time = split.get(4);
                                    rating = split.get(5);
                                    if (rating.contains("NS")) {
                                        rating = "2.0";
                                    }
                                    menu.addCourse(course, last, other, days, time, rating, p);
                                    if (p == 1) break;
                                }


                            }
                        } catch (IOException e) {
                            //log the exception
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    //log the exception
                                }
                            }
                        }


                        menu.optimize(desired);

                        */
                    }
                }

            }
        });

    }

}
