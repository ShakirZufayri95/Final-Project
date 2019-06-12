package com.rb26.ShakirOs;



import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    String userid, name, phone, location, latitude, longitude;
    Spinner sploc;
    TextView tvphone,tvlocation;
    EditText tvuserid, tvname, edoldpass, ednewpass;
    CircleImageView imgprofile;
    Button btnUpdate;
    ImageButton btnloc;
    Dialog myDialogMap;

    String slatitude, slongitude;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imgprofile = findViewById(R.id.imageView4);
        tvuserid = findViewById(R.id.txtemail);
        tvname = findViewById(R.id.txtUsername);
        tvphone = findViewById(R.id.txtphone);
        edoldpass = findViewById(R.id.txtoldpassword);
        ednewpass = findViewById(R.id.txtnewpassword);
        sploc = findViewById(R.id.spinLoc);
        btnUpdate = findViewById(R.id.button5);
        btnloc = findViewById(R.id.btnloc);
        tvlocation  = findViewById(R.id.tvloc);
        userid = bundle.getString("userid");//email
        name = bundle.getString("username");  //full name
        phone = bundle.getString("phone"); //phone

        tvphone.setText(phone);
        String image_url = "http://uumresearch.com/foodninja/profileimages/" + phone + ".jpg";
        Picasso.with(this).load(image_url)
                .resize(400, 400).into(imgprofile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadUserProfile();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newemail = tvuserid.getText().toString();
                String newname = tvname.getText().toString();
                String oldpass = edoldpass.getText().toString();
                String newpass = ednewpass.getText().toString();
                String newloc = sploc.getSelectedItem().toString();
                dialogUpdate(newemail, newname, newloc, oldpass, newpass);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userid", userid);
                bundle.putString("name", name);
                bundle.putString("phone", phone);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void loadUserProfile() {
        class LoadUserProfile extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("userid", phone);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://uumresearch.com/foodninja/php/load_user.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(ProfileActivity.this, s, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray restarray = jsonObject.getJSONArray("user");
                    JSONObject c = restarray.getJSONObject(0);
                    name = c.getString("name");
                    userid = c.getString("email");
                    location = c.getString("location");
                    latitude = c.getString("latitude");
                    longitude = c.getString("longitude");
                } catch (JSONException e) {

                }
                //Log.e("LOC",location);
                for (int i = 0; i < sploc.getCount(); i++) {
                    if (sploc.getItemAtPosition(i).toString().equalsIgnoreCase(location)) {
                        sploc.setSelection(i);
                    }
                }
                tvuserid.setText(userid);
                tvname.setText(name);
                tvlocation.setText("https://www.google.com/maps/@"+latitude+","+longitude+",15z");
            }
        }
        LoadUserProfile loadUserProfile = new LoadUserProfile();
        loadUserProfile.execute();
    }

    void updateProfile(final String newemail, final String newname, final String newloc, final String oldpass, final String newpass) {
        class UpdateProfile extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email", newemail);
                hashMap.put("name", newname);
                hashMap.put("phone", phone);
                hashMap.put("opassword", oldpass);
                hashMap.put("npassword", newpass);
                hashMap.put("newloc", newloc);
                hashMap.put("latitude", latitude);
                hashMap.put("longitude", longitude);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://uumresearch.com/foodninja/php/update_profile.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")) {
                    Toast.makeText(ProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userid", userid);
                    bundle.putString("name", name);
                    bundle.putString("phone", phone);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.execute();
    }






    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {

    }


    public void onProviderDisabled(String provider) {

    }


    private void dialogUpdate(final String newemail, final String newname, final String newloc, final String oldpass, final String newpass) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Profile");

        alertDialogBuilder
                .setMessage("Update this profile")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        updateProfile(newemail, newname, newloc, oldpass, newpass);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}