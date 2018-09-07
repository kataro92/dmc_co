create view dmc_warehouse_status as
  SELECT dmi.warehouse_id,
         to_char(dmi.import_date, 'ddMMyyyy' :: text) AS process_date,
         dmi.category_id,
        dmid.material_id,
         mg._id                                       AS group_id,
         mg.name                                      AS group_name,
         ms._id                                       AS subgroup_id,
         ms.name                                      AS subgroup_name,
         sum(dmid.quantity)                           AS quantity,
         sum(dmid.price)                              AS price,
         'imp' :: text                                AS type
  FROM dmc_material_import dmi,
       dmc_material_import_detail dmid,
       material_group mg,
       material_subgroup ms
  WHERE ((dmi.id = dmid.material_import_id) AND (dmid.material_group_id = ms._id) AND
         ((mg._id) :: text = (ms.material_group_code) :: text) AND (dmi.status = 0))
  GROUP BY dmi.warehouse_id, dmi.category_id, (to_char(dmi.import_date, 'ddMMyyyy' :: text)), mg._id, ms._id,
           dmid.material_id
  UNION ALL
  SELECT COALESCE(NULLIF(dmi.export_from_id, 0), dmi.warehouse_id) AS warehouse_id,
         to_char(dmi.export_date, 'ddMMyyyy' :: text)              AS process_date,
         dmi.category_id,
         dmid.material_id,
         mg._id                                                    AS group_id,
         mg.name                                                   AS group_name,
         ms._id                                                    AS subgroup_id,
         ms.name                                                   AS subgroup_name,
         (-sum(dmid.quantity))                                     AS quantity,
         (-sum(dmid.price))                                        AS price,
         'exp' :: text                                             AS type
  FROM dmc_material_export dmi,
       dmc_material_export_detail dmid,
       material_group mg,
       material_subgroup ms
  WHERE ((dmi.id = dmid.material_export_id) AND (dmid.material_group_id = ms._id) AND
         ((mg._id) :: text = (ms.material_group_code) :: text) AND (dmi.status = 0) AND (dmi.export_from <> 3))
  GROUP BY COALESCE(NULLIF(dmi.export_from_id, 0), dmi.warehouse_id), dmi.category_id,
           (to_char(dmi.export_date, 'ddMMyyyy' :: text)), mg._id, ms._id,
           dmid.material_id;