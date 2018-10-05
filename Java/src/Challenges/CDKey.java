package Challenges;
/*  Build CD Key    */

class CDKey {
    public String solution(String S, int K) {
        StringBuilder ans = new StringBuilder();

        // calculates the size of the first group if they are not all equal sizes
        int dashCount = S.length() - S.replace("-", "").length();
        int firstGroup = (S.length() - dashCount) % K;

        // case 1: S is only one character and no dashes
        if (S.length() == 1) {
            ans.append(S.toUpperCase());
            return String.valueOf(ans);
        }

        // case 2: S is more than one character and contains dashes
        else {
            StringBuilder subStr = new StringBuilder();

            for (int i = 0; i < S.length(); i++) {
                String elem = String.valueOf(S.charAt(i));

                if (!elem.equals("-")) {
                    subStr.append(elem.toUpperCase());

                    if (ans.length() == 0 && firstGroup > 0) {
                        if (subStr.length() == firstGroup) {
                            ans.append(subStr);
                            ans.append("-");
                            subStr = new StringBuilder();
                        }
                    }

                    else if (subStr.length() == K) {
                        ans.append(subStr);
                        ans.append("-");
                        subStr = new StringBuilder();
                    }
                }
            }

            // removes the last dash
            ans.setLength(ans.length()-1);

            return String.valueOf(ans);
        }
    }
}