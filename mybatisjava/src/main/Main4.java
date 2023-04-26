package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;

public class Main4 {
	private final static SqlSessionFactory sqlMap;//member로 들어가 main에서도 접근가능하게??
	static {
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
		//쿼리문 같은게 sqlMap에 들어있음
	}
	private static Class<StudentMapper> cls = StudentMapper.class;
		//cls 는 Class타입
		//Class 는 StudentMapper 정보를 가지고 있음
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("모든 학생 정보 조회하기");
		List<Student> list = session.getMapper(cls).select();
		//Student 정보를 읽어서 select 정보를 가져와줘
		//result 타입은 select의 리턴타입 => list???
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생 정보 조회하기");
		list = session.getMapper(cls).selectGrade(1);
		for(Student s : list) System.out.println(s);
		
		System.out.println("9711 학생의 정보 조회하기");
		Student st = session.getMapper(cls).selectStudno(970111);
		System.out.println(st);
		
		System.out.println("이름이 진영훈 학생 정보 조회하기");
		list = session.getMapper(cls).selectName("진영훈");
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 학생 중 키가 180 이상인 정보 조회하기");
		Map<String,Object> map = new HashMap<>();
		map.put("grade",1);
		map.put("height",180);
		list = session.getMapper(cls).selectGradeHeight(map);
		for(Student s : list) System.out.println(s);
		list = session.getMapper(cls).selectGradeHeight2(1,180);
		//selectGradeHeight2 는 위에적은 selectGradeHeight과 같지 않게 하기 위해
		for(Student s : list) System.out.println(s);
	}

}
