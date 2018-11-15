/**
 * 
 */
package huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lee
 *	2280	自动售货系统
 *	引用http://blog.csdn.net/ganzi1991/article/details/38350955
 *  华为 自动售货系统 测试用例 8 不能通过
 */
public class VendingSystem {

    public static VendingSystem vs = new VendingSystem();
    public static VendingSystem.Customer customer = vs.getCustomer();
    public static VendingSystem.GoodsAll goodsAll = vs.getGoodsAll();
    public static VendingSystem.MoneyBox moneyBox = vs.getMoneyBox();
  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String com = s.nextLine();
	   //Customer customer = new Customer();    
	   //GoodsAll goodsAll = new GoodsAll();    
	   //MoneyBox moneyBox = new MoneyBox();  
		vs.parse(com);//处理输入的命令
	}


	class Goods{//定义商品类
		public String name;
		public int price;
		public int count;
		public Goods(String name,int price){
			this.name = name;
			this.price = price;
			count = 0;
		}
		public String print(){
			return new String(name+" "+price+" "+count);
		}
	}//end Goods
	
	class GoodsAll{//定义货物柜
		public Goods A1 = new Goods("A1",2);    
		public Goods A2 = new Goods("A2",3);     
		public Goods A3 = new Goods("A3",4);
		public Goods A4 = new Goods("A4",5);
		public Goods A5 = new Goods("A5",8);    
		public Goods A6 = new Goods("A6",6);
		
		public GoodsAll(){
			
		}
		public  Goods getGoods(int i){
			switch(i){
				case 1:return A1;
				case 2:return A2;
				case 3:return A3;
				case 4:return A4;
				case 5:return A5;
				case 6:return A6;
				default:return null;
			}
		}
		public  Goods getGoods(String name){
			int index = Integer.parseInt(name.substring(1));
			return this.getGoods(index);
		}
		public void init(String com){
			String[] args = com.split("-");
			for(int i=0; i<args.length; i++){
				this.getGoods(i+1).count = Integer.parseInt(args[i]);
			}
		}
		public String isEmpty(){
			boolean isempty = true;
			for(int i=1; i<=6; i++){
				if(this.getGoods(i).count != 0){
					isempty = false;
				}
			}
			if(isempty){
				return "E005:All the goods sold out";
			}else{
				return "";
			}
		}
		public String print(){
			String result = "";
			for(int i=1; i<=6; i++){
				Goods goods = this.getGoods(i);
				result = result.concat(goods.name+" "+goods.price+" "+goods.count+"\n");
			}
			return result;
		}
	}//end GoodAll
	
	class Money{//定义钱
		int value;
		int count;
		public Money(int value){
			this.value = value;
			this.count = 0;
		}
	}//end Money
	
	class MoneyBox{//定义存钱盒
		public Money ten = new Money(10);
		public Money five = new Money(5);
		public Money two = new Money(2);
		public Money one = new Money(1);
		
		public  Money getMoney(int i){
			switch(i){
				case 4:return ten;
				case 3:return five;
				case 2:return two;
				case 1:return one;
				default:return null;
			}
		}
		public  Money getMoneyByValue(int i){
			switch(i){
				case 10:return ten;
				case 5:return five;
				case 2:return two;
				case 1:return one;
				default:return null;
			}
		}
		public void init(String com){
			String[] args = com.split("-");
			for(int i=0; i<args.length; i++){
				this.getMoney(i+1).count = Integer.parseInt(args[i]);
			}
		}
		public Money addMoney(int value){
			Money money = this.getMoneyByValue(value);
			switch(value){
				case 5:
				case 10:{
					if(this.one.count+this.two.count*2 < value){
						return null;
					}
				}
				default:{
					
				}
			}
			return money; 
		}
		public int[] exchange(int balance){
			int[] exchangeCount = new int[4];
			for(int i=0; i<4; i++){
				exchangeCount[i] = 0;
			}
			while(this.ten.count>0 && this.ten.value<= balance){//先找10元的
				this.ten.count--;
				exchangeCount[3]++;
				balance -= this.ten.value;
			}
			List<int[]> container = new ArrayList<int[]>();//然后用动态规划的方法，遍历所有可能组合
			for(int i=this.five.count; i>=0; i--){
				int curi = this.five.value*i;//cur 是当前组合的总金钱
				if(curi>balance)continue;//cur 找零要小于余额
				for(int j=this.two.count; j>=0; j--){
					int curj = curi + this.two.value*j;
					if(curj>balance)continue;
					for(int k=this.one.count; k>=0; k--){
						int curk = curj + this.one.value*k;
						if(curk>balance)continue;
						int[] cur = {k,j,i,curk};
						container.add(cur);
					}
				}
			}
			int max = -1, index=0;
			for(int i=0; i<container.size(); i++){
				if(max<container.get(i)[3]){
					max = container.get(i)[3];
					index = i;
				}
			}
			for(int i=0; i<3; i++){
				exchangeCount[i] = container.get(index)[i];
			}
			for(int i=0; i<4; i++){
				Money money = this.getMoney(i+1);
				money.count -= exchangeCount[i];
			}
			return exchangeCount;
		}
		public String print(){
			String result = "";
			for(int i=1; i<=4; i++){
				Money money = this.getMoney(i);
				result = result.concat(money.value+" yuan coin number="+money.count+"\n");
			}
			return result;
		}
	}//end MoneyBox
	
	class Customer{//定义顾客类
		int balance;//余额
		
		public int coinIn(Money money){//投币
			balance += money.value;
			return balance;
		}	
		public String coinIn(int value){
			if(this.balance > 10){
				return "E004:Pay the balance is beyond the scope biggest";
			}else{
				return "";
			}
		}
		public boolean buy(Goods goods){//购买商品
			if(balance >= goods.price){
				balance -= goods.price;
				return true;// 购买成功
			}else{
				return false;//余额不足
			}
		}
		
		public boolean refund(){
			if(balance == 0){
				return false;//余额为0，不能退币
			}else{
				return true;//可以退币
			}
		}
	}// end Customer
	
	//自动售卖系统的方法
	public void reset(String com){//系统初始化
		String[] args = com.split(" +");
		goodsAll.init(args[1]);
		moneyBox.init(args[2]);
		System.out.println("S001:Initialization is successful");
	}
	public void pullin(String com){
		String[] args = com.split(" +");
		int value = Integer.parseInt(args[1]);
		String result = "";
		Money money = null;
		switch(value){
			case 1:
			case 2:
			case 5:
			case 10:{
				money = moneyBox.addMoney(value);
				if(money == null){
					result = "E003:Change is not enough, pay fail";
				}else{
					result = customer.coinIn(value);
					if(result.equals("")){
						result = goodsAll.isEmpty();
					}
				}
				break;
			}
			default:
				result = "E002:Denomination error";
		}
		if(result.equals("")){
			money.count++;
			customer.balance += value;
			result = "S002:Pay success,balance="+value;
		}
		System.out.println(result);
	}
	public void buy(String com){
		String[] args = com.split(" +");
		String result = "";
		Goods goods = goodsAll.getGoods(args[1]);
		if(goods == null){
			result = "E006:Goods does not exist";
		}else if(goods.count == 0){
			result = "E007:The goods sold out";
		}else if(goods.price > customer.balance){
			result = "E008:Lack of balance";
		}else{
			customer.buy(goods);
			goods.count--;
			result = "S003:Buy success,balance="+customer.balance;
		}
		System.out.println(result);
	}
	public void change(String com){
		if(customer.balance == 0){
			System.out.println("E009:Work failure");
		}else if(customer.balance > 0){
			int[] count = moneyBox.exchange(customer.balance);
			customer.balance = 0;
			int[] value = {1,2,5,10};
			for(int i=0; i<4; i++){
				System.out.println(value[i]+" yuan coin number="+count[i]);
			}
		}
	}
	public void query(String com){
		String[] args = com.split(" +");
		int value = Integer.parseInt(args[1]);
		switch(value){
			case 0:
				System.out.println(goodsAll.print());
				break;
			case 1:
				System.out.print(moneyBox.print());
				break;
			default:
				System.out.print("E010:Parameter error");
				break;
		}
	}
	
	public Customer getCustomer(){//新建顾客
		return new Customer();
	}
	public GoodsAll getGoodsAll(){//新建储物柜
		return new GoodsAll();
	}
	public MoneyBox getMoneyBox(){//新建存钱柜
		return new MoneyBox();
	}
	//命令解析
	public void parse(String com){//解析一行命令
		String[] args = com.split(";");
		for(int i=0; i<args.length; i++){
			switch(args[i].charAt(0)){
				case 'r':
					reset(args[i]);//r，初始化商品和存钱盒信息
					break;
				case 'p':
					pullin(args[i]);//p，向自动售货机投入某个面额的钱币
					break;
				case 'b':
					buy(args[i]);//b，购买商品
					break;
				case 'c':
					change(args[i]);//c，可退出所有投币余额，投币余额清零。
					break;
				case 'q':
					query(args[i]);//q，查询自动售货机中所有商品存量信息及存钱盒信息
					break;
				default:
					break;
			}
		}
	}
}
