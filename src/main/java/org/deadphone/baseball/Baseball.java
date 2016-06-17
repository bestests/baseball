package org.deadphone.baseball;

import java.util.Scanner;

public class Baseball {
	// 렌덤 숫자 뽑기
	public int[] pickRandomNum() {
		int[] pickRanNums = new int[3];
		int numCount = 0;
		
		a:
		while(numCount <= 2) {
			int num = (int)(Math.random() * 10);
			if(numCount == 0 && num ==0) continue;
			if(numCount != 0) {
				for(int i = 0 ; i < pickRanNums.length ; i++) {
					if(num == pickRanNums[i]) continue a;
				}
			}
			pickRanNums[numCount++] = num;
		}
		return pickRanNums;
	}
	
	// 숫자 입력 받기
	public int[] insertNum() {
		Scanner sc = new Scanner(System.in);
		int[] numbers = new int[3];
		boolean isNum = false;
		boolean isEnd = false;
		
		while(true) {
			System.out.print("숫자를 입력해주세요. (예: 123): ");
			String nums = sc.nextLine();
			
			if(nums.length() == 3) {
				for(int i = 0 ; i < nums.length(); i++) {
					if(nums.charAt(i) > 0 || nums.charAt(i) < 9) isNum = true;
				}
				if(isNum) {
					for(int j = 0 ; j < nums.length() ; j++) {
						numbers[j] = (int)(nums.charAt(j) - 48);
					}
					isEnd = true;
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}
			} else {
				System.out.println("숫자를 3자리만 입력해주세요.");
			}
			if(isEnd) break;
		}
		
		return numbers;
	}
	
	// 두 숫자 비교 후 결과
	public String judgeGame(int[] ranNum, int[] pickNum) {
		String result = "";
		int strikeCount = 0;
		int ballCount = 0;
		int outCount = 0;
		boolean isOut = true;
		
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				if(ranNum[i] == pickNum[j]) {
					if(i == j) strikeCount++;
					else ballCount++;
					isOut = false;
				}
			}
			if(isOut) outCount++;
			isOut = true;
		}
		
		result = strikeCount + "스트라이크, " + ballCount + "볼";
		
		if(outCount == 3) result = "3아웃 입니다.";
		else if(outCount != 0) {
			result += ", " + outCount + "아웃입니다.";
		}
		if(strikeCount == 3) result = "정답입니다!!!";
		
		return result;
	}
	
	// 게임 시작
	public void startGame() {
		Baseball bb = new Baseball();
		int tryCount = 1;
		int[] pickNums = bb.pickRandomNum();
		
		while(tryCount <= 10) {
			System.out.println("===================================");
			System.out.println(tryCount + "번째 시도입니다.");
			
			String result = bb.judgeGame(pickNums, bb.insertNum());
			
			System.out.println("-----------------------------------");
			System.out.println(result);
			
			if(result.equals("정답입니다!!!")) {
				System.out.println(tryCount + "회 시도하셨습니다.");
				
				break;
			}
			tryCount++;
		}
		if(tryCount == 11) {
			System.out.println("10회 모두 시도하셨습니다.");
			String answer = "";
			for(int i = 0 ; i < pickNums.length ; i++) {
				answer += pickNums[i];
			}
			System.out.println("정답은 '" + answer + "'입니다.");
		}
		System.out.println("===================================");
	}
	
	// 게임 컨트롤
	public void runGame() {
		boolean isFirst = true;
		String msg = "";
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			if(isFirst == true) {
				msg = "야구게임을 시작하시겠습니까? (Y or N) : ";
				isFirst = false;
			} else {
				msg = "계속 하시겠습니까? (Y or N) : ";
			}
			System.out.print(msg);
			
			switch(sc.nextLine()) {
				case "Y":
				case "y":
					System.out.println("야구 게임 시작!!!");
					new Baseball().startGame();
					break;
				case "N":
				case "n":
					System.out.println("야구 게임 종료합니다!!!");
					System.exit(0);
					break;
				default:
					System.out.println("'Y'나 'N'만 입력해주세요.");
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		new Baseball().runGame();
	}
}