package com.example.mycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    Boolean mResult = false;
    EditText editText;
    TextView mTextView;
    Double operandFirst = null;
    Double operandSecond = null;
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        mTextView = findViewById(R.id.textView);
    }

    public void onNumberClick(View view) {
        if (mResult) {
            onClearClick(view);
        }
        mButton = (Button)view;
        editText.append(mButton.getText());
    }

    public void onCorrectClick(View view) {
        if (editText.length() > 0) {
            StringBuffer string = new StringBuffer(editText.getText().toString());
            string.deleteCharAt(string.length() - 1);
            editText.setText(string);
            if (editText.getText().toString().equals("0"))
                editText.setText("");
        }
    }

    public void onClearClick(View view) {
        editText.setText("");
        mTextView.setText("");
        operandFirst = null;
        operandSecond = null;
        operation = "";
        mResult = false;
    }

    public void onNullClick(View view) {
        if (editText.length()>0 && !mResult)
            onNumberClick(view);
    }

    public void onDotClick(View view) {
        String string = editText.getText().toString();
        if (editText.length()==0)
            editText.append("0");
        if (string.indexOf('.') == -1 )
            onNumberClick(view);
    }

    public void onOperationClick(View view) {
        if (operandFirst == null && editText.length() >0) {
            mButton = (Button) view;
            operation = mButton.getText().toString();
            //String string;
            //string = editText.getText().toString();
            operandFirst = Double.valueOf(editText.getText().toString());
            mTextView.append(operandFirst.toString());
            mTextView.append(operation);
            editText.setText("");
        }
    }

    public void onEquallyClick(View view) {
        if (mTextView.length() != 0 && editText.length() !=0 && !mResult){
            operandSecond = Double.valueOf(editText.getText().toString());
            mTextView.append(operandSecond.toString());
            Double result;
            switch (operation){
                case "/":
                    if (operandSecond == 0)
                        operandSecond = 1.0;
                    divityOfNull(view);
                    result = operandFirst / operandSecond;
                    break;
                case "*":
                    result = operandFirst * operandSecond;
                    break;
                case "-":
                    result = operandFirst - operandSecond;
                    break;
                case "+":
                    result = operandFirst + operandSecond;
                    break;
                    default:
                        result= -1.0;
                        break;
            }
            editText.setText(result.toString());
            mResult = true;
        }
    }

    private void divityOfNull(View view) {
        Toast toast = Toast.makeText(this, "Вы поделили на ноль!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
