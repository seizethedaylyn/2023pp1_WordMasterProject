package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addItem(){
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다.\n");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    public int updateItem(Object obj) {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next(); //공백 허용 안함
        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();

        s.nextLine(); //엔터 저장

        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        /*실제 수정할 객체의 id가져와야함 */
        Word word = list.get(idlist.get(id - 1));
        // 0번째 시작이니까 id-1
        word.setMeaning(meaning);
        //뜻 변경
        System.out.println("\n단어가 수정되었습니다!\n");
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {
    }

    public void listAll(){
        System.out.println("\n--------------------------------");

        for(int i = 0; i < list.size(); i++){
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------\n");
    }

    public ArrayList<Integer> listAll(String keyword){
        /*선택되어 출력되는걸 다시 list에 담기 위해 ArrayList로 구현,
        integer로 리턴타입하면, 원본 리스트의 아이디를 담게 됨*/

        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("\n--------------------------------");
        for(int i = 0; i < list.size(); i++){
            String word = list.get(i).getWord(); //list에 있는 단어 가져옴
            if(! word.contains(keyword)) continue; //키워드 검색해서 해당 안되면 출력 안함

            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());

            idlist.add(i);
            j++;
        }
        System.out.println("--------------------------------\n");
        return idlist;
    }

}