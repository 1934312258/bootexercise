package arithmetic;

import java.util.Arrays;

public class Maopao {

    int[] score = {1, 45, 23, 43, 89, 123};

    public int[] maopao1() {

        for (int i = 0; i < this.score.length; i++) {
            Boolean flag = true;
            for (int j = 0; j < score.length - 1; j++) {
                if (score[j] > score[j + 1]) {
                    int temp = score[j + 1];
                    score[j + 1] = score[j];
                    score[j] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(score));
        return score;

    }

    public static void main(String[] args) {
        Maopao maopao = new Maopao();
        maopao.maopao1();
    }
}
