package com.prueba.calculadora_taller_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.pantalla).equals(display.getText().toString())){

                    display.setText("");

                }
            }
        });
    }

    private void updateText(String stringtoADD){
        String oldText=display.getText().toString();
        int cursorPosition=display.getSelectionStart();
        String leftString= oldText.substring(0,cursorPosition);
        String rightString=oldText.substring(cursorPosition);
        if(getString(R.string.pantalla).equals(display.getText().toString())){
            display.setText(stringtoADD);
            display.setSelection(cursorPosition + 1);
        }
        else{
            display.setText(String.format("%s%s%s", leftString,stringtoADD,rightString));
            display.setSelection(cursorPosition + 1);
        }
    }

    public void btnCero(View view){
        updateText("0");
    }

    public void btnUno(View view){
        updateText("1");
    }

    public void btnDos(View view){
        updateText("2");
    }
    public void btnTres(View view){
        updateText("3");
    }
    public void btnCuatro(View view){
        updateText("4");
    }
    public void btnCinco(View view){
        updateText("5");
    }
    public void btnSeis(View view){
        updateText("6");
    }
    public void btnSiete(View view){
        updateText("7");
    }
    public void btnOcho(View view){
        updateText("8");
    }
    public void btnNueve(View view){
        updateText("9");
    }

    public void btnMas(View view){
        updateText("+");
    }
    public void btnMenos(View view){
        updateText("-");
    }
    public void btnDividir(View view){
        updateText("÷");
    }
    public void btnmasMenos(View view){
        updateText("+/-");
    }
    public void btnClear(View view){
        display.setText("");
    }
    public void btnMultiplicar(View view){
        updateText("×");
    }
    public void btnPunto(View view){
        updateText(".");
    }
    public void btnIgual(View view){
        String userExpression=display.getText().toString();
        userExpression=userExpression.replaceAll("÷","/");
        userExpression=userExpression.replaceAll("×","*");

        Expression expression=new Expression(userExpression);
        String result=String.valueOf(expression.calculate());
        display.setText(result);
        display.setSelection(result.length());

    }
    public void btnExponente(View view){
        updateText("^");
    }
    public void btnParentesis(View view){
        int cursorPosition=display.getSelectionStart();
        int openParentesis=0;
        int closeParentesis=0;
        int textLen=display.getText().length();

        for (int i=0; i<cursorPosition;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openParentesis+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closeParentesis+=1;
            }
            //(8+9)x1

        }
        if (openParentesis==closeParentesis || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }else if (closeParentesis<openParentesis && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");

        }
        display.setSelection(cursorPosition + 1);
    }
    public void btnBackSpace(View view){
        int cursorPositon=display.getSelectionStart();
        int lenText=display.getText().length();

        if (cursorPositon != 0 && lenText!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPositon-1,cursorPositon,"");
            display.setText(selection);
            display.setSelection(cursorPositon-1);
        }

    }
}