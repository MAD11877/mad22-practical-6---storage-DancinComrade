package sg.edu.np.mad.practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    ArrayList<User> userModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler dbhandler = new DBHandler(this);

        userModels = dbhandler.getUsers();

        // If no data in database, generate new data
        if (userModels.size() == 0) {
            // Generate 20 user objects with randomized name, description and boolean Follow.
            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                User userObject = new User();
                userObject.Name = Integer.toString(random.nextInt(999999999));
                userObject.Description = Integer.toString(random.nextInt(999999999));
                userObject.Followed = random.nextBoolean();

                // Id is a running number starting from 1
                userObject.Id = i + 1;

                userModels.add(userObject);
                dbhandler.insertUser(userObject);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.ListRecyclerView);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(userModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}