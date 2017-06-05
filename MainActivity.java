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
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Spinner sp1,sp2,sp3;
    CheckBox priority;
    Boolean important = false;
    String selectedClass;
    ArrayList<Pair<String,Boolean>> classes;//important, when finalized is pressed, this is the arra
                                            //y of classes and priorities
    ArrayAdapter<String> adp1,adp2,adp3;
    ArrayList<String> l1,l2;
    //double spinner to choose class
    int pos;
    int cc;
    int desired = 3;//this is the value of classes student wants to take
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //DROP DOWN BOXES



        l1=new ArrayList<String>();
        classes = new ArrayList<Pair<String,Boolean>>();
        l1.add("-Subject-");
        /*while(reader.hasNext())) {
            l1.add(reader.nextLine());
        }*/
        l1.add("ANTH");
        l1.add("CMPSC");
        l1.add("COMM");
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
                // TODO Auto-generated method stub

                switch(pos)
                {
                    case 0:
                        l2= new ArrayList<String>();
                        /*while(reader.hasNext())) {
                        l1.add(reader.nextLine());
                        }*/
                        l2.add("-Course-");

                        adp2=new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        break;
                    case 1:
                        l2= new ArrayList<String>();
                        /*while(reader.hasNext())) {
                        l1.add(reader.nextLine());
                        }*/
                        l2.add("ANTH3");
                        l2.add("ANTH5");
                        l2.add("ANTH9");
                        l2.add("ANTH103");

                        adp2=new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 2:
                        l2= new ArrayList<String>();
                        l2.add("CMPSC8");
                        l2.add("CMPSC16");
                        l2.add("CMPSC24");
                        l2.add("CMPSC32");

                        adp2=new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 3:
                        l2= new ArrayList<String>();
                        l2.add("COMM1");
                        l2.add("COMM87");
                        l2.add("COMM88");
                        l2.add("COMM89");

                        adp2=new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
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
        else if(Temp=="3")
            desired = 4;
        else
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
                if((!check)&&(selectedClass!="-Course-")&&(selectedClass!=null)) {
                    Toast.makeText(getApplicationContext(), "Adding " + selectedClass, Toast.LENGTH_SHORT).show();
                    Pair<String,Boolean> current_set = new Pair(selectedClass, important);
                    //checkbox
                    CheckBox priority = new CheckBox(getApplicationContext());
                    priority.setChecked(current_set.getBoolean());
                    priority.setText("Priority");
                    //text
                    TextView course = new TextView(getApplicationContext());
                    course.setText(selectedClass);
                    //delete button
                    Button delete = new Button(getApplicationContext());
                    delete.setText("X");
                    //table
                    TableLayout ll= (TableLayout) findViewById(R.id.table);
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

                //thenruns optimize functions
            }
        });

    }

}
