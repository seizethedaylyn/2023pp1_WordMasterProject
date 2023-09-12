package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";

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

    public void updateItem(Object obj) {
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

    public void listAll(int level){

        int j = 0;
        System.out.println("\n--------------------------------");
        for(int i = 0; i < list.size(); i++){
            int wlevel = list.get(i).getLevel(); //list에 있는 단어 가져옴
            if( wlevel != level) continue; //키워드 검색해서 해당 안되면 출력 안함

            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------\n");
    }

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next(); //공백 허용 안함
        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();

        s.nextLine(); //엔터 저장

        System.out.print("정말로 삭제하시겠습니까? (Y/N): ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(id - 1)); //remove는 객체를 넣든지, paramete로 정수로 몇번째인지 넣어야함, integer를 정수로 바꿔줌
            System.out.println("\n단어가 삭제되었습니다!\n");
        } else
            System.out.println("\n취소되었습니다!\n");
    }

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));

            String line;
            int count = 0;

            while(true){
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0,level,word,meaning));
                count ++;
            }

            br.close();
            System.out.println(count + "개 로딩 완료!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));

            for(Word one : list){
                pr.write(one.toFileString()+"\n");
            }

            pr.close();
            System.out.println("데이터 파일 저장 완료!\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchLevel() {
        System.out.print("=> 레벨(1: 초급, 2: 중급, 3: 중급) 선택: ");
        int level = s.nextInt(); //공백 허용 안함
        this.listAll(level);
    }
}