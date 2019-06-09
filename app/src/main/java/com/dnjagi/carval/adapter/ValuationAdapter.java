package com.dnjagi.carval.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dnjagi.carval.CarValuationFragment;
import com.dnjagi.carval.Global.GlobalVarible;
import com.dnjagi.carval.Model.ValuationModel;
import com.dnjagi.carval.R;
import com.dnjagi.carval.data.UploadRecord;
import com.dnjagi.carval.utility.Utilities;

import java.util.ArrayList;

public class ValuationAdapter extends ArrayAdapter<UploadRecord> {
    private Activity activity;
    public ArrayList<UploadRecord> IValuationModel;
    private static LayoutInflater inflater = null;

    public ValuationAdapter(Activity activity, int textViewResourceId, ArrayList<UploadRecord> _IValuationModel) {
        super(activity, textViewResourceId, _IValuationModel);
        try {
            this.activity = activity;
            this.IValuationModel = _IValuationModel;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {

        }
    }

    public int getCount() {
        return IValuationModel.size();
    }

    public ValuationModel getItem(ValuationModel position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView modelTextView;
        public TextView makeTextView;
        public TextView firstNameTextView;
        public TextView middleNameTextView;
        public TextView lastNameTextView;
        public TextView statusTextView;
        public TextView regNoTextView;
        public TextView commentsTextView;
        public Button review_button;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ValuationAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                view = inflater.inflate( R.layout.valuationlist_item, null);
                holder = new ValuationAdapter.ViewHolder();
                holder.regNoTextView = (TextView) view.findViewById(R.id.regNoTextView);
                holder.statusTextView = (TextView) view.findViewById(R.id.statusTextView);
                holder.lastNameTextView = (TextView) view.findViewById(R.id.lastNameTextView);
                holder.middleNameTextView = (TextView) view.findViewById(R.id.middleNameTextView);
                holder.firstNameTextView = (TextView) view.findViewById(R.id.firstNameTextView);
                holder.makeTextView = (TextView) view.findViewById(R.id.makeTextView);
                holder.modelTextView = (TextView) view.findViewById(R.id.modelTextView);
                holder.commentsTextView = (TextView) view.findViewById(R.id.commentsTextView);
                holder.review_button = (Button) view.findViewById(R.id.review_button);
                view.setTag(holder);
            } else {
                holder = (ValuationAdapter.ViewHolder) view.getTag();
            }

            holder.regNoTextView.setText(IValuationModel.get(position).RegistrationNumber);
            holder.statusTextView.setText(IValuationModel.get(position).StatusString);
            holder.lastNameTextView.setText(IValuationModel.get(position).LastName);
            holder.middleNameTextView.setText(IValuationModel.get(position).MiddleName);
            holder.firstNameTextView.setText(IValuationModel.get(position).FirstName);
            holder.makeTextView.setText(IValuationModel.get(position).Make);
            holder.modelTextView.setText(IValuationModel.get(position).CarModel);
            holder.commentsTextView.setText(IValuationModel.get(position).Comments);
            //Todo: uncheck for rejected valuations
//            if(IValuationModel.get(position).StatusString =="Rejected")
//            {
//                holder.review_button.setVisibility(View.VISIBLE);
//            }
            holder.review_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalVarible.uploadRecord = IValuationModel.get(position);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new CarValuationFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, myFragment).addToBackStack(null).commit();

                }
            });

        } catch (Exception e) {
                       Utilities.LogException(new Exception("Error in Valuation Adapter.." + " " +  e.getMessage()));
        }
        return view;
    }

}