package com.rb26.ShakirOs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Spinner sploc;
    EditText edEmail,edPass,edPhone,edName;
    Button btnReg;
    TextView tvlogin;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserInput();
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void registerUserInput() {
        String email,pass,phone,name,location;
        email = edEmail.getText().toString();
        pass = edPass.getText().toString();
        phone = edPhone.getText().toString();
        name = edName.getText().toString();
        location = sploc.getSelectedItem().toString();
        //error checking here
        user = new User(name,phone,email,pass,location);
        //insertData(user);
        registerUserDialog();
    }

    private void insertData() {
        class RegisterUser extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this,
                        "Registration","...",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("email",user.email);
                hashMap.put("password",user.password);
                hashMap.put("phone",user.phone);
                hashMap.put("name",user.name);
                hashMap.put("location",user.location);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest
                        ("https://s-z-o.000webhostapp.com/shakiros/insert_registration.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    RegisterActivity.this.finish();
                    startActivity(intent);

                }else if (s.equalsIgnoreCase("nodata")){
                    Toast.makeText(RegisterActivity.this, "Please fill in data first", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }

            }
        }

        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }

    private void registerUserDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(this.getResources().getString(R.string.registerfor)+" "+user.name+"?");

        alertDialogBuilder
                .setMessage(this.getResources().getString(R.string.registerdialognew))
                .setCancelable(false)
                .setPositiveButton(this.getResources().getString(R.string.yesbutton),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //Toast.makeText(getActivity(), "DELETE "+jobid, Toast.LENGTH_SHORT).show();
                        insertData();
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.registrationprocess), Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(this.getResources().getString(R.string.nobutton),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void initView() {
        sploc = findViewById(R.id.spinLoc);
        edEmail = findViewById(R.id.txtEmail);
        edPass = findViewById(R.id.txtpassword);
        edPhone =findViewById(R.id.txtphone);
        edName = findViewById(R.id.txtname);
        btnReg = findViewById(R.id.btn_register);
        tvlogin = findViewById(R.id.tvregister);


    }
}
