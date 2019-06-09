package com.dnjagi.carval.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dnjagi.carval.Model.ValuationModel;
import com.dnjagi.carval.R;
import com.dnjagi.carval.utility.Utilities;

import java.util.ArrayList;

public class ValuationAdapter extends ArrayAdapter<ValuationModel> {
    private Activity activity;
    public ArrayList<ValuationModel> IValuationModel;
    private static LayoutInflater inflater = null;

    public ValuationAdapter(Activity activity, int textViewResourceId, ArrayList<ValuationModel> _IValuationModel) {
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
        public TextView display_logDate;
        public TextView display_message;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ValuationAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate( R.layout.valuationlist_item, null);
                holder = new ValuationAdapter.ViewHolder();
                holder.display_message = (TextView) vi.findViewById(R.id.regNoTextView);

                vi.setTag(holder);
            } else {
                holder = (ValuationAdapter.ViewHolder) vi.getTag();
            }

            holder.display_message.setText(IValuationModel.get(position).RegistrationNumber);

        } catch (Exception e) {
                       Utilities.LogException(new Exception("Error in Valuation Adapter.." + " " +  e.getMessage()));
        }
        return vi;
    }

}