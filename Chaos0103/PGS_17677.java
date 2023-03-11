import java.util.*;

class Solution {

    public List<String> transfer(String str) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String part = str.substring(i, i + 2).toUpperCase();
            //숫자나 특수문자가 포함된 경우
            if (!isAlpha(part.charAt(0)) || !isAlpha(part.charAt(1))) {
                continue;
            }
            result.add(part);
        }
        return result;
    }

    public boolean isAlpha(char ch) {
        if ('A' <= ch && ch <= 'Z') {
            return true;
        }
        return false;
    }

    public int solution(String str1, String str2) {
        List<String> transferStr1 = transfer(str1);
        List<String> transferStr2 = transfer(str2);

        List<String> intersection = new ArrayList<>(transferStr1);
        List<String> union = new ArrayList<>(transferStr1);
        intersection.retainAll(transferStr2);
        union.addAll(transferStr2);

        HashSet<String> set1 = new HashSet<>(intersection);
        HashSet<String> set2 = new HashSet<>(union);

        int size1 = set1.size();
        int size2 = set2.size();
        if (size1 == 0 && size2 == 0) {
            return 65536;
        }

        for (String str : set1) {
            long count1 = transferStr1.stream()
                    .filter(t -> t.equals(str))
                    .count();
            long count2 = transferStr2.stream()
                    .filter(t -> t.equals(str))
                    .count();
            size1 += (Math.min(count1, count2) - 1);
        }

        for (String str : set2) {
            long count1 = transferStr1.stream()
                    .filter(t -> t.equals(str))
                    .count();
            long count2 = transferStr2.stream()
                    .filter(t -> t.equals(str))
                    .count();
            size2 += (Math.max(count1, count2) - 1);
        }

        return (int) (((float) size1 / size2) * 65536);
    }
}
