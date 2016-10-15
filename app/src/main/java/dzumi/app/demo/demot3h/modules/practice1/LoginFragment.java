package dzumi.app.demo.demot3h.modules.practice1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.modules.app_components.activities.fragments.BaseFragment;

//See more: https://github.com/sourcey/materiallogindemo
public class LoginFragment extends BaseFragment {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    TextView tvSignUpLink;
    ImageView ivLogo;
    @Override
    public String getTagName() {
        return "LoginFragment";
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.layout_sample_login2, container, false);
        edtEmail = (EditText) root.findViewById(R.id.edtEmail);
        edtPassword = (EditText) root.findViewById(R.id.edtPassword);
        tvSignUpLink = (TextView) root.findViewById(R.id.tvSignUpLink);
        tvSignUpLink.setVisibility(View.GONE);
        ivLogo = (ImageView) root.findViewById(R.id.ivLogo);
        ivLogo.setVisibility(View.GONE);
        btnLogin = (Button) root.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        return root;
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }



    public void onLoginSuccess() {
        btnLogin.setEnabled(true);

        //luu thong tin login

        Intent intent = new Intent(getActivity(), PracticeMain.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();

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

        //check userid & pass
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        if(email.equals(sharedPreferences.getString("email","")) &&
                password.equals(sharedPreferences.getString("password", ""))){
            valid = true;
        }
        else valid = false;

        return valid;
    }
}
