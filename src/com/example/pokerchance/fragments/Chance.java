package com.example.pokerchance.fragments;

import com.example.pokerchance.R;
import com.example.pokerchance.model.ChanceModel;
import com.example.pokerchance.model.ChanceModel.OutsChangeListener;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chance extends Fragment {
    
	private Context mContext;
	private Button dec;
	private Button inc;
	private TextView outs;
	private TextView ternChance;
	
	final ChanceModel chanceModel = new ChanceModel();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_chance, container, false);
		
		mContext = view.getContext();
		
		dec = (Button) view.findViewById(R.id.dec_btn);
		inc = (Button) view.findViewById(R.id.inc_btn);
		outs = (TextView) view.findViewById(R.id.outs_tv);
		
		ternChance =(TextView) view.findViewById(R.id.chance_tern_tv);
		
		dec.setOnClickListener(onDecClick);
		inc.setOnClickListener(onIncClick);
		
		chanceModel.setOutsChangeListener(outsChangeListener);
		
		
		return view;
	}
	
	
	OnClickListener onDecClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int number = Integer.parseInt(outs.getText().toString());
			number--;
			chanceModel.setOuts(number);
		}
	};
	
	OnClickListener onIncClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int number = Integer.parseInt(outs.getText().toString());
			number++;
			chanceModel.setOuts(number);
		}
	};
		
	OutsChangeListener outsChangeListener = new OutsChangeListener() {

		@Override
		public void onOutsChange(ChanceModel model) {

			outs.setText(model.getOuts());
			ternChance.setText(model.getTernChance());
		}
			
	
	};

}
