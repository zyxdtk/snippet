package main

import (
  "fmt"
  "strconv"
  "crypto/md5"
)


func HashIndex(expID, id string, totalCount uint64) (index uint64) {
  s := fmt.Sprintf("%x", md5.Sum([]byte(expID+id)))
  fmt.Println(s)
  i, _ := strconv.ParseUint(s[24:], 16, 64)
  index = i % totalCount
  return
}

func main() {
  expID := "5b2a6073ab9bad551c827cd8"
  id := "d9c75e61edfc03bc"
  totalCount := 20
  a := HashIndex(expID, id, uint64(totalCount))
  fmt.Println(a)
}