package main

import (
	"fmt"
	"github.com/bean-du/dfa"
	"github.com/feiin/sensitivewords"
)

func beandu() {
	sensitive := []string{"王八蛋", "王八羔子"}

	fda := dfa.New()
	fda.AddBadWords(sensitive)

	str := "你个王#八……羔子， 你就是个王*八/蛋"
	fmt.Println(fda.Check(str))
}

func feiin() {
	sensitive := sensitivewords.New()
	/*
	 * keywords.txt:
	 * 尼玛
	 * 哈哈
	 */
	sensitive.LoadFromFile("./resource/keywords.txt")

	//sensitive.AddWord("测试")
	//sensitive.AddWords("+q", "+v")

	s, keyword := sensitive.Find("园发生砍 测试啊+q/+v,尼玛,哈哈")
	fmt.Printf("Find:%v, %v\n", s, keyword) //true,测试
	s, results := sensitive.FindAll("园发生砍 测试啊+q/+v,尼玛,哈哈")
	fmt.Printf("FindAll:%v, %v\n", s, results) //true, [测试 +q +v 尼玛 哈哈哈]
	s, results = sensitive.FindAny("园发生砍测试啊+q/+v,尼玛,哈哈", 3)
	fmt.Printf("FindAny:%v, %v\n", s, results) //true, [测试 +q +v]

	s = sensitive.Check("园发生砍测试啊+q/+v,尼玛,哈哈")
	fmt.Printf("Check: %v\n", s) //true

	str := sensitive.Filter("测试园发生砍测试啊+q/+v,尼玛,哈哈")
	fmt.Printf("Filter:%v\n", str) //**啊**/**,**,**
}

func main() {
	feiin()
	beandu()
}
