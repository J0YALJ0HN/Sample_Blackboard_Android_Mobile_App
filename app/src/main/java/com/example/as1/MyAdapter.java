
package com.example.as1;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // globa variables
    ArrayList<String> arrayList;
    Context context;

    // Constructor to initialize values of global variables
    public MyAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Get the context of the parent
        Context context = parent.getContext();

        // Inflate the sample layout file
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.sample,parent,false);

        // Return a new instance of MyViewHolder class
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        // Set the text of the textView in the MyViewHolder object to the string at current position of the arraylist
        holder.textView.setText(arrayList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        // Return the size of the arraylist
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            // Call the super constructor and initialize the textView object
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_id);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    // Get the position of the clicked item
                    int position = getAdapterPosition();

                    // Create a new intent object to navigate to the DetailsActivity class
                    Intent intent = new Intent(context,DetailsActivity.class);

                    // Get the string at the current position in the arraylist
                    String value = arrayList.get(position);

                    // Add the string as an extra to the intent
                    intent.putExtra("key",value);

                    // Start DetailsActivity using intent
                    context.startActivity(intent);
                }
            });
        }
    }
}
