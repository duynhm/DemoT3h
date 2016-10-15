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
public class SignupFragment extends BaseFragment {
    private static final String TAG = "SignupActivity";

    EditText edtEmail;
    EditText edtName;
    EditText edtPassword;
    Button btnSignUp;
    TextView tvLoginLink;
    ImageView ivLogo;

    @Override
    public String getTagName() {
        return "SignupFragment";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_sample_signup, container, false);
        edtEmail = (EditText) root.findViewById(R.id.edtEmail);
        edtPassword = (EditText) root.findViewById(R.id.edtPassword);
        edtName = (EditText) root.findViewById(R.id.edtName);
        btnSignUp = (Button) root.findViewById(R.id.btnSignUp);
        tvLoginLink = (TextView) root.findViewById(R.id.tvLoginLink);
        tvLoginLink.setVisibility(View.GONE);

        ivLogo = (ImageView) root.findViewById(R.id.ivLogo);
        ivLogo.setVisibility(View.GONE);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


        return root;
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        btnSignUp.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        btnSignUp.setEnabled(true);
//        setResult(RESULT_OK, null);
        //luu thong tin dang ky
        //tao doi tuong sharedPReference de luu tru thong tin duoi dang KEY - VALUE
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", edtName.getText().toString());
        editor.putString("email", edtEmail.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.apply();

        //di vao man hinh main
        Intent intent = new Intent(getActivity(), PracticeMain.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();

        btnSignUp.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            edtName.setError("at least 3 characters");
            valid = false;
        } else {
            edtName.setError(null);
        }

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