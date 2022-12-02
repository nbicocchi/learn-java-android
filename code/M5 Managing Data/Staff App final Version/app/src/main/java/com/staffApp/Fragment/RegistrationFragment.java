package com.staffApp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.staffApp.R;

import com.staffApp.Models.User;
import com.staffApp.RecyclerView.RVActivity;
import com.staffApp.Watchers.AgeWatcher;
import com.staffApp.Watchers.NameWatcher;


public class RegistrationFragment extends Fragment  {

    Context context;
    ImageButton doneBtn;
    FirebaseAuth firebaseAuth;

    EditText editName,editAge,editEmail,editPassword;
    AgeWatcher ageWatcher;
    NameWatcher nameWatcher;
    TextView loginBackBtn;




    public RegistrationFragment(Context context) {
        this.context=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doneBtn=view.findViewById(R.id.registration_btn_done);
        editName=view.findViewById(R.id.registration_edit_username);
        editAge=view.findViewById(R.id.registration_edit_age);
        editPassword=view.findViewById(R.id.registration_edit_password);
        loginBackBtn=view.findViewById(R.id.registration_back_to_login);
        loginBackBtn.setOnClickListener(v->{ backToLogin();});
        ageWatcher=new AgeWatcher(context);
        nameWatcher=new NameWatcher(context,editName);
        editEmail=view.findViewById(R.id.registration_edit_email);
        doneBtn.setOnClickListener(v->{checkRegistrationUser();});

    }

    private void backToLogin() {
        LoginFragment loginFragment=new LoginFragment(context);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,loginFragment).addToBackStack("Registration").commit();
    }

    public void checkRegistrationUser() {

        String username=editName.getText().toString().trim();
        String age=editAge.getText().toString().trim();
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        if(username.isEmpty())
        {
            editName.setError("Name is Required");
            editName.requestFocus();
            return;
        }
        if(age.isEmpty())
        {
            editAge.setError("Age is Required");
            editAge.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Email  is Required");
            editEmail.requestFocus();
            return;
        } else {

        if (password.length()<6) {
                editPassword.setError("Password lenght should be 6 characters at least!");
                editPassword.requestFocus();
                return;
            }
        }

        addUser(username,age,email,password);
        return;



    }

    private boolean checkIfUserExists(String email) {

        return true;
    }

    private void addUser(String username, String age,String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        User user=new User(username,age,email);
                        //Add/Create Users node
                        FirebaseDatabase.getInstance().getReference("Users")
                                //retrive id from authentication in order to save it inside the Real-time database
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(context,"User Add succesfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(context, RVActivity.class));
                                        }
                                        else
                                        {
                                            Toast.makeText(context,"Registration failed, Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }
}