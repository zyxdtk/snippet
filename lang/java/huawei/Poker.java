/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class Poker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		String[] deals = line.split("-");
		Cards firstDeal = new Cards(deals[0]);
		Cards secondDeal = new Cards(deals[1]);
		int result = firstDeal.compareTo(secondDeal);
		switch(result){
			case 0:
			case 1:
				firstDeal.cardsPrint();
				break;
			case -1:
				secondDeal.cardsPrint();
				break;
			default:
				System.out.println("ERROR");
		}
	}
}

class Cards{
	String[] allCard = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "joker", "JOKER"};
	int firstCard = -1;
	int cardClass = -1;//个子1、对子2、顺子<4>（连续5张）、三个3、炸弹<5>（四个）和对王6
	
	Cards(String cardStr){
		String[] cards = cardStr.split(" +");
		this.firstCard = getCardIndex(cards[0]);
		this.cardClass = getCardClass(cards);
	}
	
	int getCardIndex(String cardCode){
		for(int i=0; i<15; i++){
			if(allCard[i].equals(cardCode)){
				return i+3; //数值从3开始
			}
		}
		return -1;
	}
	
	int getCardClass(String[] cards){
		switch(cards.length){
			case 1:
				return 1;
			case 2:{
				if(cards[0].equals("joker") && cards[1].equals("JOKER")){
					return 6;
				}else{
					return 2;
				}
			}
			case 3:
				return 3;
			case 4:
				return 5;
			case 5:
				return 4;
			default:
				return -1;	
		}
	}
	
	int compareTo(Cards c){
		if(this.cardClass == c.cardClass){
			if(this.firstCard < c.firstCard){
				return -1;
			}else if(this.firstCard == c.firstCard){
				return 0;
			}else if(this.firstCard > c.firstCard){
				return 1;
			}
		}else{
			if(this.cardClass < 5 && this.cardClass < 5){
				return -2;
			}else{
				if(this.cardClass < c.cardClass){
					return -1;
				}else if(this.cardClass == c.cardClass){
					return 0;
				}else if(this.cardClass > c.cardClass){
					return 1;
				}
			}
		}
		return -2;
	}
	
	void cardsPrint(){
		String card = this.allCard[this.firstCard-3];
		switch(this.cardClass){
			case 1:{
				System.out.println(card);
				break;
			}
			case 2:{
				System.out.println(card+" "+card);
				break;
			}
			case 3:{
				System.out.println(card+" "+card+" "+card);
				break;
			}
			case 4:{
				for(int i=0; i<4; i++){
					System.out.print(this.allCard[this.firstCard++-3]);
					System.out.print(" ");
				}
				System.out.println(this.allCard[this.firstCard-3]);
				break;
			}
			case 5:{
				System.out.println(card+" "+card+" "+card+" "+card);
				break;
			}
			case 6:{
				System.out.println("joker JOKER");
				break;
			}
		}
	}
	
}