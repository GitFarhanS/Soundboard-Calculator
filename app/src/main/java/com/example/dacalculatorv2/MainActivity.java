package com.example.dacalculatorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.media.MediaPlayer;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonAdd, buttonSubtract, buttonEqual;
    MaterialButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    MaterialButton buttonAC, buttonDot;

    MediaPlayer clashRoyale;
    MediaPlayer deezNuts;
    MediaPlayer exportIDK;
    MediaPlayer goodBye;
    MediaPlayer manSnore;
    MediaPlayer pingMissing;
    MediaPlayer theWeekend;
    MediaPlayer tyler1;
    MediaPlayer vineBoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignID(buttonC, R.id.button_c);
        assignID(buttonBracketOpen, R.id.button_open_bracket);
        assignID(buttonBracketClose, R.id.button_close_bracket);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMultiply, R.id.button_multiply);
        assignID(buttonAdd, R.id.button_add);
        assignID(buttonSubtract, R.id.button_subtract);
        assignID(buttonEqual, R.id.button_equal);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(button0, R.id.button_0);
        assignID(buttonAC, R.id.button_AC);
        assignID(buttonDot, R.id.button_dot);

        clashRoyale = MediaPlayer.create(this, R.raw.clash_royale_hog_rider);
        deezNuts = MediaPlayer.create(this, R.raw.deez_nuts_sound_effect_free_download_hd);
        exportIDK = MediaPlayer.create(this, R.raw.export_idk);
        goodBye = MediaPlayer.create(this, R.raw.cartoon_hammer);
        manSnore = MediaPlayer.create(this, R.raw.man_snoring_meme);
        pingMissing = MediaPlayer.create(this, R.raw.ping_missing);
        theWeekend = MediaPlayer.create(this, R.raw.the_weeknd_rizzz);
        tyler1 = MediaPlayer.create(this, R.raw.tyler_fkin_1_machine_gun_1);
        vineBoom = MediaPlayer.create(this, R.raw.vine_boom);

    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;

        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        if(buttonText.equals("1")){
            clashRoyale.start();
        }
        if(buttonText.equals("2")){
            deezNuts.start();
        }
        if(buttonText.equals("3")){
            exportIDK.start();
        }
        if(buttonText.equals("4")){
            goodBye.start();
        }
        if(buttonText.equals("5")){
            manSnore.start();
        }
        if(buttonText.equals("6")){
            pingMissing.start();
        }
        if(buttonText.equals("7")){
            theWeekend.start();
        }
        if(buttonText.equals("8")){
            tyler1.start();
        }
        if(buttonText.equals("9")){
            vineBoom.start();
        }

        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript",1, null ).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}