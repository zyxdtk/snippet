package main

import (
	_ "github.com/apache/calcite-avatica-go/v5"
	"database/sql"
	"fmt"
)

func main() {
	db, err := sql.Open("avatica", "http://172.25.188.171:8765")
	fmt.Println("err1", err)
	rows, err := db.Query("Select * from \"funny\".\"recommend_http_logs_etl\" limit 10")
	defer rows.Close()
	fmt.Println("err2", err)
	fmt.Println("rows", rows)
	a, err := rows.Columns()
	fmt.Println("err3", err)
	fmt.Println("a", a)
	var list []interface{}
	rows.Scan(&list)
	rows.ColumnTypes()
	fmt.Println("list", list)
	for _, l := range list {
		fmt.Println("l", l)
	}

	var (
		Id          string
		User_id     int
		Create_time int
		Type        string
		Line        string
	)
	for rows.Next() {

		err := rows.Scan(&Id, &User_id, &Create_time, &Type, &Line)
		if err != nil {
			errors := fmt.Sprintln("err: ", err)
			print(errors)
		}

		result := fmt.Sprintln("result: ", Id, User_id, Create_time, Type, Line)
		print(result)
	}
}
