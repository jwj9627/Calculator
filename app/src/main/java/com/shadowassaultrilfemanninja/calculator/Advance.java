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


public class Advance extends ActionBarActivity implements View.OnClickListener {
    private Button sin, cos, tan, i, ln, log, pi, e, sq, power, open;

    private String display = "";
    private String left = "";
    private String right = "";
    private String op = "";
    private Double total;
    private Boolean delete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        EditText userInput = (EditText) findViewById(R.id.user_input);
        disableSoftInputFromAppearing(userInput);

        Intent intent = getIntent();
        display = intent.getStringExtra("display");
        left = intent.getStringExtra("left");
        right = intent.getStringExtra("right");
        op = intent.getStringExtra("op");
        total = intent.getDoubleExtra("total", 0.0);
        delete = intent.getBooleanExtra("delete", false);
//
        userInput.setText(display);
        createButtons();
    }

    public void createButtons() {
        sin = (Button) findViewById(R.id.sin);
        sin.setOnClickListener(this);
        cos = (Button) findViewById(R.id.cos);
        cos.setOnClickListener(this);
        tan = (Button) findViewById(R.id.tan);
        tan.setOnClickListener(this);

        i = (Button) findViewById(R.id.imag);
        i.setOnClickListener(this);
        ln = (Button) findViewById(R.id.ln);
        ln.setOnClickListener(this);
        log = (Button) findViewById(R.id.log);
        log.setOnClickListener(this);
        pi = (Button) findViewById(R.id.pi);
        pi.setOnClickListener(this);
        e = (Button) findViewById(R.id.e);
        e.setOnClickListener(this);
        sq = (Button) findViewById(R.id.sqrt);
        sq.setOnClickListener(this);
        power = (Button) findViewById(R.id.pow);
        power.setOnClickListener(this);
        open = (Button) findViewById(R.id.open);
        open.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sin:
                display = store();
                display = evaluate("sin");
                break;

            case R.id.cos:
                display = store();
                display = evaluate("cos");

            case R.id.tan:
                display = store();
                display = evaluate("tan");

            case R.id.imag:
                display = store();

            case R.id.ln:
                display = store();
                display = evaluate("ln");

            case R.id.log:
                display = store();
                display = evaluate("log");

            case R.id.pi:
                display = store();
                display = evaluate("pi");

            case R.id.e:
                display = store();
                display = evaluate("e");

            case R.id.per:
                display = store();
                if(!left.equals("") && !right.equals(""))
                    display = evaluate("per");

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

    private String evaluate(String operator){
        double leftNum;
        double rightNum;

        if(!left.equals("")){
            leftNum = Double.parseDouble(left);

            //total = Math.sin(leftNum);

            total = evaluateExpression(operator, leftNum);
        }else if(!right.equals("")){
            rightNum = Double.parseDouble(right);
            total = evaluateExpression(operator, rightNum);
        }

        this.left = total + "";
        this.right = "";
        this.op = "";
        return total + "";
    }

    private double evaluateExpression(String operator, String left, String right){
        double leftNum = Double.parseDouble(left);
        double rightNum = Double.parseDouble(right);

        if(operator.equals("per")){
            return (leftNum / rightNum) * 100;
        }

        return 0.0;
    }

    private double evaluateExpression(String operator, double num){
        if(operator.equals("sin")){
            return Math.sin(num);
        }else if(operator.equals("cos")){
            return Math.cos(num);
        }else if(operator.equals("tan")){
            return Math.tan(num);
        }else if(operator.equals("ln")){
            return Math.log(num)/Math.log(10);
        }else if(operator.equals("log")){
            return Math.log(num);
        }else if(operator.equals("pi")){
            return Math.PI;
        }else if(operator.equals("e")){
            return Math.exp(num);
        }

        return num;
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
        getMenuInflater().inflate(R.menu.advance_menu, menu);
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
        }else if(id == R.id.basic_settings){
            Intent intent = new Intent(this, MainActivity.class);

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
