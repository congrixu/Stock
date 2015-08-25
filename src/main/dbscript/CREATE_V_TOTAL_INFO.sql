CREATE VIEW `V_TOTAL_INFO` AS 
SELECT COUNT(0) AS count_,
       SUM(salorder . total_num) AS total_num,
       SUM(salorder . total_price) AS total_price,
       salorder . sales_date AS operdate,
       'sales' AS type_
  FROM t_sales_order salorder
 GROUP BY salorder . sales_date
UNION ALL
SELECT COUNT(0) AS COUNT(*),
       SUM(pitem . num_) AS SUM(pitem.num_),
       SUM(porder . total_price) AS SUM(porder.total_price),
       porder . create_date AS operdate,
       'purchase' AS purchase
  FROM (t_purchase_order porder JOIN t_purchase_item pitem)
 WHERE (porder . purchase_order_id = pitem . purchase_order_id)
 GROUP BY porder . create_date
UNION ALL
SELECT COUNT(0) AS COUNT(*),
       SUM(salorder . total_num) AS SUM(salorder.total_num),
       SUM(salorder . total_price) AS SUM(salorder.total_price),
       salorder . sales_date AS operdate,
       'gift' AS gift
  FROM (t_sales_order salorder JOIN t_sales_item sitem)
 WHERE ((salorder . sales_order_id = sitem . sales_order_id) AND
       (sitem . price_ <= 0))
 GROUP BY salorder . sales_date
