package com.example.pokerchance.model;

import com.example.pokerchance.Util;
import com.example.pokerchance.data.Chance;

public class ChanceModel {
	
	private Chance chance = new Chance();
	
	public interface OutsChangeListener{
		void onOutsChange(ChanceModel chanceModel);
	}
	
	public interface BankChangeListener{
		void onBankChange(ChanceModel chanceModel);
	}
	
	public interface BetChangeListener{
		void onBetChange(ChanceModel chanceModel);
	}
	
	private OutsChangeListener outsChangeListener;
	private BankChangeListener bankChangeListener;
	private BetChangeListener betChangeListener;
	
	
	public void setChance(Chance chance){
		this.chance = chance;
	}

	public void setOutsChangeListener(OutsChangeListener listener){
		outsChangeListener = listener;
	}
	
	public void setBankChangeListener(BankChangeListener listener){
		bankChangeListener = listener;
	}
	
	public void setBetChangeListener(BetChangeListener listener){
		betChangeListener = listener;
	}
	
	private void notifyListener(){
		if(outsChangeListener != null){
			outsChangeListener.onOutsChange(this);
		}
	}
	
	private void notifyBankListener(){
		if(bankChangeListener != null){
			bankChangeListener.onBankChange(this);
		}
	}
	
	private void notifyBetListener(){
		if(betChangeListener != null){
			betChangeListener.onBetChange(this);
		}
	}
	
	
	public void setOuts(float outs){
		this.chance.outs = outs;
		/*this.chance.ternChance = calculate_tern_chance(outs);
		this.chance.riverChance =  calculate_river_chance(outs);	
		this.chance.ternAndRiverChance = calculate_tr_chance(outs);

		this.chance.ternPercent = calculate_tern_percent(outs);
		this.chance.riverPercent = calculate_river_percent(outs);
		this.chance.ternRiverPercent = calculate_tr_percent(outs);
		*/
		notifyListener();
	}
	
	public void setBank(float bank){	
		this.chance.bank = bank;	
		notifyBankListener();
		notifyBetListener();
	}
	
	public void setBet(float bet){	
		this.chance.bet = bet;	
		notifyBetListener();
		notifyBankListener();
	}
	
	public String getOuts(){
		return  Util.formatUnit(this.chance.outs);
	}

	public float calculate_tern_chance(float outs){
		return  (47 - outs) / outs;	
	}
	
	public float calculate_river_chance(float outs){
		return  (46 - outs) / outs;	
	}
	
	public float calculate_tr_chance(float outs){
		return (100 / ((1 - ((47 - outs) / 47) * ((46 - outs) / 46)) * 100)) - 1;
	}
	
	public float calculate_tern_percent(float outs){
		return  outs / 47 *100	;	
	}
	
	public float calculate_river_percent(float outs){
		return   (outs / 46) * 100	;
	}
	
	public float calculate_tr_percent(float outs){
		return  (1 - ((47 - outs) / 47) * ((46 - outs) / 46)) * 100;
	}
	
	public float calculateRatio(float bank, float bet){
		float result = bank/bet;
	
		return  (Float.isInfinite(result) || Float.isNaN(result)) ? 0 : result;
	}
	
	public float calculateBreakevenPercent(float bank, float bet){
		float result = 0;
		if (bank > 0){
		   result = bet/(bank+bet) * 100;
		}
		
		return (Float.isInfinite(result) || Float.isNaN(result) || result == 0 ) ? 0 : result;
	}
	
	public String getTernChance(){
		return Util.formatChance( calculate_tern_chance(this.chance.outs) );
	}
	
	public String getRiverChance(){
		return Util.formatChance(calculate_river_chance(this.chance.outs));
	}
	
	public String getTRChance(){
		return Util.formatChance(calculate_tr_chance(this.chance.outs));
	}
	
	public String getTernPercent(){
		return Util.formatPercent(calculate_tern_percent(this.chance.outs));
	}
	
	public String getRiverPercent(){
		return Util.formatPercent(calculate_river_percent(this.chance.outs));
	}
	
	public String getTRPercent(){
		return Util.formatPercent(calculate_tr_percent(this.chance.outs));
	}
	
	public String getRatio(){
		float f = calculateRatio(this.chance.bank, this.chance.bet);
		
		return Util.formatChance(f);
	}
	
	public String getBreakevenPercent(){
		float f = calculateBreakevenPercent(this.chance.bank, this.chance.bet);
		
		return Util.formatPercent(f);
	}
	
	public void resetValue(){
		notifyBetListener();
		notifyBankListener();	
	}
	
}
