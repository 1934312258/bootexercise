package com.kevin.javaDemo.lambda;

import com.alibaba.fastjson.JSON;
import jodd.util.ObjectUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2020-7-2 16:30
 * @description todo
 **/
public class People {
    Integer age;
    String name;
    String id;
    Date brith;

    public People(String id, Integer age, String name, Date brith) {
        this.age = age;
        this.brith = brith;
        this.id = id;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    static class student{
        Long age;
        Long score;
        public student(Long age,Long score){
            this.age = age;
            this.score = score;
        }

        public Long getAge() {
            return age;
        }

        public void setAge(Long age) {
            this.age = age;
        }

        public Long getScore() {
            return score;
        }

        public void setScore(Long score) {
            this.score = score;
        }
    }

    public static void main(String[] args) {
        List<People> peoples = new ArrayList<>();
        peoples.add(new People("K.O1", 21, "kevin", new Date()));
        peoples.add(new People("K.O3", 23, "kevin1", new Date()));
        peoples.add(new People("K.O4", 24, "kevin", new Date()));
        peoples.add(new People("K.O5", 25, "kevin1", new Date()));
        peoples.add(new People("K.O2", 22, "kevin", new Date()));
        peoples.add(new People("K.O6", 26, "kevin2", new Date()));

        List<student> students = new ArrayList<>();
        students.add(new student(1L,2L));
        students.add(new student(3L,4L));

        List<String> strings = peoples.stream().map(a->StringUtils.isEmpty(a.getAge())?a.getId():a.getName()).collect(Collectors.toList());
        strings = peoples.stream().map(a->!StringUtils.isEmpty(a.getAge())?a.getId():a.getName()).collect(Collectors.toList());
        Long list = students.stream().mapToLong(a->true?a.getAge():a.getScore()).sum();
        list = students.stream().mapToLong(a->false?a.getAge():a.getScore()).sum();

        String str = "{\"age\":18,name:\"kevin\"}";
        People people = JSON.parseObject(str,People.class);
        System.out.println();
    }
}
