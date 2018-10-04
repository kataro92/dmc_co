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
                .addRule(Join.path("/").to("/index.xhtml"))
                .addRule(Join.path("/error").to("/pages/error.xhtml"))
                .addRule(Join.path("/config").to("/pages/admin/config.xhtml"))
                .addRule(Join.path("/login").to("/pages/admin/login.xhtml"))
                .addRule(Join.path("/logout").to("/pages/admin/logout.xhtml"))
                .addRule(Join.path("/main").to("/pages/tools/dash.xhtml"))
                .addRule(Join.path("/position_mgr").to("/pages/admin/jobPosition.xhtml"))
                .addRule(Join.path("/user_mgr").to("/pages/admin/user.xhtml"))
                .addRule(Join.path("/supplier_mgr").to("/pages/admin/supplier.xhtml"))
                .addRule(Join.path("/dept_mgr").to("/pages/admin/department.xhtml"))
                .addRule(Join.path("/client_mgr").to("/pages/admin/client.xhtml"))
                .addRule(Join.path("/material_group_mgr").to("/pages/admin/materialGroup.xhtml"))
                .addRule(Join.path("/material_type_mgr").to("/pages/admin/materialSubgroup.xhtml"))
                .addRule(Join.path("/material_mgr").to("/pages/admin/material.xhtml"))
                .addRule(Join.path("/product_group_mgr").to("/pages/admin/productGroup.xhtml"))
                .addRule(Join.path("/product_type_mgr").to("/pages/admin/productSubgroup.xhtml"))
                .addRule(Join.path("/product_mgr").to("/pages/admin/product.xhtml"))
                .addRule(Join.path("/warehouse_mgr").to("/pages/admin/warehouse.xhtml"))
                .addRule(Join.path("/warehouse_import").to("/pages/warehouse/warehouseImport.xhtml"))
                .addRule(Join.path("/warehouse_export").to("/pages/warehouse/warehouseExport.xhtml"))
                .addRule(Join.path("/warehouse_transfer").to("/pages/warehouse/warehouseTransfer.xhtml"))
                .addRule(Join.path("/warehouse_dismiss").to("/pages/warehouse/warehouseDismiss.xhtml"))
                .addRule(Join.path("/warehouse_check").to("/pages/warehouse/warehouseCheck.xhtml"))
                .addRule(Join.path("/create_product").to("/pages/product/createProduct.xhtml"))
                .addRule(Join.path("/material_of_product").to("/pages/warehouse/configProductMaterial.xhtml"))
//                .addRule(Join.path("/report").to("/pages/tools/report.xhtml"))
                ;
    }
}
