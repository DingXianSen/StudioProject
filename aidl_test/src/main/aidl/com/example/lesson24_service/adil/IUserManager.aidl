package com.example.lesson24_service.adil;
/*
*除java.lang包下的类不需要导包即可使用外，其他所有类型都需要导包才能使用，即使是同类的包
*
* 所有的引用数据类型需要使用in,out,inout修饰，表示参数变量是作为什么功能使用
*/
import com.example.lesson24_service.User;
interface IUserManager {
    int count();
    void addUser(in User user);//输入一个User
    User loadUser(int id);//返回一个User按照User id
    List<User> getUsers();
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
