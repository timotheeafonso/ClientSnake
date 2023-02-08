package model;

import java.io.Serializable;
import java.util.ArrayList;

import utils.ItemType;
import utils.Position;

public class Item extends Agent implements Serializable{
	private  ItemType itemType;
	public Item(Position position, ItemType itemType) {
		this.position=new ArrayList<Position>();
		this.position.add(position);
		this.itemType = itemType;
	}
	
	public String toString() {
		return position.toString()+" : "+itemType;
	}

	public ItemType getItemType() {
		return itemType;
	}


	
}
