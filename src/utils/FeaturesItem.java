package utils;

import java.io.Serializable;

public class FeaturesItem implements Serializable {

	
	int x;
	int y;
	ItemType itemType;
	




	public FeaturesItem(int x, int y, ItemType itemType) {
		
		this.x = x;
		this.y = y;
		this.itemType = itemType;
	
	}
	
	
	
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public ItemType getItemType() {
		return itemType;
	}


	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}


	
	
}
