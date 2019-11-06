package com.tahoecn.xkc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/***
 * 代码生成器
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GenerateCodeBuilder {

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url;

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String user;

    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;

    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String driver;

    //生成文件所在项目路径
    private static String baseProjectPath = "d:/wc/";

    //基本包名
    private static String basePackage="com.tahoecn.xkc";

    //要生成的表名
    private static String[] tables= {"costomer_report"};

    @Test
    public void generateCodeBuilder(){

        AutoGenerator gen = new AutoGenerator();

        gen.setDataSource(new DataSourceConfig()
                .setDbType(DbType.SQL_SERVER)
                .setDriverName(this.driver)
                .setUrl(this.url)
                .setUsername(this.user)
                .setPassword(this.password)
        );

        gen.setGlobalConfig(new GlobalConfig()
                .setOutputDir(this.baseProjectPath)
                .setFileOverride(true)
                .setBaseResultMap(false)
                .setBaseColumnList(false)
                .setOpen(true)
                .setAuthor("YYY")
                .setSwagger2(true)
        );

        gen.setStrategy(new StrategyConfig()
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setInclude(tables)
                        .setRestControllerStyle(true)
                        .setEntityLombokModel(false)
                        .setSuperControllerClass("com.tahoecn.xkc.controller.TahoeBaseController")

        );

        gen.setPackageInfo(new PackageConfig()
                .setParent(basePackage)
                .setEntity("model")
        );

        gen.setTemplateEngine(new FreemarkerTemplateEngine());
        gen.setTemplate(new TemplateConfig());

        // 执行生成
        gen.execute();
    }
}
