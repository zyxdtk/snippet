#!/usr/bin/env python
# -*- coding:utf-8 -*-
# import xgboost as xgb
from sklearn import linear_model
# import matplotlib.pyplot as plt
import pandas as pd
from pandas import DataFrame
from sklearn.metrics import mean_squared_error
import math

df = pd.read_csv("/Users/liyong/Documents/protected/xgb/xgb_gender_age/Index-20190114.csv", sep='\t')
# df = DataFrame.from_csv("/Users/liyong/Documents/protected/xgb/xgb_gender_age/Index-20190114.csv", header=0, sep='\t')
a = 110
b = 100
df['score_xgb_dur_ln'] = df.apply(lambda x: math.log(min(max(x['score_xgb_dur'], 1), a)), axis=1)
df['pgan_detail_ln'] = df.apply(lambda x: min(max(x['pgan_detail'], 0.1), b), axis=1)
print(df.head())
print(a)
print(b)

regr = linear_model.LinearRegression()
# regr.fit(df['score_xgb_share'].values.reshape(-1, 1), df['mix_score'])
regr.fit(df.loc[:, ('score_xgb_ctr', 'score_xgb_dur_ln', 'score_xgb_share', 'pgan_detail_ln', 'pgan_hots')], df['mix_score'])
# regr.fit(df.loc[:, ('score_xgb_ctr', 'score_xgb_share')], df['mix_score'])


print(regr)
print(regr.coef_, regr.intercept_)


train_pred = regr.predict(df.loc[:, ('score_xgb_ctr', 'score_xgb_dur_ln', 'score_xgb_share', 'pgan_detail_ln', 'pgan_hots')])
mse = mean_squared_error(df['mix_score'], train_pred)
print(mse)

test_df = pd.read_csv("/Users/liyong/Documents/protected/xgb/xgb_gender_age/Index-20190114-test.csv", sep='\t')
# test_df['score_xgb_dur_ln'] = test_df.apply(lambda x: math.log(max(x['score_xgb_dur'], 1)), axis=1)
test_df['score_xgb_dur_ln'] = test_df.apply(lambda x: math.log(min(max(x['score_xgb_dur'], 1), a)), axis=1)
test_df['pgan_detail_ln'] = test_df.apply(lambda x: min(max(x['pgan_detail'], 1), b), axis=1)
test_pred = regr.predict(test_df.loc[:, ('score_xgb_ctr', 'score_xgb_dur_ln', 'score_xgb_share', 'pgan_detail_ln', 'pgan_hots')])
# test_pred = regr.predict(test_df.loc[:, ('score_xgb_ctr', 'score_xgb_share')])
mse = mean_squared_error(test_df['mix_score'], test_pred)
print(mse)

mean = test_df['mix_score'].mean()
print(mean)
var = test_df['mix_score'].var()
print(var)
