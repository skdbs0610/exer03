package models;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class AccountDao extends MybatisDao {

	public AccountDao() throws IOException {
		super();
	}
	
	//회원가입할 때, 중복처리용
	public Map getAccountById(String id){
		
		SqlSession sql = factory.openSession();
		
		try {
			Map p = sql.selectOne("account.getAccountById",id);
			return p;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int addAccount(Map map) {
		SqlSession sql = factory.openSession();
		try {
			int r = sql.insert("account.addAccount",map);
			if(r==1) {
				sql.commit();
			}
			return r;
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//로그인용
	public Map loginCheck(Map map){
		SqlSession sql = factory.openSession();
		try {
			Map p = sql.selectOne("account.loginCheck",map);
			System.out.println(p);
			return p;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
