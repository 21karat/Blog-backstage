package com.karat.cn.blog_backstage.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {



    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 根据id查询学生信息
     */
    @Test
    public void findById() {
        String key = "student_" + 1;
        //输出对象
        Student student=null;
        //实例
        ValueOperations<String, Student> operations = redisTemplate.opsForValue();
        //缓存存在
        if (redisTemplate.hasKey(key)) {
            student=operations.get(key);//获取该键对应的对象
            System.out.println(student.toString());
        }
    }


    /**
     * 根据id删除学生信息
     */
    @Test
    public void del() {
        boolean flag=false;
        //缓存存在
        String key = "student_" + 1;
        if (redisTemplate.hasKey(key)) {
            //删除对应缓存
            flag=redisTemplate.delete(key);
        }
        System.out.println(flag);
    }

    /**
     * 修改学生信息
     */
    @Test
    public void modify() {
        //修改对象
        Student student=new Student(1,"荆轲123","男");
        //实例
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存存在
        String key = "student_" +1;
        if (redisTemplate.hasKey(key)) {
            //更新缓存
            operations.set(key, student);
        }
    }

    /**
     * 修改学生信息某一字段
     */
    @Test
    public void modifyBy() {
        //实例
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存存在
        String key = "student_" +1;
        if (redisTemplate.hasKey(key)) {
            //更新缓存
            Student stu=(Student)operations.get(key);
            System.out.println(stu);
            stu.setName("99999");
            operations.set(key,stu);
            System.out.println(operations.get(key));
        }
    }

    /**
     * 添加学生信息
     */
    @Test
    public void add() {
        String key = "student_";
        //修改对象
        Student student=new Student(1,"荆轲","男");
        //实例
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存不存在
        if (!redisTemplate.hasKey(key+student.getId())) {
            //添加缓存
            operations.set(key+student.getId(), student);
        }
    }

    /**
     * 添加学生集合信息
     */
    @Test
    public void addList() {
        String key = "student_list_";
        //查看添加的list
        /*List<Student> userList1=(List<Student>)redisTemplate.opsForValue().get(key+1);
        if(userList1.size()>0){
            userList1.forEach(i->{
                System.out.println(i.toString());
            });
        }*/
        System.out.println("*****************");
        //对象集合
        List list=new ArrayList();
        Student student1=new Student(7,"333","男");
        Student student2=new Student(8,"444","男");
        Student student3=new Student(9,"555","女");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        //缓存不存在
        if (redisTemplate.hasKey(key+1)){
            //添加缓存
            //redisTemplate.opsForValue().set(key+1,list);
            redisTemplate.opsForValue().getAndSet(key+1,list);
        }
        //查看添加的list
       /* List<Student> userList2=(List<Student>)redisTemplate.opsForValue().get(key+1);
        if(userList2.size()>0){
            userList2.forEach(i->{
                System.out.println(i.toString());
            });
        }*/
    }

}
