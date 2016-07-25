package dzumi.demo.app.demoapiservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dzumi.demo.app.demoapiservice.model.User;
import dzumi.demo.app.demoapiservice.model.response.BaseResponse;
import dzumi.demo.app.demoapiservice.network.APIServiceBuilder;
import dzumi.demo.app.demoapiservice.network.DemoAPIService;
import dzumi.demo.app.demoapiservice.network.NetworkRequest;
import dzumi.demo.app.demoapiservice.utils.CustomSharedPref;
import dzumi.demo.app.demoapiservice.utils.SharedPreferenceUtil;
import rx.Subscription;

//See more: https://github.com/sourcey/materiallogindemo
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    TextView tvSignUpLink;

    Subscription subscription;
    DemoAPIService demoAPIService;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sample_login2);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        tvSignUpLink = (TextView) findViewById(R.id.tvSignUpLink);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvSignUpLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
              /*  Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);*/
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

       /* if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);
*/
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        // TODO: Implement your own authentication logic here.
        demoAPIService = (DemoAPIService)
                APIServiceBuilder.buildAPIService(this, Constants.DOMAIN);

        User user = new User();
        user.setUserName(email);
        user.setPassword(password);

        subscription = NetworkRequest.
                performAsyncRequest(this,demoAPIService.login(user),
                        data->{
                            //luu token
                            if(data.getStatus() == BaseResponse.STATUS_SUCCESS){
                                CustomSharedPref.saveUser(this, data.getUser());
                            }
                            return data;},
                        next->{
                            progressDialog.dismiss();
                            if(next.getStatus() == BaseResponse.STATUS_SUCCESS){
                                //di tiep toi man hinh mainActivity
                                Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                //xuat thong bao
                                Snackbar.make(btnLogin, next.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });

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
//        moveTaskToBack(true);'
        super.onBackPressed();
    }

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("enter a valid email address");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        return valid;
    }
}
