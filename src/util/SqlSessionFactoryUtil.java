package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Version:
 * @Description:
 * @Author:
 * @Modified:
 * @Date: 2020/12/10 18:06
 */
public class SqlSessionFactoryUtil {

    private SqlSessionFactoryUtil(){
    }

    public static SqlSessionFactory sqlSessionFactory = null;

    public static final Class<SqlSessionFactoryUtil> LOCK = SqlSessionFactoryUtil.class;

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        if (sqlSessionFactory != null){
            return sqlSessionFactory;
        }
        synchronized (LOCK){
            if (sqlSessionFactory == null){
                InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
                return sqlSessionFactory;
            }else {
                return sqlSessionFactory;
            }
        }
    }

    public static SqlSession getSqlSession() throws IOException {
        if (sqlSessionFactory == null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }

}