import Challenges.BunnyMaze;

public class Test {
    public static void main(String[] args) {

        System.out.println(Challenges.LinearChess.solution("R__L","__RL")); // true
        System.out.println(Challenges.LinearChess.solution("_RL_","LR__")); // false
        System.out.println(Challenges.LinearChess.solution("_R","R_")); // false

    }
}