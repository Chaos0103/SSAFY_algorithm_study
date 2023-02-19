class Solution {

    public String dfs(String str) {

        if (str.isEmpty()) {
            return "";
        }

        int count = 0;
        String u = "", v = "";

        for (int i = 0; i < str.length(); i++) {
            count += str.charAt(i) == '(' ? 1 : -1;
            if (count == 0) {
                u = str.substring(0, i + 1);
                v = str.substring(i + 1);
                break;
            }
        }

        boolean flag = false;
        count = 0;
        for (int i = 0; i < u.length(); i++) {
            count += str.charAt(i) == '(' ? 1 : -1;
            if (count < 0) {
                flag = true;
                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (flag) {
            stringBuilder.append('(').append(dfs(v)).append(')');
            u = u.substring(1, u.length() - 1);
            for (int i = 0; i < u.length(); i++) {
                char ch = u.charAt(i) == '(' ? ')' : '(';
                stringBuilder.append(ch);
            }
            return String.valueOf(stringBuilder);
        } else {
            return u + dfs(v);
        }
    }

    public String solution(String p) {
        return dfs(p);
    }
}
