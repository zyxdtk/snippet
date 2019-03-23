#!/usr/bin/env python
# coding:utf-8

from odps.udf import annotate
import hashlib

# odpscmd -e "DROP FUNCTION rec_hash"
# odpscmd -e "add py ../utils/odps_udf_rec.py -f"
# odpscmd -e "create function rec_hash as 'odps_udf_rec.HASH' using 'zuiyou_recommendation/resources/odps_udf_rec.py';"

@annotate("*->bigint")
class HASH(object):

	def evaluate(self, arg0, arg1, arg2):
		md5str = arg0 + arg1
		ml = hashlib.md5()
		ml.update(md5str.encode("utf-8"))
		token = ml.hexdigest()[24:]
		val_int = int(token, 16)
		res = (val_int % int(arg2))
		return res


if __name__ == '__main__':
	hash = HASH()
	expID = "5b2a6073ab9bad551c827cd8"
	id = "d9c75e61edfc03bc"
	totalCount = 20
	bucket = hash.evaluate(expID, id, totalCount)
	print(bucket)
