import pymysql


class MySQL:
    def __init__(self, database='cbirc', host='39.97.124.144', user='cbirc', password='Cbirc123.', port=3306):
        self.host = host
        self.user = user
        self.password = password
        self.database = database
        self.port = port

    def open(self):
        self.db = pymysql.connect(host=self.host,
                                  user=self.user,
                                  password=self.password,
                                  db=self.database,
                                  port=self.port)
        self.cur = self.db.cursor()

    def close(self):
        self.cur.close()
        self.db.close()

    # 数据库执行操作方法:
    def Operation(self, sql):
        try:
            self.open()
            self.cur.execute(sql)
            self.db.commit()
            print("ok")
        except Exception as e:
            self.db.rollback()
            print("Failed", e)
            self.close()

    # 数据库查询所有操作方法:
    def Search(self, sql):
        try:
            self.open()
            self.cur.execute(sql)
            result = self.cur.fetchall()
            return result
        except Exception as e:
            print("Failed", e)
            self.close()

if __name__ == '__main__':
    db = MySQL()
    data = db.Search("select * from interpretation where doc_identifier='123'")
    print(data)