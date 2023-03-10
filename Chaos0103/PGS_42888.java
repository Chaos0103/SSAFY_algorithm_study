import java.util.*;

class Solution {

    //key: id, value: nickname
    public Map<String, String> repository = new HashMap<>();
    public ArrayList<String> result = new ArrayList<>();
    
    public String[] solution(String[] record) {
        for (String cmd : record) {
            String[] data = cmd.split(" ");
            if (!data[0].equals("Leave")) {
                repository.put(data[1], data[2]);
            }
        }

        for (String cmd : record) {
            String[] data = cmd.split(" ");
            if (data[0].equals("Enter")) {
                result.add(repository.get(data[1]) + "님이 들어왔습니다.");
            } else if (data[0].equals("Leave")) {
                result.add(repository.get(data[1]) + "님이 나갔습니다.");
            }
        }
        
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
