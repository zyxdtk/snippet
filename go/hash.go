package main

import (
  "fmt"
  "strconv"
  "crypto/md5"
  "strings"
  "regexp"
)


func HashIndex(expID, id string, totalCount uint64) (index uint64) {
  s := fmt.Sprintf("%x", md5.Sum([]byte(expID+id)))
  fmt.Println(s)
  i, _ := strconv.ParseUint(s[24:], 16, 64)
  index = i % totalCount
  return
}

func main() {
  //expID := "5b2a6073ab9bad551c827cd8"
  //id := "d9c75e61edfc03bc"
  //totalCount := 20
  //a := HashIndex(expID, id, uint64(totalCount))
  //fmt.Println(a)
  rawTitle, topics := "当你在夜晚孤军奋战时，满天星光都为你亮，你若想要世界最好的东西，得先让世界看到最好的你#aaa励志语录", "#aa励志语录"
  //err, title := service.RefreshTitle(rawTitle, topics)
  s := strings.Split("", "#")
  fmt.Println(rawTitle,topics,s, len(s))

  r, _ := regexp.Compile("([p{Han}]+)")
  fmt.Println(r.MatchString(rawTitle)) //true

  reg := regexp.MustCompile("(#[0-9a-zA-Z\u4e00-\u9fa5]+)")
  titleSlice := reg.FindAll([]byte(rawTitle), -1)
  topicSlice := reg.FindAll([]byte(topics), -1)
  for _, v := range titleSlice {
    fmt.Println(string(v))
  }
  for _, v := range topicSlice {
    fmt.Println(string(v))
  }

}