class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int size = 1; size <= s.length() / 2; size++) {
            StringBuilder builder = new StringBuilder();

            String prev = s.substring(0, size);
            int count = 1;
            int step;
            for (step = size; step + size <= s.length(); step += size) {
                String sub = s.substring(step, step + size);
                if (prev.equals(sub)) {
                    count++;
                } else {
                    if (count > 1) {
                        builder.append(count);
                    }
                    builder.append(prev);
                    prev = sub;
                    count = 1;
                }
            }
            //마지막 체킹
            if (count > 1) {
                builder.append(count);
            }
            builder.append(prev);
            if (step < s.length()) {
                builder.append(s.substring(step));
            }

            answer = Math.min(answer, builder.length());
        }

        return answer;
    }
}
