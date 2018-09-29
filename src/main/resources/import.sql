create or replace view dmc_warehouse_status as
  SELECT dmi.warehouse_id,
         to_char(dmi.import_date, 'yyyyMMdd' :: text) AS process_date,
         dmi.category_id,
         dmid.material_id,
         mg._id                                       AS group_id,
         mg.name                                      AS group_name,
         ms._id                                       AS subgroup_id,
         ms.name                                      AS subgroup_name,
         sum(dmid.quantity)                           AS quantity,
         sum(dmid.price)                              AS price,
         'imp' :: text                                AS type,
         ma.name                                      AS name
  FROM dmc_material_import dmi,
       dmc_material_import_detail dmid,
       material_group mg,
       material_subgroup ms, material ma
  WHERE ((dmi.id = dmid.material_import_id) AND (dmid.material_group_id = ms._id) AND
         ((mg._id) :: text = (ms.material_group_code) :: text) AND (dmi.status = 0))
  AND dmid.material_id = ma._id
  GROUP BY dmi.warehouse_id, dmi.category_id, (to_char(dmi.import_date, 'yyyyMMdd' :: text)), mg._id, ms._id,
           dmid.material_id, ma.name
  UNION ALL
  SELECT COALESCE(NULLIF(dmi.export_from_id, 0), dmi.warehouse_id) AS warehouse_id,
         to_char(dmi.export_date, 'yyyyMMdd' :: text)              AS process_date,
         dmi.category_id,
         dmid.material_id,
         mg._id                                                    AS group_id,
         mg.name                                                   AS group_name,
         ms._id                                                    AS subgroup_id,
         ms.name                                                   AS subgroup_name,
         (-sum(dmid.quantity))                                     AS quantity,
         (-sum(dmid.price))                                        AS price,
         'exp' :: text                                             AS type,
         ma.name                                      AS name
  FROM dmc_material_export dmi,
       dmc_material_export_detail dmid,
       material_group mg,
       material_subgroup ms, material ma
  WHERE ((dmi.id = dmid.material_export_id) AND (dmid.material_group_id = ms._id) AND
         ((mg._id) :: text = (ms.material_group_code) :: text) AND (dmi.status = 0) AND (dmi.export_from <> 3))
    AND dmid.material_id = ma._id
  GROUP BY COALESCE(NULLIF(dmi.export_from_id, 0), dmi.warehouse_id), dmi.category_id,
           (to_char(dmi.export_date, 'yyyyMMdd' :: text)), mg._id, ms._id,
           dmid.material_id, ma.name;
INSERT INTO public.dmc_user (_id, code, extra_props, name, password, permission, permissions, status, username) VALUES (1, 'admin', '', 'Admin', '3f75336dfc774c69627d62afd4469862', 1, null, 0, 'admin');

