package sg.edu.np.mad.practical;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView picView;
    ImageView imageView2;
    TextView nameView, desView;
    View viewItem;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        viewItem = itemView;
        picView = itemView.findViewById(R.id.userPicView);
        imageView2 = itemView.findViewById(R.id.imageView2);
        nameView = itemView.findViewById(R.id.userNameView);
        desView = itemView.findViewById(R.id.userDesView);

    }
}
