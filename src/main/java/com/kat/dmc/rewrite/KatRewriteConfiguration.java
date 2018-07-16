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
                .addRule(Join.path("/error").to("/pages/error.xhtml").withInboundCorrection())
                .addRule(Join.path("/config").to("/pages/admin/config.xhtml").withInboundCorrection())
                .addRule(Join.path("/login").to("/pages/admin/login.xhtml").withInboundCorrection())
                .addRule(Join.path("/logout").to("/pages/admin/logout.xhtml").withInboundCorrection())
                .addRule(Join.path("/main").to("/pages/tools/dash.xhtml").withInboundCorrection())
                .addRule(Join.path("/position_mgr").to("/pages/admin/jobPosition.xhtml").withInboundCorrection())
                .addRule(Join.path("/user_mgr").to("/pages/admin/user.xhtml").withInboundCorrection())
                .addRule(Join.path("/supplier_mgr").to("/pages/admin/supplier.xhtml").withInboundCorrection())
                .addRule(Join.path("/dept_mgr").to("/pages/admin/department.xhtml").withInboundCorrection())
                .addRule(Join.path("/client_mgr").to("/pages/admin/client.xhtml").withInboundCorrection())
                .addRule(Join.path("/material_group_mgr").to("/pages/admin/materialGroup.xhtml").withInboundCorrection())
                .addRule(Join.path("/material_type_mgr").to("/pages/admin/materialSubgroup.xhtml").withInboundCorrection())
                .addRule(Join.path("/material_mgr").to("/pages/admin/material.xhtml").withInboundCorrection())
                .addRule(Join.path("/product_group_mgr").to("/pages/admin/productGroup.xhtml").withInboundCorrection())
                .addRule(Join.path("/product_type_mgr").to("/pages/admin/productSubgroup.xhtml").withInboundCorrection())
                .addRule(Join.path("/product_mgr").to("/pages/admin/product.xhtml").withInboundCorrection())
                .addRule(Join.path("/warehouse_mgr").to("/pages/admin/warehouse.xhtml").withInboundCorrection())
                .addRule(Join.path("/warehouse_import").to("/pages/warehouse/warehouseImport.xhtml").withInboundCorrection())
                .addRule(Join.path("/warehouse_export").to("/pages/warehouse/warehouseExport.xhtml").withInboundCorrection())
                .addRule(Join.path("/warehouse_transfer").to("/pages/warehouse/warehouseTransfer.xhtml").withInboundCorrection())
                .addRule(Join.path("/warehouse_dismiss").to("/pages/warehouse/warehouseDismiss.xhtml").withInboundCorrection())
                .addRule(Join.path("/warehouse_check").to("/pages/warehouse/warehouseCheck.xhtml").withInboundCorrection())
                .addRule(Join.path("/create_product").to("/pages/product/createProduct.xhtml").withInboundCorrection())
                .addRule(Join.path("/material_of_product").to("/pages/warehouse/configProductMaterial.xhtml").withInboundCorrection())
                ;
    }
}
