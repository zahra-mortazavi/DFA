import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Map<Character, List<String>> grmr = new HashMap<>();

        System.out.println("is your grammar left liner or right liner grammar?\n1)left liner\n2)right liner");
        int ans=Integer.parseInt(sc.nextLine());
        boolean left=false;
        if (ans==1)
            left=true;
        else
            left=false;
        System.out.println("enter number of your grammar lines");
        int num=Integer.parseInt(sc.nextLine());
        System.out.println("enter each line like : S=aB|c");
        for (int i = 0; i < num; i++) {
            if (!validateGrammar(sc.nextLine(),left,grmr)) {
                System.out.println("grammar is wrong");
                return ;
            }
        }

         String input = " ";
        while (!input.equals("END")){
            System.out.println("enter string");
         input = sc.nextLine();
        if (left){
            StringBuilder sb=new StringBuilder(input);
            input=sb.reverse().toString();
        }
        if (input.equals("$")){
            System.out.println("YES");
            continue;
        }
        if(checkString(input,grmr)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }}
    public  static boolean checkString(String input,Map<Character, List<String>> grmr) {
        Stack<Character> stack = new Stack<>();
        stack.push('S');

        for (int i = 0; i < input.length(); i++) {
            char currentRule = stack.pop();
            char ptrChr = input.charAt(i);

            if (currentRule == (ptrChr)) {
                continue;
            }

            if (!grmr.containsKey(currentRule)) {
                return false;
            }

            List<String> allRules = grmr.get(currentRule);
            boolean find = false;
            for (int j = allRules.size() - 1; j >= 0; j--) {
                if(allRules.get(j).charAt(0) == ptrChr) {
                    find = true;
                    for (int k =  allRules.get(j).length()-1; k > 0; k--)
                        stack.push(allRules.get(j).charAt(k));
                }
            }
            if(!find)
                return false;
        }

        return stack.isEmpty();
    }
    public static boolean validateGrammar(String  input, boolean left, Map<Character, List<String>> grmr){
        String []input2=input.split("=");
        if ((int)input2[0].toCharArray()[0]<(int) 'A'||(int)input2[0].toCharArray()[0]>(int) 'Z')
            return false;
        grmr.put(input2[0].toCharArray()[0],new ArrayList<>());
        String[]input3=input2[1].split("\\|");

        for (int i = 0; i < input3.length; i++) {
            if (input3[i].length()==1){
            if (input3[i].length()==1&&input3[i].charAt(0) > 'a' || input3[i].charAt(0) < 'z'){
                grmr.get(input2[0].toCharArray()[0]).add(input3[i]);
            }}
            else {
                if (left) {

                    for (int j = 1; j < input3[i].length(); j++) {
                        if (input3[i].charAt(j) < 'a' || input3[i].charAt(j) > 'z')
                            return false;

                    }
                } else {

                    for (int j = 0; j < input3[i].length() - 1; j++) {
                        if (input3[i].charAt(j) < 'a' || input3[i].charAt(j) > 'z')
                            return false;
                    }
                }
            }

            StringBuilder sb = new StringBuilder(input3[i]);

            if (left)

                grmr.get(input2[0].toCharArray()[0]).add(sb.reverse().toString());

            else
                grmr.get(input2[0].toCharArray()[0]).add(input3[i]);
        }

        return true;}

}
