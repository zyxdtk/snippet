package main

import (
	"fmt"
	"encoding/json"
)

func main() {
	json_str := "{\"exp_name\":\"newuser_xgbplus\",\"xgb_config\":{\"pgan\":{\"completion\":\"completion\",\"negative\":\"negative\",\"detail_dur\":\"detail_dur\"},\"base_keys\":[\"ctr\",\"completion\"],\"feedback_keys\":[\"like\",\"share\"],\"consume_keys\":[\"detail_dur\"],\"model\":{\"dur\":\"xgbdur\",\"share\":\"xgb_0_v1_share_0\",\"like\":\"xgb_0_v1_like_0\",\"ctr\":\"xgbctrv2\"}},\"boy_video_score_config\":{\"max\":{\"completion\":1,\"ctr\":1,\"share\":0.04,\"like\":0.04,\"negative\":0.5,\"detail_dur\":10},\"linear\":{\"completion\":1,\"ctr\":1,\"share\":1,\"like\":1,\"detail_dur\":1},\"exp\":{\"completion\":0.5,\"share\":0.5,\"like\":0.5,\"ctr\":0.5,\"detail_dur\":0.5},\"min\":{\"completion\":0.00001,\"ctr\":0.00001,\"share\":0.001,\"detail\":1,\"like\":0.001}},\"boy_img_score_config\":{\"max\":{\"completion\":1,\"ctr\":1,\"share\":0.04,\"like\":0.04,\"negative\":0.5,\"detail_dur\":10},\"linear\":{\"completion\":1,\"ctr\":1,\"share\":1,\"like\":1,\"detail_dur\":1},\"exp\":{\"completion\":0.5,\"share\":0.5,\"like\":0.5,\"ctr\":0.5,\"detail_dur\":0.5},\"min\":{\"completion\":0.00001,\"ctr\":0.00001,\"share\":0.001,\"detail\":1,\"like\":0.001}},\"girl_video_score_config\":{\"max\":{\"completion\":1,\"ctr\":1,\"share\":0.04,\"like\":0.04,\"negative\":0.5,\"detail_dur\":10},\"linear\":{\"completion\":1,\"ctr\":1,\"share\":1,\"like\":1,\"detail_dur\":1},\"exp\":{\"completion\":0.5,\"share\":0.5,\"like\":0.5,\"ctr\":0.5,\"detail_dur\":0.5},\"min\":{\"completion\":0.00001,\"ctr\":0.00001,\"share\":0.001,\"detail\":1,\"like\":0.001}},\"girl_img_score_config\":{\"max\":{\"completion\":1,\"ctr\":1,\"share\":0.04,\"like\":0.04,\"negative\":0.5,\"detail_dur\":10},\"linear\":{\"completion\":1,\"ctr\":1,\"share\":1,\"like\":1,\"detail_dur\":1},\"exp\":{\"completion\":0.5,\"share\":0.5,\"like\":0.5,\"ctr\":0.5,\"detail_dur\":0.5},\"min\":{\"completion\":0.00001,\"ctr\":0.00001,\"share\":0.001,\"detail\":1,\"like\":0.001}},\"go_to_newsystem\":1}"
	m := make(map[string]interface{})
	err := json.Unmarshal([]byte(json_str), &m)
	fmt.Println(err)
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(m["exp_name"])
		data := m["boy_video_score_config"]
		if v, ok := data.(map[string]interface{})["max"].(map[string]interface{}); ok {
			fmt.Println(ok, v["ctr"].(float64), v["negative"])
		}

		key := m["xgb_config"]
		if v, ok := key.(map[string]interface{})["base_keys"].([]interface{}); ok {
			fmt.Println(ok, v)
			for _, a := range v {
				a =  a.(string)
				fmt.Println(ok, a)
			}
		}
	}
	fmt.Println("over")
}