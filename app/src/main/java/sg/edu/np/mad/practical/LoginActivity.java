package sg.edu.np.mad.practical;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        // Database Integration (manual input of URL as  the DB is located in a SEA server)
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-practical-26919-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference databaseReference = database.getReference("Users");

        databaseReference.child("mad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Post newPost = dataSnapshot.getValue(Post.class);
                // Retrieve user details and store as global variables
                username = newPost.username;
                password = newPost.password;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        Button loginButton = (Button) findViewById(R.id.loginButton);
        EditText usernameEdit = (EditText) findViewById(R.id.usernameInputView);
        EditText passwordEdit = (EditText) findViewById(R.id.passwordInputView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = usernameEdit.getText().toString();
                String passwordInput = passwordEdit.getText().toString();

                // If one input or none is entered, will prompt user to enter both via a toast msg
                if (usernameInput.matches("") || passwordInput.matches("") ) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter an input", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Compare input username/password with the one retrieved from the DB
                    if (usernameInput.equals(username) && passwordInput.equals(password)) {
                        Intent ListActivity = new Intent(LoginActivity.this,
                                ListActivity.class);
                        startActivity(ListActivity);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect " +
                                "Password/Username, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}