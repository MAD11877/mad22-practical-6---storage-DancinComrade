package sg.edu.np.mad.practical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    ArrayList<User> userModels;

    public RecyclerViewAdapter(ArrayList<User> userModels) {
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        // ViewType obtained from the getItemViewType() function
        if (viewType == 1) {
            view = inflater.inflate(R.layout.recycler_view_row_dynamic, parent, false);
        } else {
            view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        }

        return new RecyclerViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        // Check if the last character of the User object name is "7"
        if (userModels.get(position).Name.endsWith("7")) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Assign individual values to the name and description
        // Get specific user object from list based on its position in RecyclerView
        holder.nameView.setText("Name: " + userModels.get(position).Name);
        holder.desView.setText("Description: " + userModels.get(position).Description);

        // Set onClickListener to pic
        holder.picView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Profile");
                builder.setMessage("Name: " + userModels.get(holder.getAdapterPosition()).Name);
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        User userObj = userModels.get(holder.getAdapterPosition());

                        // Put multiple extras in one intent
                        Intent mainActivity = new Intent(view.getContext(), MainActivity.class);
                        int pos = userModels.indexOf(userObj);

                        mainActivity.putExtra("position",pos);
                        mainActivity.putParcelableArrayListExtra("currentUsers",userModels);
                        view.getContext().startActivity(mainActivity);
                    }
                });

                AlertDialog madnessAlert = builder.create();
                madnessAlert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // to know the number of items to display (from the list size)
        return userModels.size();
    }
}
