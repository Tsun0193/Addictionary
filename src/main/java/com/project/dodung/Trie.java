package com.project.dodung;

import java.util.ArrayList;

public class Trie {
    public Trie() {
        trie = new node[10000000];
        trie[0] = new node();
        curNode = new ArrayList<Integer>();
    }

    public int getValidIntChar(char x) {
        if( ((int)x < (int)'a' || (int)x > (int)'z') && ((int)x != 45 ) && ((int)x != 32 )) return -1;
        int nextChar = (x - 'a');
        if((int)x == 45) {
            nextChar = 26;
        }
        if((int)x == 32) {
            nextChar = 27;
        }
        return nextChar;
    }

    /** Thêm một từ mới vào cây Trie. */
    public void addWord(String word,int wordId) {

        int pos = 0;
        int nextChar = 0;
        for(int i = 0; i < word.length(); ++i) {
            nextChar = getValidIntChar(word.charAt(i));
            if(nextChar < 0) continue;
            if(trie[pos].getNext()[nextChar] == 0) {
                ++this.curMax;
                trie[curMax] = new node();
                trie[pos].setNext(nextChar, this.curMax);
            }
            pos = trie[pos].getNext()[nextChar];
        }
        if (trie[pos].getId() == 0) {
            trie[pos].setId(wordId);
        }
    }

    /** Di chuyển vị trí hiện tại khi từ ta đang có được thêm chữ cái mới. */
    public void traverseNextChar(char nextChar) {
        int intNextChar = getValidIntChar(nextChar);
        if(intNextChar < 0) return;
        curNode.add(trie[getLastCurNode()].getNext()[intNextChar]);
    }

    /** Di chuyển vị trí hiện tại khi từ ta đang có xóa đi chữ cái cuối. */
    public void traverseBack() {
        curNode.remove(getCurNodeSize() - 1);
    }

    /** Xóa hết. */
    public void reset() {
        curNode.clear();
    }
    /** Tìm tối đa 7 từ tương tự với từ hiện tại đang có. */
    public void findSimilar(ArrayList<Integer> resList,int curFindNode) {
        if(trie[curFindNode].getId() != 0) {
            resList.add(trie[curFindNode].getId());
        }
        if (resList.size() == 7) {
            return;
        }
        for(int i = 0;i <= 27;++i) {
            if(trie[curFindNode].getNext()[i] != 0) {
                findSimilar(resList, trie[curFindNode].getNext()[i]);
            }
            if (resList.size() == 7) {
                return;
            }
        }
    }

    public ArrayList<Integer> getCurNode() {
        return this.curNode;
    }

    public int getLastCurNode() {
        if(getCurNodeSize() >= 1)
            return this.curNode.get(getCurNodeSize() - 1);
        else return 0;
    }

    public int getCurNodeSize() {
        return this.curNode.size();
    }
    private node[] trie = null;
    private int curMax = 0;
    private ArrayList<Integer> curNode = null;

    class node {

        public node() {
            next = new int[28];
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int[] getNext() {
            return next;
        }

        public void setNext(int index, int newId) {
            this.next[index] = newId;
        }

        private int id;
        private int[] next;
    }
}
