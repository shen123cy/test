package com.example.demo1.other.thymeleafDemo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.thymeleaf.ThymeleafEngine;
import com.example.demo1.dto.User;

public class ThymeleafTest {

    public void test() {
        TemplateConfig templates = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
        templates.setCustomEngine(ThymeleafEngine.class);
        TemplateEngine engine = TemplateUtil.createEngine(templates);
        Template reqTexttemplate = engine.getTemplate("t.html");
        User user = User.builder().id(1L).name("z").age(12).build();
        String render = reqTexttemplate.render(BeanUtil.beanToMap(user));
        String s = CharSequenceUtil.subBetween(render, "<body>", "</body>");
        System.out.println(s);
    }
}
