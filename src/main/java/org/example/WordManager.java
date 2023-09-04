package org.example;

import java.util.Scanner;

public class WordManager {
    Scanner s = new Scanner(System.in);
    public void selectMenu(){
        System.out.print("*** 영단어 마스터 ***\n" +
                "******************\n" +
                "1. 모든 단어 보기\n" +
                "2. 수준별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "******************\n" +
                "=> 원하는 메뉴는? ");
    }

    public void start(){
        selectMenu();
    }
}
