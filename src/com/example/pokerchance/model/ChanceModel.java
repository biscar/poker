package com.example.pokerchance.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.pokerchance.data.Chance;

public class ChanceModel {
	
	private Chance chance = new Chance();
	
	public interface OutsChangeListener{
		void onOutsChange(ChanceModel chanceModel);
	}
	
	private OutsChangeListener outsChangeListener;
	
	public void setChance(Chance chance){
		this.chance = chance;
	}

	public void setOutsChangeListener(OutsChangeListener listener){
		outsChangeListener = listener;
	}
	
	private void notifyListener(){
		if(outsChangeListener!=null){
			outsChangeListener.onOutsChange(this);
		}
	}
	
	public void setOuts(int outs){
		this.chance.setOuts(outs);
		this.chance.setTernChance(calculate_tern_chance(outs));
		
		notifyListener();
	}
	
	public String getOuts(){
		return String.valueOf(this.chance.getOuts());
	}

	public float calculate_tern_chance(int outs){
		return new BigDecimal(((47 - outs)/ outs)).setScale(2,  RoundingMode.UP).floatValue();
		
	}
	
	public String getTernChance(){
		return this.chance.getTernChance() + " : 1";
	}
	
	
	
}
