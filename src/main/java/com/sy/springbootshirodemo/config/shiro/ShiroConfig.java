package com.sy.springbootshirodemo.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description
 * @Author sunyu
 * @Date 2023/9/25 08:42
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${shiro.hashIterations}")
    private int hashIterations;

    @Bean
    public DefaultWebSecurityManager securityManager() {
        // 创建默认的安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置安全管理器使用的 Realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public Realm myShiroRealm() {
        MyRealm realm = new MyRealm();
        // 设置密码加密器，单向哈希+盐
        // realm.setCredentialsMatcher(credentialsMatcher());
        // 设置密码加密器，使用BCrypt
        realm.setCredentialsMatcher(credentialsMatcherByBCrypt());
        return realm;
    }

    /**
     * 单向哈希+盐
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        // 创建密码匹配器对象
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置密码算法名称
        credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        //设置哈希迭代次数
        credentialsMatcher.setHashIterations(hashIterations);
        // 返回密码匹配器对象
        return credentialsMatcher;
    }

    /**
     * 单向哈希+盐
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcherByBCrypt() {
        return (token, info) -> {
            UsernamePasswordToken userToken = (UsernamePasswordToken) token;
            // 要验证的明文密码
            String rawPassword = new String(userToken.getPassword());
            // 数据库中的加密后的密文
            String hashed = info.getCredentials().toString();
            return BCrypt.checkpw(rawPassword, hashed);
        };
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean factoryBean() {
        // 创建 Shiro 过滤器工厂 Bean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        // 设置登录页面的 URL
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 设置过滤规则
        Map<String, String> map = new HashMap<>();
        // 指定 /login URL 不需要进行认证
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        // 其他 URL 需要进行认证
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 返回 Shiro 过滤器工厂 Bean
        return shiroFilterFactoryBean;
    }


    //springboot如何注册web三大组件。
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        // 创建过滤器注册Bean对象
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // 设置过滤器为DelegatingFilterProxy
        filterRegistrationBean.setFilter(new DelegatingFilterProxy());

        // 设置过滤器名称
        filterRegistrationBean.setName("shiroFilter");

        // 添加过滤器的URL模式，匹配所有URL
        filterRegistrationBean.addUrlPatterns("/*");

        // 返回过滤器注册Bean对象
        return filterRegistrationBean;
    }



}
