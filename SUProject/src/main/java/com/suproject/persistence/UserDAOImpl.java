package com.suproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.suproject.domain.UserDTO;
import com.suproject.domain.UserVO;

@Repository
@Qualifier("1")
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.suproject.mappers.UserMapper";
	
	@Override
	public void joinUser(UserDTO dto) throws Exception {
		// 유저를 추가하는 메서드
		sqlSession.insert(namespace + ".joinUser", dto);
	}

	@Override
	public UserVO readUser(String userid) throws Exception {
		// 유저를 읽어오는 메서드		
		return (UserVO)sqlSession.selectOne(namespace + ".readUser", userid);
	}
	
	@Override
	public String deleteUser(String userid, String userpw) throws Exception {
		// 유저를 삭제하는 메서드
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		System.out.println(sqlSession.delete(namespace.concat(".deleteUser"), paramMap)); // 잘 삭제되면 1, 삭제될 데이터가 없으면 0, 오류시 -1
		return "삭제완료";
	}
	
	@Override
	public List<UserVO> listTrainer() throws Exception {
		// 모든 트레이너를 읽어오는 메서드 
		return sqlSession.selectList(namespace.concat(".listTrainer"));
	}
	
	@Override
	public void updateAuth(UserDTO dto) throws Exception {
		sqlSession.update(namespace + ".updateAuth", dto);
	}
}
