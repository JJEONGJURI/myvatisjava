package main;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Main1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			//리소스로부터 mapper 폴더에 존재하는 mybatis-config.xml 파일 읽기
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			//sqlMap : sql 구문들을 xml에 있는 id 속성값으로 저장된 객체
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
			//builder가 파일읽어?
		} catch(IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		//session : Connection 객체를 mybatis에서 연결한 객체.  
		SqlSession session = sqlMap.openSession();
		//selectOne : 결과레코드가 1건인 경우.=>결과레코드가 2개 이상인 경우 오류 발생
		x = (Integer)session.selectOne("member.count");
		System.out.println("member 테이블의 레코드 갯수:"+x);
		
		
		//멤버테이블을 가지고 오려면 빈클래스가 필요함
		System.out.println("member 테이블 전체 조회===============");
		//selectList : 결과레코드가 여러건인 경우. => List 객체로 리턴
		List<Member> list = session.selectList("member.list");
		for(Member m : list) System.out.println(m);
		System.out.println("member 테이블 admin 정보 조회===============");
		Member mem = session.selectOne("member.selectid","admin");
	//	List<Member> mem = session.selectList("member.selectid","admin");
		System.out.println(mem);
		System.out.println("member 테이블 이름에 '스'를 가진 정보 조회===============");
		list = session.selectList("member.selectname","%스%");
		for(Member m : list) System.out.println(m);
		System.out.println("member 테이블 이름에 '스'를 가진 정보 조회2===============");
		list = session.selectList("member.selectname2","스");
		for(Member m : list) System.out.println(m);
		System.out.println("member 테이블 gender에 1을 가진 정보 조회===============");
		list = session.selectList("member.selectgender",1);
		for(Member m : list) System.out.println(m);
		System.out.println("member 테이블 이름에 '스'를 가진 남자 정보 조회===============");
		//두개 이상은 Map 객체 사용해야함
		Map<String,Object> map = new HashMap<>();
		map.put("name", '스');
		map.put("gender", 1);
		list = session.selectList("member.selectnamegender",map);
		for(Member m : list) System.out.println(m);
	}

}
