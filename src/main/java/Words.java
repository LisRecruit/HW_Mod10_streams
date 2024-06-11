public class Words {
    Words (String word){
        this.word=word;
        count++;
    }
    private String word="";
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return word+" "+count;
    }
    public void addWord(){
        count++;
    }
}
