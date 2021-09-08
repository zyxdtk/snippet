import phoenixdb
import phoenixdb.cursor

database_url = 'http://47.93.79.196:8765/'
conn = phoenixdb.connect(database_url)
print(database_url)
print(conn)
cursor = conn.cursor()
cursor.execute("Select * from \"funny\".\"recommend_http_logs_etl\" limit 10")
print(cursor.fetchall())

cursor = conn.cursor(cursor_factory=phoenixdb.cursor.DictCursor)
cursor.execute("Select * from \"funny\".\"recommend_http_logs_etl\" limit 10")
print(cursor.fetchone()['USERNAME'])