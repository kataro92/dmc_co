DROP TABLE IF EXISTS dmc_warehouse_status;
create or replace view dmc_warehouse_status as
  SELECT dmi.warehouse_id,
    to_char(dmi.import_date, 'yyyyMMdd' :: text) AS process_date,
    dmi.category_id,
    dmid.material_id,
    mg.id                                       AS group_id,
    mg.name                                      AS group_name,
    ms.id                                       AS subgroup_id,
    ms.name                                      AS subgroup_name,
    sum(dmid.quantity)                           AS quantity,
    sum(dmid.price)                              AS price,
    'imp' :: text                                AS type,
    ma.name                                      AS name
  FROM dmc_material_import dmi,
    dmc_material_import_detail dmid,
    dmc_material_group mg,
    dmc_material_subgroup ms, dmc_material ma
  WHERE ((dmi.id = dmid.material_import_id) AND (dmid.material_group_id = ms.id) AND
         ((mg.id) :: text = (ms.material_group_code) :: text) AND (dmi.status = 1))
        AND dmid.material_id = ma.id
  GROUP BY dmi.warehouse_id, dmi.category_id, (to_char(dmi.import_date, 'yyyyMMdd' :: text)), mg.id, ms.id,
    dmid.material_id, ma.name
  UNION ALL
  SELECT COALESCE(NULLIF(dmi.export_from_id, 0), dmi.warehouse_id) AS warehouse_id,
         to_char(dmi.export_date, 'yyyyMMdd' :: text)              AS process_date,
    dmi.category_id,
    dmid.material_id,
         mg.id                                                    AS group_id,
         mg.name                                                   AS group_name,
         ms.id                                                    AS subgroup_id,
         ms.name                                                   AS subgroup_name,
         (-sum(dmid.quantity))                                     AS quantity,
         (-sum(dmid.price))                                        AS price,
         'exp' :: text                                             AS type,
         ma.name                                      AS name
  FROM dmc_material_export dmi,
    dmc_material_export_detail dmid,
    dmc_material_group mg,
    dmc_material_subgroup ms, dmc_material ma
  WHERE ((dmi.id = dmid.material_export_id) AND (dmid.material_group_id = ms.id) AND
         ((mg.id) :: text = (ms.material_group_code) :: text) AND (dmi.status = 1) AND (dmi.export_from <> 3))
        AND dmid.material_id = ma.id
  GROUP BY COALESCE(NULLIF(dmi.export_from_id, 0), dmi.warehouse_id), dmi.category_id,
    (to_char(dmi.export_date, 'yyyyMMdd' :: text)), mg.id, ms.id,
    dmid.material_id, ma.name;