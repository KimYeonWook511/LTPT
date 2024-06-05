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
		// ������ �߰��ϴ� �޼���
		sqlSession.insert(namespace + ".joinUser", dto);
	}

	@Override
	public UserVO readUser(String userid) throws Exception {
		// ������ �о���� �޼���		
		return (UserVO)sqlSession.selectOne(namespace + ".readUser", userid);
	}
	
	@Override
	public String deleteUser(String userid, String userpw) throws Exception {
		// ������ �����ϴ� �޼���
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		System.out.println(sqlSession.delete(namespace.concat(".deleteUser"), paramMap)); // �� �����Ǹ� 1, ������ �����Ͱ� ������ 0, ������ -1
		return "�����Ϸ�";
	}
	
	@Override
	public List<UserVO> listTrainer() throws Exception {
		// ��� Ʈ���̳ʸ� �о���� �޼��� 
		return sqlSession.selectList(namespace.concat(".listTrainer"));
	}
	
	@Override
	public void updateAuth(UserDTO dto) throws Exception {
		sqlSession.update(namespace + ".updateAuth", dto);
	}
}
