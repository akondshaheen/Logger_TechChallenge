public class MyMain {


    public static void main(String[] args) {
        Parse parse = new Parse();
        parse.parseLine("80.238.9.179,1025000,FAILURE,badUsername");
        parse.parseLine("80.238.9.179,1025000,FAILURE,uglyUsername");
        parse.parseLine("80.238.9.179,1025000,FAILURE,testUsername");
        parse.parseLine("192.168.0.1,1025000,FAILURE,localhost");
        parse.parseLine("80.238.9.179,1025000,SUCCESS,goodUsername");

        parse.parseLine("80.238.9.179,1025000,FAILURE,goodUsername");
        parse.parseLine("80.238.9.179,1025000,FAILURE,goodUsername");

        parse.parseLine("80.238.9.179,1025000,FAILURE,superUsername");
        parse.parseLine("80.238.9.179,2000000,FAILURE,badUsername");

    }
}
