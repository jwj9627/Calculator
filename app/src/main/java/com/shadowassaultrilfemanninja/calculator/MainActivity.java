package com.shadowassaultrilfemanninja.calculator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Stack;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, del, per, equ, div, add, mult, sub;

    private String display = "";
    private String left = "";
    private String right = "";
    private String op = "";
    private Double total;
    private Boolean delete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userInput = (EditText) findViewById(R.id.user_input);
        disableSoftInputFromAppearing(userInput);



        if( getIntent().getExtras() != null)
        {
            Intent intent = getIntent();
            display = intent.getStringExtra("display");
            left = intent.getStringExtra("left");
            right = intent.getStringExtra("right");
            op = intent.getStringExtra("op");
            total = intent.getDoubleExtra("total", 0.0);
            delete = intent.getBooleanExtra("delete", false);

            userInput.setText(display);
        }


        createButtons();

    }

    public void createButtons() {
        n0 = (Button) findViewById(R.id.num0);
        n0.setOnClickListener(this);
        n1 = (Button) findViewById(R.id.num1);
        n1.setOnClickListener(this);
        n2 = (Button) findViewById(R.id.num2);
        n2.setOnClickListener(this);
        n3 = (Button) findViewById(R.id.num3);
        n3.setOnClickListener(this);
        n4 = (Button) findViewById(R.id.num4);
        n4.setOnClickListener(this);
        n5 = (Button) findViewById(R.id.num5);
        n5.setOnClickListener(this);
        n6 = (Button) findViewById(R.id.num6);
        n6.setOnClickListener(this);
        n7 = (Button) findViewById(R.id.num7);
        n7.setOnClickListener(this);
        n8 = (Button) findViewById(R.id.num8);
        n8.setOnClickListener(this);
        n9 = (Button) findViewById(R.id.num9);
        n9.setOnClickListener(this);

        add = (Button) findViewById(R.id.plus);
        add.setOnClickListener(this);
        sub = (Button) findViewById(R.id.sub);
        sub.setOnClickListener(this);
        mult = (Button) findViewById(R.id.mult);
        mult.setOnClickListener(this);
        div = (Button) findViewById(R.id.div);
        div.setOnClickListener(this);

        per = (Button) findViewById(R.id.per);
        per.setOnClickListener(this);

        del = (Button) findViewById(R.id.del);
        del.setOnClickListener(this);

        equ = (Button) findViewById(R.id.equ);
        equ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.num0:
                if(display != "")
                    display += "0";
                break;

            case R.id.num1:
                display += "1";
                break;

            case R.id.num2:
                display += "2";
                break;

            case R.id.num3:
                display += "3";
                break;

            case R.id.num4:
                display += "4";
                break;

            case R.id.num5:
                display += "5";
                break;

            case R.id.num6:
                display += "6";
                break;

            case R.id.num7:
                display += "7";
                break;

            case R.id.num8:
                display += "8";
                break;

            case R.id.num9:
                display += "9";
                break;

            case R.id.plus:
                display = store();
                op = "+";
                break;

            case R.id.sub:
                display = store();
                op = "-";
                break;

            case R.id.mult:
                display = store();
                op = "*";
                break;

            case R.id.div:
                display = store();
                op = "/";
                break;

            case R.id.per:
                display += ".";
                break;

            case R.id.del:
                display = "";

                break;

            case R.id.equ:
                display = store();
                left = evaluate();
                display = left;
                break;

            default:
                break;
        }


        EditText userInput = (EditText) findViewById(R.id.user_input);
        userInput.setText(display);
    }

    public String store(){
        if(left == ""){
            this.left = display;
        }else{
            this.right = display;
        }

        return "";
    }

    private String evaluate(){
        double leftNum = Double.parseDouble(left);
        double rightNum = Double.parseDouble(right);

        if(op == "+"){
            total = leftNum + rightNum;
        }else if(op == "-"){
            total = leftNum - rightNum;
        }else if(op == "*"){
            total = leftNum * rightNum;
        }else if(op == "/"){
            total = leftNum / rightNum;
        }

        this.right = "";
        this.op = "";
        return total + "";
    }

    /**
     * Disable soft keyboard from appearing, use in conjunction with android:windowSoftInputMode="stateAlwaysHidden|adjustNothing"
     *
     * @param editText
     */
    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.advance_settings){
            Intent intent = new Intent(this, Advance.class);

            intent.putExtra("display", display);
            intent.putExtra("left", left);
            intent.putExtra("right", right);
            intent.putExtra("op", op);
            intent.putExtra("total", total);
            intent.putExtra("delete", false);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
