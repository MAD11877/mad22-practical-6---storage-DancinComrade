package sg.edu.np.mad.practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);



        Button grp1Btn = (Button) findViewById(R.id.grp1Button);
        grp1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.placeholderFrame, new grp1Fragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        });

        Button grp2Btn = (Button) findViewById(R.id.grp2Button);
        grp2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.placeholderFrame, new grp2Fragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        });
    }
}