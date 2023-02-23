package com.yoojin.boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_21608 {
	
	public static class Student implements Comparable<Student>{ // 학생 배열 
		int num; // 학생 번호
		int x; // 학생의 x좌표
		int y; // 학생의 y좌표 
		
		public Student(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Student [num=" + num + ", x=" + x + ", y=" + y + "]";
		}


		@Override
		public int compareTo(Student o) {
			return Integer.compare(this.num, o.num);		
		}
	}
	
	public static class Point {
		int x; 
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static int N; 
	static int[][] desks;
	static List<Student> students = new ArrayList<>(); // 학생 배열 
	static ArrayList<Integer>[] likes; // 학생의 번호(행), 학생이 좋아하는 학생들의 번호 (열)
	static boolean[] sitYn;
	static int[][] locs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int[] studentOrder;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		desks = new int[N][N]; // 학생들의 책상 배열
		studentOrder = new int[N*N];
		likes = new ArrayList[N*N];
		sitYn = new boolean[N*N];
		for(int i =0;i<N*N;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int student = Integer.parseInt(st.nextToken());
			int like1 = Integer.parseInt(st.nextToken());
			int like2 = Integer.parseInt(st.nextToken());
			int like3 = Integer.parseInt(st.nextToken());
			int like4 = Integer.parseInt(st.nextToken());
			likes[student-1] = new ArrayList<>();
			likes[student-1].add(like1-1); // 학생 번호 index와 맞추기 위해 모두 1 감소
			likes[student-1].add(like2-1); // 학생 번호 index와 맞추기 위해 모두 1 감소
			likes[student-1].add(like3-1); // 학생 번호 index와 맞추기 위해 모두 1 감소
			likes[student-1].add(like4-1); // 학생 번호 index와 맞추기 위해 모두 1 감소
			studentOrder[i] = student-1;
			students.add(new Student(student-1,-1,-1));
		}
		
		Collections.sort(students);
		
		ArrayList<Student> friends;
		
		for(int i =0;i<N*N;i++) {
			// 현재 책상에 앉힐 학생 가져오기 
			int currentStudent = studentOrder[i];
			friends = new ArrayList<>();
			for(int j =0;j<4;j++) { // 학생의 친한 친구를 한명씩 보면서 -> 배정이 된 학생인 경우 친구 배열에 넣기 
				if(sitYn[likes[currentStudent].get(j)]) {
					// 배정이 된 학생인 경우
					friends.add(students.get(likes[currentStudent].get(j))); 
				}
			}
			
			Map<Point,Integer> friendsNearPoints = new HashMap<>();
			
			for(Student friend: friends) { // 배정이된 친구 배열을 돌면서 
				// 사방을 보면서 
				for(int[] loc: locs) {
					int nx = friend.x + loc[0];
					int ny = friend.y + loc[1];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if(desks[nx][ny] != 0) continue;
					
					// 범위안에 있고, 차지되지 않았다면, 해당 좌표를 친구인접좌표 맵에 넣기 
					friendsNearPoints.put(new Point(nx,ny), friendsNearPoints.getOrDefault(new Point(nx,ny), 0)+1);
				}
			}
			// value값으로 정렬
			List<Point> keySet = new ArrayList<>(friendsNearPoints.keySet());
			keySet.sort(new Comparator<Point>() {

				@Override
				public int compare(Point o1, Point o2) { // value로 내림차순 정렬 (가장 큰값이 가장 앞에)
					return friendsNearPoints.get(o2).compareTo(friendsNearPoints.get(o1)); 
				}
			});
			
			// 가장 큰 value를 꺼내고 -> 해당 value를 가진 키를 배열에 저장하기 
			ArrayList<Point> maxFriendsNearPoints = new ArrayList<>();
			int maxValue = 0;
			if(friendsNearPoints.size() != 0) {
				maxValue = friendsNearPoints.get(keySet.get(0));
				
				for(Point key:keySet) {
					if(friendsNearPoints.get(key) == maxValue) {
						maxFriendsNearPoints.add(key); 
					}
				}
			}

			
			if(maxFriendsNearPoints.size() == 1) {
				// 1개인 경우 (하나)
				Point here = maxFriendsNearPoints.get(0);
				desks[here.x][here.y] = currentStudent;
				sitYn[currentStudent] = true; 
				students.get(currentStudent).x = here.x;
				students.get(currentStudent).y = here.y;
				continue;
			}

			
			Map<Point, Integer> nearBlanks = new HashMap<>(); // 각 좌표별 인접한 빈칸의 개수를 센 맵 
			List<Point> allPoints = new ArrayList<>();
			List<Point> all = new ArrayList<>();
			if(maxFriendsNearPoints.size() == 0) {
				// 0개인 경우 모든 칸을 다봐 
				// 0개가아니면 해당 인접만봐? 그게 뭐야 그런설명없잖아 
				// 여러개이거나 하나도 없는 경우 -> 인접한 칸의 개수를 세서 map 에 저장하기
				for(int a = 0;a<N;a++) {
					for(int b = 0;b<N;b++) {
						
						if(desks[a][b] != 0) continue;
						// 자리가 차지 되지 않은 모든 빈 좌표들을 배열에 담기
						all.add(new Point(a,b));
						int n = 0;
						for(int[] loc:locs) {
							int nx = a+loc[0];
							int ny = b+loc[1];
							if(nx<0||nx>=N||ny<0||ny>=N) continue;
							if(desks[nx][ny] != 0) continue;
							n++;
						}
						
						if(n==0) continue;
						nearBlanks.put(new Point(a,b), n);
					}
				}
			}else {
				// 0개가 아니면 (인접 친구가 있으면 해당 인접 친구 들의 near blanks를 계산)
				for(Point point:maxFriendsNearPoints) {
					int n = 0;
					for(int[] loc:locs) {
						int nx = point.x + loc[0];
						int ny = point.y + loc[1];
						if(nx<0||nx>=N||ny<0||ny>=N) continue;
						if(desks[nx][ny] != 0) continue;
						n++;
					}
					if(n==0) continue;
					nearBlanks.put(new Point(point.x,point.y), n);
				}
			}
			
			// value값으로 정렬
			keySet = new ArrayList<>(nearBlanks.keySet());
			keySet.sort(new Comparator<Point>() {

				@Override
				public int compare(Point o1, Point o2) { // value로 내림차순 정렬 (가장 큰값이 가장 앞에)
					return nearBlanks.get(o2).compareTo(nearBlanks.get(o1)); 
				}
			});
			// 가장 큰 value를 찾기 
			ArrayList<Point> maxNearBlanks = new ArrayList<>(); // 인접 빈칸 좌표의 수가 큰 좌표들의 배열
			if(nearBlanks.size() != 0) {
				maxValue = nearBlanks.get(keySet.get(0));
				
				// 해당 value를 가진 좌표들을 모음 
				for(Point key:keySet) {
					if(nearBlanks.get(key) == maxValue) {
						maxNearBlanks.add(key);
						allPoints.add(key);
					}
				}
			}else {
				allPoints = maxFriendsNearPoints;
			}
			
			
			if(maxNearBlanks.size() == 1) {
				// 한개인 경우 
				Point here = maxNearBlanks.get(0);
				desks[here.x][here.y] = currentStudent;
				sitYn[currentStudent] = true; 
				
				students.get(currentStudent).x = here.x;
				students.get(currentStudent).y = here.y;
				continue;
			}
			if(allPoints.size() != 0) {
				// 여러개인 경우 
				allPoints.sort(new Comparator<Point>() {

					@Override
					public int compare(Point o1, Point o2) {
						if(o1.x != o2.x) { 
							return Integer.compare(o1.x,o2.x);
						}
						return Integer.compare(o1.y, o2.y);
					}
				});
				
				Point here = allPoints.get(0);
				desks[here.x][here.y] = currentStudent;
				sitYn[currentStudent] = true;
				students.get(currentStudent).x = here.x;
				students.get(currentStudent).y = here.y;
				continue;
			}
			
			
			all.sort(new Comparator<Point>() {

				@Override
				public int compare(Point o1, Point o2) {
					if(o1.x != o2.x) { 
						return Integer.compare(o1.x,o2.x);
					}
					return Integer.compare(o1.y, o2.y);
				}
			});
			
			Point here = all.get(0);
			desks[here.x][here.y] = currentStudent;
			sitYn[currentStudent] = true;
			students.get(currentStudent).x = here.x;
			students.get(currentStudent).y = here.y;
			continue;
			
			
		}
		
		
		for(int i=0;i<N;i++) {
			for(int j =0;j<N;j++) {
				System.out.print(desks[i][j]+1+" ");
			}
			System.out.println();
		}
		
		int sum = 0;
		for(int i =0;i<N;i++) {
			for(int j =0;j<N;j++) {
				int n =0;
				int studentNum = desks[i][j]; // 현재 학생 
				for(int[] loc: locs) {
					int nx = students.get(studentNum).x + loc[0];
					int ny = students.get(studentNum).y + loc[1];
					if(nx < 0 || nx>=N||ny<0||ny>=N) continue;
					if (likes[studentNum].contains(desks[nx][ny])) {
						n++;
					}
				}
				if(n==0) {
					continue;
				}
				sum += Math.pow(10, n-1);
			}
		}
		System.out.println(sum);
		
	}
}