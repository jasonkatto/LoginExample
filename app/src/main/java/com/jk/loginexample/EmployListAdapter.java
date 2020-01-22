package com.jk.loginexample;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployListAdapter extends BaseAdapter {

    ArrayList<EmployModel> arrayList;
    Context context;

    public EmployListAdapter(ArrayList<EmployModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view= View.inflate(context,R.layout.listrow, null);
        MyViewHolder myViewHolder= new MyViewHolder();
        myViewHolder.ID=view.findViewById(R.id.id);
        myViewHolder.name=view.findViewById(R.id.name);
        myViewHolder.phoneNumber=view.findViewById(R.id.phoneNumber);
        myViewHolder.emailAddress=view.findViewById(R.id.emailAddress);
        myViewHolder.imageView=view.findViewById(R.id.imageView);
        myViewHolder.edit=view.findViewById(R.id.edit);
        myViewHolder.delete=view.findViewById(R.id.delete);
        view.setTag(myViewHolder);
        myViewHolder.ID.setText(arrayList.get(position).getID());
        myViewHolder.name.setText(arrayList.get(position).getName());
        myViewHolder.phoneNumber.setText(arrayList.get(position).getPhoneNumber());
        myViewHolder.emailAddress.setText(arrayList.get(position).getEmailAddress());
        myViewHolder.imageView.setImageResource(arrayList.get(position).getImage());

        myViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, MainActivity.class).putExtra("Edit", arrayList.get(position).getID()));
            }
        });

        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySqliteOpenHelper  mySqliteOpenHelper= new MySqliteOpenHelper(context, " " , null, 1);
                mySqliteOpenHelper.deleteEmployee(Integer.parseInt(arrayList.get(position).getID()));
                context.startActivity(new Intent(context, EmployList.class));
            }
        });

        return view;

    }

    public  class MyViewHolder {
        TextView ID, name, phoneNumber, emailAddress;
        ImageView imageView;
        Button edit, delete;

    }

}
