package com.pht.groovy

class CalcTest {
    public static void main(String[] args) {
        def test = new CalcTest()

    }
    def returVL(String testname){
        def map = new HashMap<>()
        map.put("1","张三疯");
        map.put("2","斩三疯");
        map.put("3",testname);
        return  map.toString();
    }
}
