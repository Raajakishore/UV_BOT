package com.example.uvbot;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
public class Main2Activity extends AppCompatActivity {
ImageView s1;
ImageView s2;
Button uvon;
Primr primr;
boolean st=false;
Button uvinit;
CountDownTimer count;
int f1=2,b1=2,r1=2,l1=2;
char dd=' ';
RadioButton radup;
WebView videoView;
String ip;
RadioButton radbelow;
char d;
String url="http://10.1.45.228:8080/";
RadioButton radright;
Button connect;
RadioButton radleft;
ImageView arrowup;
String data="s",prev="s";
int c=0;
int countt=0;
TextView timer;
ImageView arrowbelow;
ImageView arrowleft;
ImageView arrowright;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        s1 = findViewById(R.id.switch1);
        timer = findViewById(R.id.timer);
        s2 = findViewById(R.id.switch2);
        connect=findViewById(R.id.connect);
        radup= findViewById(R.id.radup);
        radbelow = findViewById(R.id.radbe);
        radleft = findViewById(R.id.radleft);
        radright = findViewById(R.id.radright);
        arrowbelow = findViewById(R.id.arrowbe);
        arrowleft= findViewById(R.id.arrowleft);
        arrowright=findViewById(R.id.arrowright);
        videoView   = findViewById(R.id.video);
        uvon = findViewById(R.id.uvon);
        uvinit = findViewById(R.id.uvinit);
        arrowup = findViewById(R.id.arrowup);
        radup.setEnabled(false);
        radbelow.setEnabled(false);
        radleft.setEnabled(false);
        radright.setEnabled(false);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connect.getText().toString().equals("Connect")){
                    Sockket sockket= new Sockket();
                    sockket.execute();
                }
                else{
                        if(primr!=null){
                            if(primr.isAlive()){
                                st=true;
                            }
                        }

                    else{
                        Toast.makeText(getApplicationContext(),"Bot is running",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
uvon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
          if(uvon.getText().toString().equals("UV ON")){

          s1.setImageResource(R.drawable.ic_power_settings_new_black_24dp);
          uvon.setText("UV OFF");
      }
      else{
          s1.setImageResource(R.drawable.ic_power_settings_new_red_24dp);
          uvon.setText("UV ON");
      }
    }
});
uvinit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(c==0){

            s2.setImageResource(R.drawable.ic_power_settings_new_black_24dp);
            c=1;
        }
        else{
            s2.setImageResource(R.drawable.ic_power_settings_new_red_24dp);
            c=0;

        }
    }
});
arrowup.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            if(event.getAction()==MotionEvent.ACTION_DOWN) {

                if(f1==0){
                    arrowup.setImageResource(R.drawable.ic_keyboard_arrow_up_red_24dp);
                    data="f";
                    soc();
            }}
            else if (event.getAction()==MotionEvent.ACTION_UP){
                if(data.equals("f")){
                data="s";
                soc();
                arrowup.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            }}
        }
        catch (Exception e){
        };
        return true;
    }
});

        arrowbelow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    if(event.getAction()==MotionEvent.ACTION_DOWN) {
                        if(b1==0){
                        arrowbelow.setImageResource(R.drawable.ic_keyboard_arrow_down_red_24dp);
                        data="b";
                        soc();
                    }}
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        if(data.equals("b")){
                        data="s";
                        soc();
                        arrowbelow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    }}
                }
                catch (Exception e){
                    Log.d("exceptionnnnn", "onTouch: " + e.toString());
                };
                return true;
            }
        });
        arrowleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {

                    if(event.getAction()==MotionEvent.ACTION_DOWN) {
                        if(l1==0){
                        arrowleft.setImageResource(R.drawable.ic_keyboard_arrow_left_red_24dp);
                        data="l";
                        soc();
                    }}
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        if(data.equals("l")){
                        data="s";
                        soc();
                        arrowleft.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp);
                    }}
                }
                catch (Exception e){
                };
                return true;
            }
        });
        arrowright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    if(event.getAction()==MotionEvent.ACTION_DOWN) {
                        if(r1==0){
                        arrowright.setImageResource(R.drawable.ic_keyboard_arrow_right_red_24dp);
                        data="r";
                            soc();
                    }}
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        if(data.equals("r")){
                        data="s";
                            soc();
                        arrowright.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                    }}
                }

                catch (Exception e){
                };
                return true;
            }
        });

    }
//    public class Prim extends  Thread{
//        Socket socket;
//        @Override
//        public void run() {
//          while (true){
//              if(data.equals("s") && st==true){
//                  break;
//              }
//            try {
//                ip = "10.1.45.228";
//                InetAddress inetAddress = InetAddress.getByName(ip);
//                socket = new Socket(inetAddress, 8134);
//                final InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
//                dd  = (char) (inputStreamReader.read());
//                inputStreamReader.close();
//                socket.close();
//            } catch (UnknownHostException ex) {
//                ex.printStackTrace();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
//                        st=true;
//                    }
//                });
//break;
//            }
//            catch (IOException ex) {
//                ex.printStackTrace();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
//                        st=true;
//                    }
//                });
//            break;}
//              }
//            super.run();
//
//        }
//    }
    void gg(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrowup.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                arrowbelow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                arrowright.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                arrowleft.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp);
                radup.setEnabled(false);radup.setChecked(false);
                radbelow.setEnabled(false);radbelow.setChecked(false);
                radleft.setEnabled(false);radright.setChecked(false);
                radright.setEnabled(false);radleft.setChecked(false);
                f1=2;b1=2;r1=2;l1=2;prev="s";
            }
        });
    }
    public class Primr extends  Thread{
        @Override
        public void run() {
            InetAddress inetAddress = null;
        while(true){

            try {
                if(data.equals("s") && st==true  ){
                    gg();
                    break;
                }
                else if(!data.equals("s") && st==true){
                    data="s";
                    soc();
                    gg();
                    break;
                }
                inetAddress = InetAddress.getByName("10.1.45.228");
                Socket socket = new Socket(inetAddress,8334);
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                d  = (char) (inputStreamReader.read());
                Log.d("Rombamukkiyam", "run: " + d);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if(d=='w'){
                            if(data.equals("f")){
                                data="s";
                                soc();
                                arrowup.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                            }
                            f1=1;
                            radup.setEnabled(true);
                            radup.setChecked(true);
                        }
                        else if(d=='l'){
                            f1=0;
                            radup.setEnabled(false);
                            radup.setChecked(false);
                        }

                        if(d=='x'){
                            if(data.equals("b")){
                                data="s";
                                soc();
                                arrowbelow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                            }
                            b1=1;
                            radbelow.setEnabled(true);
                            radbelow.setChecked(true);
                        }
                        else if(d=='m'){
                            b1=0;
                            radbelow.setEnabled(false);
                            radbelow.setChecked(false);
                        }

                        if(d=='y'){
                            if(data.equals("r")){
                                arrowright.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                                data="s";
                                soc();
                            }
                            r1=1;
                            radright.setEnabled(true);
                            radright.setChecked(true);
                        }
                        else if(d=='n'){
                            r1=0;
                            radright.setEnabled(false);
                            radright.setChecked(false);
                        }

                        if(d=='z'){
                            if(data.equals("l")){
                                arrowleft.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp);
                                data="s";
                                soc();
                            }
                            l1=1;
                            radleft.setEnabled(true);
                            radleft.setChecked(true);
                        }
                        else if(d=='o'){
                            l1=0;
                            radleft.setEnabled(false);
                            radleft.setChecked(false);
                        }
                    }
                });
                inputStreamReader.close();
                socket.close();
            } catch (UnknownHostException e) {
                Log.d("eroorrrrreee", "doInBackground: "+ e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("eroorrrrree", "doInBackground: "+ e.toString());
                e.printStackTrace();
            }
       }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                countdownstop();
                st=false;
                Toast.makeText(getApplicationContext(),"Disconnected",Toast.LENGTH_LONG).show();
                connect.setText("Connect");

            }
        });
            super.run();

        }
    }
    void countdownstart(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText("00:00");
                count=  new CountDownTimer(86400000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        countt++;
                        int minutes = countt/60;
                        int seconds = countt%60;
                        String min,sec;
                        if(minutes<10){
                            min=0+String.valueOf(minutes);
                        }
                        else{
                            min=String.valueOf(minutes);
                        }
                        if(seconds<10){
                            sec=0+String.valueOf(seconds);
                        }
                        else{
                            sec=String.valueOf(seconds);
                        }
                        String time=min+":"+sec;
                        timer.setText(time);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });

    }
    void countdownstop(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                countt=0;
                count.cancel();
            }
        });

    }
    void  soc(){
        Sockkett sockkett = new Sockkett();
        sockkett.execute();
    }
    public class Sockkett extends AsyncTask<Void,Void,Void>{
        Socket socket;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if(!data.equals("s")){
                    prev= data;
                    ip = "10.1.45.228";
                    InetAddress inetAddress = InetAddress.getByName(ip);
                    socket = new Socket(inetAddress, 8234);
                    final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeBytes(data);
                    dataOutputStream.close();
                    socket.close(); }
                else{

                    if(prev!="s"){
                        prev= "s";
                        ip = "10.1.45.228";
                        InetAddress inetAddress = InetAddress.getByName(ip);
                        socket = new Socket(inetAddress, 8234);
                        final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        dataOutputStream.writeBytes(data);
                        dataOutputStream.close();
                        socket.close();
                    }
                }
            }
            catch (IOException e) {
                Log.d("eroorrrrre", "doInBackground: "+ e.toString());
                e.printStackTrace();
            }



            return null;
        }
    }
    public class  Sockket extends AsyncTask<Void,Void,Void>{
        Socket socket;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                 ip = "10.1.45.228";
                InetAddress inetAddress = InetAddress.getByName(ip);
                socket = new Socket(inetAddress, 8134);
                final InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                dd  = (char) (inputStreamReader.read());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(dd=='c'){
                            primr =new Primr();
                            primr.start();
                            videoView.setWebViewClient(new WebViewClient());
                            videoView.getSettings().setJavaScriptEnabled(true);
                            videoView.loadUrl(url);
                            connect.setText("Disconnect");
                            countdownstart();
                        Toast.makeText(getApplicationContext(),"Connection Established",Toast.LENGTH_LONG).show();
                    }}
                });
                inputStreamReader.close();
                socket.close();
            } catch (UnknownHostException ex) {
                Log.d("eroorrrrr1", "doInBackground: "+ ex.toString());
                ex.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_LONG).show();
                    }
                });

            } catch (IOException ex) {
                Log.d("eroorrrrr2", "doInBackground: "+ ex.toString());
                ex.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_LONG).show();
                    }
                });

            }
            return null;
        }
        }
    }

