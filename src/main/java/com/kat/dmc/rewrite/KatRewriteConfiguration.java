package com.kat.dmc.rewrite;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

@RewriteConfiguration
public class KatRewriteConfiguration extends HttpConfigurationProvider {

    @Override
    public int priority()
    {
        return 10;
    }


    @Override
    public Configuration getConfiguration(final ServletContext context)
    {
        return ConfigurationBuilder.begin()
//                .addRule().perform(Log.message(Level.INFO, "Rewrite is active :D."))
                .addRule(Join.path("/").to("/index.xhtml").withInboundCorrection())
                .addRule(Join.path("/login").to("/pages/admin/login.xhtml").withInboundCorrection())
                .addRule(Join.path("/logout").to("/pages/admin/logout.xhtml").withInboundCorrection())
                .addRule(Join.path("/home").to("/pages/tools/dashboard.xhtml").withInboundCorrection())
                .addRule(Join.path("/employee").to("/pages/admin/employee.xhtml").withInboundCorrection())
                .addRule(Join.path("/user").to("/pages/admin/user.xhtml").withInboundCorrection())
                ;
    }
}
