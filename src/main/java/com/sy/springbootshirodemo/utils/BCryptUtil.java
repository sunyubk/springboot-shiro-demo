package com.sy.springbootshirodemo.utils;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @ClassName BCryptUtil
 * @Description
 * @Author sunyu
 * @Date 2023/9/25 09:08
 * @Version 1.0
 **/
public class BCryptUtil {
    public static String encode(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());             //对明文密码进行加密,并返回加密后的密码
    }

    public static boolean match(String password, String encodePassword){          //将明文密码跟加密后的密码进行匹配，如果一致返回true,否则返回false
        return BCrypt.checkpw(password,encodePassword);
    }

    public static String getSalt() {
        return BCrypt.gensalt();
    }


    public static void main(String[] args) {
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode1 = encode("123");
        String encode2 = encode("123");
        System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(match("123", encode1));
        System.out.println(match("123", encode2));
    }
}
