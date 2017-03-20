package com.intelligentz.skoolvan.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intelligentz.skoolvan.R;
import com.intelligentz.skoolvan.constants.Tags;
import com.intelligentz.skoolvan.constants.URL;
import com.intelligentz.skoolvan.parser.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private ProgressDialog pDialog;
    private String username;
    private String password;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);
        username = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        new AttemptLogin().execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
//        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _emailText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }

        //|| password.length() < 4 || password.length() > 10

        if (password.isEmpty() ) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
            pDialog.setMessage("Authenticating...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest(
                        URL.LOGIN_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(Tags.TAG_SUCCESS);
                JSONObject user = json.getJSONObject("user");
                String id = user.getString("id");
                String name = user.getString("name");
                String username = user.getString("username");
                String phone = user.getString("phone");

                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    Intent i = new Intent(LoginActivity.this, SearchActivity.class);
                    i.putExtra("id",id);
                    i.putExtra("name", name);
                    i.putExtra("username", username);
                    i.putExtra("phone", phone);
                    SharedPreferences prefs = getSharedPreferences(
                            "skoolvan.username", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("id",id);
                    editor.putString("name", name);
                    editor.putString("username", username);
                    editor.putString("phone", phone);
                    editor.commit();
                    startActivity(i);
                    onLoginSuccess();
                    return json.getString(Tags.TAG_MESSAGE);
                }else{

                    Log.d("Login Failure!", json.getString(Tags.TAG_MESSAGE));
                    onLoginFailed();
                    //Toast.makeText(Login.this, "Invalid login details", Toast.LENGTH_LONG).show();
                    return json.getString(Tags.TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }
}
