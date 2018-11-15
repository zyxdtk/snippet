/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * ID2035 MP3光标位置
 */
public class ID2035 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		String com = s.next();
		ID2035 id = new ID2035();
		MP3 mp3 = id.new MP3(num);
		mp3.parse(com);
		mp3.print();
	}
	class MP3{
		int[] playlist;
		int display;
		int cur;
		MP3(int num){		
			playlist = new int[num];
			int i = 0;
			while(i<num){
				playlist[i] = i+1;
				i++;
			}
			display = 0;
			cur = 0;
		}
		
		void parse(String com){
			int i=0;
			int len = com.length();
			while(i<len){
				switch(com.charAt(i)){
					case 'U':
						up();break;
					case 'D':
						down();break;
				}
				i++;
			}
		}
		void up(){
			if(cur == 0){//光标在第一首歌曲上时，按Up键光标挪到最后一首歌曲；
				cur = playlist.length -1;
			}else{
				cur--;
			}
			if(playlist.length > 4){
				if(cur < display){//屏幕显示的不是第一页时，光标在当前屏幕显示的第一首歌曲时，用户按Up键后，屏幕从当前歌曲的上一首开始显示，光标也挪到上一首歌曲。
					display--;
				}else if(cur > display + 3){//屏幕显示的是第一页（即显示第1 –4首）时，光标在第一首歌曲上，用户按Up键后，屏幕要显示最后一页（即显示第7-10首歌），同时光标放到最后一首歌上。
					display = cur - 3;
				}
			}
		}
		void down(){
			if(cur == playlist.length-1){//光标在最后一首歌曲时，按Down键光标挪到第一首歌曲。
				cur = 0;
			}else{
				cur++;
			}
			if(playlist.length>4){
				if(cur>display+3){
					display++;
				}else if(cur <display){
					display = 0;
				}
			}
		}
		void print(){
			int i=0;
			int len = (4<playlist.length)?3:playlist.length-1;
			while(i<len){
				System.out.print(playlist[display+i]+" ");
				i++;
			}
			System.out.println(playlist[display+i]);
			System.out.println(playlist[cur]);
		}
	}
}
