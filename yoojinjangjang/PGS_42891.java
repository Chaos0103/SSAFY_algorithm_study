package com.yoojin.pgs;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PGS_42891 {
	public static class Food {
		int order; // 음식 idx
		int time; // 먹는데 필요한 시간
		public Food(int order, int time) {
			super();
			this.order = order;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Food [order=" + order + ", time=" + time + "]";
		}
		
	}

    public static int solution(int[] food_times, long k) {
    	List<Food> foodList = new LinkedList<>();
    	
    	for(int i=0;i<food_times.length;i++) {
    		foodList.add(new Food(i+1, food_times[i])); // 리스트에 추가
    	}
    	
    	Collections.sort(foodList,Comparator.comparingInt(o->o.time));
    	
    	int foodCnt = foodList.size();
    	int i = 0;
    	int preTime = 0;
    	for(Food food: foodList) {
    		long diff = food.time - preTime;
    		if( diff != 0) {
    			long spend = diff * foodCnt;
    			if(spend <= k) {
    				k -= spend;
    				preTime = food.time;
    			} else {
    				k %= foodCnt;
    				foodList.subList(i,food_times.length).sort((Food a, Food b) -> {
    					return a.order - b.order;
    				});
    				return foodList.get(i+(int)k).order;
    			}
    		}
    		i++;
    		foodCnt--;
    	}
    	
		return -1;
    }
   
    public static void main(String[] args) {
		int answer = solution(new int[] {3,1,2},5);
		System.out.println(answer);
	}
}