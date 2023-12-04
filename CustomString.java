/*
 * Kyle Krstulich
 * 11/3/23
 * CustomString.java
 */
public class CustomString {

    private char[] value;
    
    //Constructors
    public CustomString(String s){
        this.value = new char[s.length()];
        for (int i = 0; i < this.value.length; i++){
            this.value[i] = s.charAt(i);
        }
    }

    public CustomString(char[] a){
        this.value = a;
    }

    public int length(){
        return this.value.length;
    }

    public char charAt(int i){
        return this.value[i];
    }

    public CustomString subString(int i, int j){
        char[] substring = new char[j - i];
        for (int start = i; start < j; start++){
            substring[start-i] = this.value[start];
        }

        return new CustomString(substring);
    }

    public boolean contains(CustomString substring){
        int j = substring.length();
        CustomString compString;

        for(int i = 0; i < this.length(); i++){
            compString = this.subString(i, j);

            if(compString.equals(substring)) return true;

            j++;
        }

        
        return false;
    }

    public boolean startsWith(CustomString prefix){
        int prefixSize = prefix.length();
        int endIndex = this.value.length - prefixSize - 1;
        CustomString compString = this.subString(0,endIndex);
        return compString.equals(prefix);
    }

    public boolean endsWith(CustomString postfix){
        int postfixSize = postfix.length();
        int startIndex = this.value.length - postfixSize;
        CustomString compString = this.subString(startIndex, this.value.length);
        return compString.equals(postfix);
    }

    public int indexOf(CustomString pattern){
        int j = pattern.length();
        CustomString compString;
        int returnIndex = -1;

        for(int i = 0; i < this.length(); i++){
            compString = this.subString(i, j);

            if(compString.equals(pattern)){
                returnIndex = i;
                break;
            }

            j++;
        }

        return returnIndex;
    }

    public int indexOf(CustomString pattern, int i){
        int j = pattern.length() + i;
        CustomString compString;
        int returnIndex = -1;

        for(int index = i; index < this.length(); index++){
            compString = this.subString(index, j);

            if(compString.equals(pattern)){
                returnIndex = index;
                break;
            }

            j++;
        }

        return returnIndex;
    }

    public CustomString concat(CustomString t){
        int newSize = this.length() + t.length();
        char[] newString = new char[newSize];

        for(int i = 0; i < newSize; i++){
            if(i < this.length()){
                newString[i] = this.charAt(i);
            }else{
                newString[i] = t.charAt(i - this.length());
            }
        }
        return new CustomString(newString);
    }

    public int compareTo(CustomString t){
        int size = t.length();
        if(size > this.length()) return size - this.length();
        if(size < this.length()) return size - this.length();
        return 0;
    }

    public CustomString toLowerCase(){
        int charAsInt;

        //Capital range = [65,90]
        for(int i = 0; i < this.length(); i++){
            charAsInt = (int)this.charAt(i);

            if((charAsInt >= 65) && (charAsInt <= 90)){
                charAsInt += 32;

                this.value[i] = (char) charAsInt;
            }
        }
        return this;
    }

    public CustomString toUpperCase(){
        int charAsInt;

        //lower range = [97,122]
        for(int i = 0; i < this.length(); i++){
            charAsInt = (int)this.charAt(i);

            if((charAsInt >= 97) && (charAsInt <= 122)){
                charAsInt -= 32;

                this.value[i] = (char) charAsInt;
            }
        }
        return this;
    }

    public CustomString replace(CustomString a, CustomString b){
        int replaceIndex = this.indexOf(a);
        int size = a.length();

        for(int i = 0; i < size; i++){
            this.value[replaceIndex+i] = b.charAt(i);
        }
        return this;
    }

    //TODO
    public CustomString trim(){
        return this;
    }

    //TODO
    public CustomString[] split(CustomString delimiter){
        int startIndex, endIndex;
        int size = delimiter.length();
        int numberOfSubstrings = 1;

        //counts the number of substrings
        for(int i = 0; i < this.length(); i++ ){
            if(this.charAt(i) == ' '){
                numberOfSubstrings++;
            }
        }
        CustomString[] splitString = new CustomString[numberOfSubstrings];

        int whereToStartSplit = 0;
        int whereToEndSplit = 0;

        for(int i = 0; i < this.length(); i++ ){
            if(this.charAt(i) == ' '){

            }
        }


        return splitString;
    }

    public boolean equals(CustomString compareString){
        boolean result = this.value.length == compareString.length();

        if(result){
            for(int i = 0; i < this.value.length; i++){
                if(this.value[i] != compareString.charAt(i)){
                    return false;
                }
            }
        }


        return result;
    }

    public String toString(){
        String returnString = "";
        for (int i = 0; i < this.value.length; i++){
            returnString += this.value[i];
        }
        return returnString;
    }

    public static void main(String[] args) {
        CustomString test = new CustomString("My name is Kyle");
        CustomString test2 = new CustomString("le");
        CustomString test3  = new CustomString("le");
        CustomString test4 = new CustomString("My name");

        System.out.println(test.endsWith(test2));
        System.out.println(test2.equals(test));

        System.out.println(test.startsWith(test4));

        System.out.println(test.indexOf(test2, 10));

        System.out.println(test3.concat(test2));






        
    }



}
