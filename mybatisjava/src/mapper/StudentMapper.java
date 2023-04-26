package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import main.Student;
/*
 * 인터페이스 방식으로 sql Mapper 설정하기
 * 	1. mybatis-config.xml 에 mapper에 package로 설정하기
 * 	2. namespace : mapper.StudentMapper 
 * 	3. 메서드의 이름이 sql 구문의 이름 => 같은 이름을 가진 메서드는 허용 안함.
 * 			=> 오버로딩 불가
 * 
 */
public interface StudentMapper {

	@Select("select * from student")
	List<Student> select();

	@Select("select * from student where grade=#{value}")
	List<Student> selectGrade(int grade);

	@Select("select * from student where studno=#{value}")
	Student selectStudno(int studno);
	
	@Select("select * from student where name=#{value}")
	List<Student> selectName(String name);

	@Select("select * from student where grade=#{grade} and height >=#{height}")
	List<Student> selectGradeHeight(Map map);
	
	@Select("select * from student where grade=#{grade} and height >=#{height}")
	List<Student> selectGradeHeight2(@Param("grade")int grade, @Param("height")int height);
	//앞에있는 grade는 grade 파라미터를 사용할거야 
	//(@Param("grade")int a, @Param("height")int b) 라고 변수명 다르게 해도됨 변수명은 힘이 없음!

	@Insert("insert into student (studno,name,jumin,id) "
			+ "values(#{studno},#{name},#{jumin},#{id})")
	int insert(Student st);

	@Update("update student set grade=#{grade},weight=#{weight}, " 
			+ " height=#{height} where studno=#{studno}")
	int update(Student st);
	//int 나 void 로 쓰면됨...?
	@Delete("delete from student where studno=#{value}")
	int delete(int studno);

}
