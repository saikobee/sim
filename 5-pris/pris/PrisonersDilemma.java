package pris;

/**
 * PrisonersDilemma.java   --  created on Jan 24, 2012, 9:11:40 AM
 * a Singleton!
 * @author levenick
 */
public class PrisonersDilemma {

    private static int cdReward = 0;

    static void playARound(Strategy s1, Strategy s2) {
        s1.setPreviousOpponentMove(true);
        s2.setPreviousOpponentMove(true);
        for (int i = 0; i < Params.rounds; i++) {
            getPlays(s1, s2);
            pay(s1, s2);
            tellThem(s1, s2);
        }

//        if (Params.BROKEN) {
//            spew(s1, s2);
//        }
    }
    static boolean s1Play, s2Play;

    static void getPlays(Strategy s1, Strategy s2) {
        s1Play = s1.cooperate();
        s2Play = s2.cooperate();
    }

    static void pay(Strategy s1, Strategy s2) {
        if (s1Play && s2Play) {
            pay(s1, s2, Params.ccPayoff, Params.ccPayoff);
        } else if (!s1Play && !s2Play) {
            pay(s1, s2, Params.ddPayoff, Params.ddPayoff);
        } else if (s1Play && !s2Play) {
            pay(s1, s2, cdReward, Params.dcPayoff);
        } else {
            pay(s1, s2, Params.dcPayoff, cdReward);
        }
    }

    static void pay(Strategy s1, Strategy s2, int s1Pay, int s2Pay) {
        s1.setScore(s1.getScore() + s1Pay);
        s2.setScore(s2.getScore() + s2Pay);
    }

    static void tellThem(Strategy s1, Strategy s2) {
        s2.setPreviousOpponentMove(s1Play);
        s1.setPreviousOpponentMove(s2Play);
    }

    static void spew(Strategy s1, Strategy s2) {
        System.out.println("...s1, then s2" + s1.toString() + s2.toString());
    }
}
